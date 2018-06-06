import javafx.fxml.FXML;
import javafx.stage.*;
import java.io.*;
import javafx.application.Platform;
import javafx.scene.control.Button;

import java.util.List;

import javafx.util.Pair;

import model.*;
import org.fxmisc.richtext.Caret;
import org.fxmisc.richtext.InlineCssTextArea;
import utility.StringUtility;

public class MainWindowController {
    private static final String LEFT_BACKUP_PATH = "left.bak";
    private static final String RIGHT_BACKUP_PATH = "right.bak";

    private FileChooser fileChooser = new FileChooser();
    private File file;
    private BackupScheduler leftBackupScheduler;

    private FileChooser fileChooserRight = new FileChooser();
    private File fileRight;
    private BackupScheduler rightBackupScheduler;

    @FXML
    private InlineCssTextArea textpane;
    @FXML
    private InlineCssTextArea textpaneRight;
    @FXML
    private Button editButton;
    @FXML
    private Button loadButton;
    @FXML
    private Button saveButton;
    @FXML
    private Button editButtonRight;
    @FXML
    private Button loadButtonRight;
    @FXML
    private Button saveButtonRight;
    @FXML
    private Button copytorightButton;
    @FXML
    private Button compareButton;
    @FXML
    private Button copytoleftButton;
    @FXML
    private Button refreshButton;
    @FXML
    private Button refreshButtonRight;
    @FXML
    private Button viewButton;

    @FXML
    private void initialize() throws IOException {
        initializeComponent(true);
        initializeComponent(false);

        refreshButton.setDisable(true);
        refreshButtonRight.setDisable(true);
        loadButtonRight.setDisable(false);
        loadButtonRight.setDisable(false);
        loadButton.setDisable(false);
        saveButtonRight.setDisable(false);
        saveButton.setDisable(false);
        compareButton.setMnemonicParsing(true);

        compareButton.setText("_Compare");

        leftBackupScheduler = new BackupScheduler(
            () -> textpane.getText(), LEFT_BACKUP_PATH
        );

        rightBackupScheduler = new BackupScheduler(
            () -> textpaneRight.getText(), RIGHT_BACKUP_PATH
        );

        textpane.appendText(leftBackupScheduler.loadBackup());
        textpaneRight.appendText(rightBackupScheduler.loadBackup());

        textpane.setShowCaret(Caret.CaretVisibility.ON);
        textpaneRight.setShowCaret(Caret.CaretVisibility.ON);

        leftBackupScheduler.start();
        rightBackupScheduler.start();
    }

    private void initializeComponent(boolean isLeft) {
        if (isLeft) {
            setEditableLeft(false);
        } else {
            setEditableRight(false);
        }

        compareDependentComponentSet(false);
    }

    private void removeStyle() {
        textpane.setStyle(0, textpane.getLength(), " ");
        textpaneRight.setStyle(0, textpaneRight.getLength(), " ");
    }

    private void compareDependentComponentSet(boolean state) {
        copytoleftButton.setDisable(!state);
        copytorightButton.setDisable(!state);
        viewButton.setDisable(!state);
    }

    private void setEditableLeft(boolean state) {
        textpane.setEditable(state);
        editButton.setDisable(state);
        removeStyle();
    }

    private void setEditableRight(boolean state) {
        textpaneRight.setEditable(state);
        editButtonRight.setDisable(state);
        removeStyle();
    }

    @FXML
    protected void comparePanel() {
        setEditableLeft(false);
        setEditableRight(false);

        Text leftPanelText = new Text(textpane.getText());
        Text rightPanelText = new Text(textpaneRight.getText());
        Comparer panelComparer = new Comparer();
        Pair<List<String>, List<String>> pair = panelComparer.panelFix(leftPanelText, rightPanelText);

        String fixedLeft = StringUtility.compact(pair.getKey(), true);
        String fixedRight = StringUtility.compact(pair.getValue(), true);

        textpane.clear();
        textpane.appendText(fixedLeft);

        textpaneRight.clear();
        textpaneRight.appendText(fixedRight);

        leftPanelText.replace(0, fixedLeft);
        rightPanelText.replace(0, fixedRight);
        highlightDifference(panelComparer.findDifLine(leftPanelText, rightPanelText));

        compareButton.setDisable(true);
        compareDependentComponentSet(true);
    }

