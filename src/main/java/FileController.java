
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.stage.*;
import java.io.*;
import javafx.application.Platform;
import javafx.scene.control.Button;

import java.util.List;
import javafx.util.Pair;

import model.BackupScheduler;
import model.FileTransmitter;
import org.fxmisc.richtext.InlineCssTextArea;
import model.Text;
import utility.StringUtility;

public class FileController {
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

    public void initialize() throws IOException {
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

        //textpane.insertText(0, leftBackupScheduler.loadBackup());

        leftBackupScheduler.start();
        rightBackupScheduler.start();
    }

    private void initializeComponent(boolean isLeft) {
        if (isLeft) {
            textpane.setEditable(false);
            editButton.setDisable(false);
        } else {
            textpaneRight.setEditable(false);
            editButtonRight.setDisable(false);
        }

        textpane.setStyle(0, textpane.getLength(), " ");
        textpaneRight.setStyle(0, textpaneRight.getLength(), " ");
        copytoleftButton.setDisable(true);
        copytorightButton.setDisable(true);
    }

    @FXML
    protected void comparePanel() {
        Text leftPanelText = new Text(textpane.getText());
        Text rightPanelText = new Text(textpaneRight.getText());
        Comparison panelComparison = new Comparison();
        Pair<List<String>, List<String>> pair = panelComparison.panelFix(leftPanelText, rightPanelText);

        String fixedLeft = StringUtility.compact(pair.getKey(), true);
        String fixedRight = StringUtility.compact(pair.getValue(), true);

        textpane.clear();
        textpane.appendText(fixedLeft);

        textpaneRight.clear();
        textpaneRight.appendText(fixedRight);

        leftPanelText.replace(0, fixedLeft);
        rightPanelText.replace(0, fixedRight);
        highlightDifference(panelComparison.findDifLine(leftPanelText, rightPanelText));

        copytoleftButton.setDisable(false);
        copytorightButton.setDisable(false);
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

        copytoleftButton.setDisable(false);
        copytorightButton.setDisable(false);
    }

    @FXML
    protected void openFile(ActionEvent event) {
        File chosenFile = fileChooser.showOpenDialog(null);
        if (chosenFile != null) {
            file = chosenFile;
            textpane.clear();
            textpane.appendText(loadFileContent(file));
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
    protected void editFile(ActionEvent event) {
        textpane.setEditable(true);
        editButton.setDisable(true);

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
    protected void openFileRight(ActionEvent event) {
        File chosenFile = fileChooserRight.showOpenDialog(null);
        if (chosenFile != null) {
            fileRight = chosenFile;
            textpaneRight.clear();
            textpaneRight.appendText(loadFileContent(fileRight));
            initializeComponent(false);
            refreshButtonRight.setDisable(false);
        }
    }

    @FXML
    protected void editFileRight(ActionEvent event) {
        textpaneRight.setEditable(true);
        editButtonRight.setDisable(true);
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
        ViewController fv = new ViewController();

        Text leftPanelText = new Text(textpane.getText());
        Text rightPanelText = new Text(textpaneRight.getText());
        Comparison panelComparison = new Comparison();
        Pair<List<String>, List<String>> pair = panelComparison.panelFix(leftPanelText, rightPanelText);

        fv.viewWindow();
        fv.setList(pair);
    }
    @FXML
    protected void findButtonAction(){
        FindController fv = new FindController();
        fv.findWindow();
    }

    @FXML
    protected void refreshButtonAction() throws IOException {
        if (file != null) {
            try (FileTransmitter transmitter = new FileTransmitter(file.getPath())) {
                textpane.clear();
                textpane.appendText(transmitter.load());
            }

            initializeComponent(true);
        }
    }

    @FXML
    protected void refreshButtonActionRight() throws IOException {
        if (fileRight != null) {
            try (FileTransmitter transmitter = new FileTransmitter(fileRight.getPath())) {
                textpaneRight.clear();
                textpaneRight.appendText(transmitter.load());
            }

            initializeComponent(false);
        }
    }

    @FXML
    protected void exitApp(ActionEvent event) {
        Platform.exit();

        leftBackupScheduler.finish();
        rightBackupScheduler.finish();
    }
}