    private void highlightDifference(Iterable<String> diffLine) {
        Text leftPanelText = new Text(textpane.getText());
        Text rightPanelText = new Text(textpaneRight.getText());

        for (String s : diffLine) {
            String leftLine = leftPanelText.getLine(Integer.parseInt(s));
            String rightLine = rightPanelText.getLine(Integer.parseInt(s));

            int leftDiffStartPoint = leftPanelText.indexOfStartFromLine(
                leftLine, Integer.parseInt(s)
            );

            int rightDiffStartPoint = rightPanelText.indexOfStartFromLine(
                rightLine, Integer.parseInt(s)
            );

            highlightLine(leftDiffStartPoint,
                leftDiffStartPoint + leftLine.length());
            highlightLineRight(rightDiffStartPoint,
                rightDiffStartPoint + rightLine.length());
        }
    }

    /**
     * Highlight left panel
     * @param from start location for highlighting
     * @param to end location for highlighting
     */
    private void highlightLine(int from, int to) {
        textpane.setStyle(from, to, "-rtfx-background-color: #ff9999; ");
    }

    /**
     * Highlight right panel
     * @param from start location for highlighting
     * @param to end location for highlighting
     */
    private void highlightLineRight(int from, int to) {
        textpaneRight.setStyle(from, to, "-rtfx-background-color: #b3ffcb; ");
    }

    @FXML
    protected void copyToLeft(){
        Text leftPanelText = new Text(textpane.getText());
        Text rightPanelText = new Text(textpaneRight.getText());

        Merger panelMerger = new Merger();
        int cursorPosition = textpaneRight.getCaretPosition();
        int mergeTargetLine = rightPanelText.positionToLineIndex(cursorPosition);

        Pair<List<String>, List<String>> mergedResult =
            panelMerger.mergeRightLeft(mergeTargetLine, leftPanelText, rightPanelText);

        refreshTextPane(mergedResult);
    }

    @FXML
    protected void copyToRight() {
        Text leftPanelText = new Text(textpane.getText());
        Text rightPanelText = new Text(textpaneRight.getText());

        int cursorPosition = textpane.getCaretPosition();
        int mergeTargetLine = leftPanelText.positionToLineIndex(cursorPosition);

        Merger panelMerger = new Merger();
        Pair<List<String>, List<String>> mergedResult =
            panelMerger.mergeLeftRight(mergeTargetLine, leftPanelText, rightPanelText);

        refreshTextPane(mergedResult);
    }

    private void refreshTextPane(Pair<List<String>, List<String>> pair) {
        String fixedLeft = StringUtility.compact(pair.getKey(), true);
        String fixedRight = StringUtility.compact(pair.getValue(), true);

        textpane.clear();
        textpaneRight.clear();

        textpane.insertText(0, fixedLeft);
        textpaneRight.insertText(0, fixedRight);

        removeStyle();
        compareDependentComponentSet(false);
        compareButton.setDisable(false);
    }

    @FXML
    protected void openFile() {
        File chosenFile = fileChooser.showOpenDialog(null);
        if (chosenFile != null) {
            file = chosenFile;
            textpane.clear();
            textpane.appendText(loadFileContent(file));
            compareButton.setDisable(false);
            initializeComponent(true);
            refreshButton.setDisable(false);
        }
    }

    private String loadFileContent(File file) {
        try (FileTransmitter transmitter = new FileTransmitter(file.getPath())) {
            return transmitter.load();
        } catch (IOException exception) {
            return StringUtility.EMPTY_STRING;
        }
    }

    @FXML
    protected void editFile() {
        setEditableLeft(true);
        compareButton.setDisable(false);
    }

    @FXML
    protected void saveFile() {
        if (file == null) {
            file = fileChooser.showSaveDialog(null);
        }

        if (file != null) {
            saveContentToFile(file, textpane.getText());
        }
    }

    @FXML
    protected void saveAsFile() {
        File chosenFile = fileChooser.showSaveDialog(null);

        if (chosenFile != null) {
            saveContentToFile(chosenFile, textpane.getText());
        }
    }

    private void saveContentToFile(File file, String content) {
        try (FileTransmitter transmitter = new FileTransmitter(file.getPath())) {
            transmitter.save(content);
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }

    @FXML
    protected void openFileRight() {
        File chosenFile = fileChooserRight.showOpenDialog(null);
        if (chosenFile != null) {
            fileRight = chosenFile;
            textpaneRight.clear();
            textpaneRight.appendText(loadFileContent(fileRight));
            compareButton.setDisable(false);
            initializeComponent(false);
            refreshButtonRight.setDisable(false);
        }
    }

    @FXML
    protected void editFileRight() {
        setEditableRight(true);
        compareButton.setDisable(false);
    }

    @FXML
    protected void saveFileRight() {
        if (fileRight == null) {
            fileRight = fileChooserRight.showSaveDialog(null);
        }

        if (fileRight != null) {
            saveContentToFile(fileRight, textpaneRight.getText());
        }
    }

    @FXML
    protected void saveAsFileRight() {
        File chosenFile = fileChooser.showSaveDialog(null);

        if (chosenFile != null) {
            saveContentToFile(chosenFile, textpaneRight.getText());
        }
    }

    @FXML
    protected void viewButtonAction() {

        Text leftPanelText = new Text(textpane.getText());
        Text rightPanelText = new Text(textpaneRight.getText());
        Comparer panelComparer = new Comparer();
        Pair<List<String>, List<String>> pair = panelComparer.panelFix(leftPanelText, rightPanelText);
        List<String> diffLines = panelComparer.findDifLine(leftPanelText, rightPanelText);

        ViewWindowController fv = new ViewWindowController();
        fv.createViewWindow(pair, diffLines);
    }
    @FXML
    protected void findButton() {
        removeStyle();
        compareDependentComponentSet(false);
        compareButton.setDisable(true);

        FindWindowController fv = new FindWindowController();
        fv.createFindWindow(
            () -> textpane.getText(),
            (index, searchWord) -> {
                removeStyle();
                textpane.moveTo(index);
                highlightLine(index, index + searchWord.length());
            },
            () -> {
                removeStyle();
                compareButton.setDisable(false);
            }
        );
    }

    @FXML
    protected void findButtonRight() {
        removeStyle();
        compareDependentComponentSet(false);
        compareButton.setDisable(true);

        FindWindowController fv = new FindWindowController();
        fv.createFindWindow(
            () -> textpaneRight.getText(),
            (index, searchWord) -> {
                removeStyle();
                textpaneRight.moveTo(index);
                highlightLineRight(index, index + searchWord.length());
            },
            () -> {
                removeStyle();
                compareButton.setDisable(false);
            }
        );
    }

    @FXML
    protected void refreshButtonAction() {
        if (file != null) {
            try (FileTransmitter transmitter = new FileTransmitter(file.getPath())) {
                textpane.clear();
                textpane.appendText(transmitter.load());
            } catch (IOException exception) {

            }

            initializeComponent(true);
        }
    }

    @FXML
    protected void refreshButtonActionRight() {
        if (fileRight != null) {
            try (FileTransmitter transmitter = new FileTransmitter(fileRight.getPath())) {
                textpaneRight.clear();
                textpaneRight.appendText(transmitter.load());
            } catch (IOException exception) {

            }

            initializeComponent(false);
        }
    }

    @FXML
    protected void exitApp() {
        Platform.exit();

        leftBackupScheduler.sweepBackup();
        rightBackupScheduler.sweepBackup();

        leftBackupScheduler.finish();
        rightBackupScheduler.finish();
    }
}
