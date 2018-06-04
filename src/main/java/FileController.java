
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.stage.*;
import java.io.*;
import javafx.application.Platform;
import javafx.scene.control.Button;

import java.util.List;
import javafx.util.Pair;
import javafx.stage.Stage;


import model.BackupScheduler;
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

    public void initialize() throws IOException {
        editButtonRight.setDisable(false);
        editButton.setDisable(false);
        loadButtonRight.setDisable(false);
        loadButton.setDisable(false);
        saveButtonRight.setDisable(false);
        saveButton.setDisable(false);
        textpaneRight.setEditable(false);
        textpane.setEditable(false);

        leftBackupScheduler = new BackupScheduler(
            () -> textpane.getText(), LEFT_BACKUP_PATH
        );

        rightBackupScheduler = new BackupScheduler(
            () -> textpaneRight.getText(), RIGHT_BACKUP_PATH
        );

        textpane.insertText(0, leftBackupScheduler.loadBackup());
        textpaneRight.insertText(0, rightBackupScheduler.loadBackup());

        leftBackupScheduler.start();
        rightBackupScheduler.start();
    }

    @FXML
    protected void comparePanel() {
        Text leftPanelText = new Text(textpane.getText());
        Text rightPanelText = new Text(textpaneRight.getText());
        Comparison panelComparison = new Comparison();
        Pair<List<String>, List<String>> pair = panelComparison.panelFix(leftPanelText, rightPanelText);

        String fixedLeft = StringUtility.compact(pair.getKey(), true);
        String fixedRight = StringUtility.compact(pair.getValue(), true);

        leftPanelText.replace(0, fixedLeft);
        rightPanelText.replace(0, fixedRight);

        List<String> diffLine = panelComparison.findDifLine(leftPanelText, rightPanelText);

        textpane.clear();
        textpaneRight.clear();

        textpane.insertText(0, leftPanelText.toString());
        textpaneRight.insertText(0, rightPanelText.toString());

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

    @FXML
    protected void copyToLeft(){
        Text leftPanelText = new Text(textpane.getText());
        Text rightPanelText = new Text(textpaneRight.getText());
        int cursorPosition = textpaneRight.getCaretPosition();

        Merger panelMerger = new Merger();
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

        Merger panelMerger = new Merger();
        int mergeTargetLine = leftPanelText.positionToLineIndex(cursorPosition);

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
    }

    @FXML
    protected void newFile(ActionEvent event) {
        textpane.clear();
        Stage stage = (Stage) textpane.getScene().getWindow();
        stage.setTitle("Untitled - Notepad");
        file = null;
    }

    @FXML
    protected void openFile(ActionEvent event) {
        file = fileChooser.showOpenDialog(null);
        if (file != null) {
            Stage stage = (Stage) textpane.getScene().getWindow();
            stage.setTitle(file.getName() + " - Notepad");
            BufferedReader br = null;
            try {
                textpane.clear();

                String sCurrentLine;
                br = new BufferedReader(new FileReader(file));
                while ((sCurrentLine = br.readLine()) != null) {
                    textpane.appendText(sCurrentLine + "\n" );
                }
                br.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            textpane.setEditable(false);
            editButton.setDisable(false);
            textpane.setStyle(" "); //ERASEIT!
        }
    }

    @FXML
    protected void editFile(ActionEvent event) {
        textpane.setEditable(true);
        editButton.setDisable(true);

    }
    @FXML
    protected void saveFile(ActionEvent event) {
        String content = textpane.getText();
        if (file != null) {
            try {
                // if file doesnt exists, then create it
                if (!file.exists()) {
                    file.createNewFile();
                }
                FileWriter fw = new FileWriter(file.getAbsoluteFile());
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(content);
                bw.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            // open a file dialog box
            file = fileChooser.showSaveDialog(null);
            if (file != null) {
                Stage stage = (Stage) textpane.getScene().getWindow();
                stage.setTitle(file.getName() + " - Notepad");
                try {
                    // if file doesnt exists, then create it
                    if (!file.exists()) {
                        file.createNewFile();
                    }
                    FileWriter fw = new FileWriter(file.getAbsoluteFile());
                    BufferedWriter bw = new BufferedWriter(fw);
                    bw.write(content);
                    bw.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @FXML
    protected void saveasFile(ActionEvent event) {
        file = fileChooser.showSaveDialog(null);

        String content = textpane.getText();
        if (file != null) {
            Stage stage = (Stage) textpane.getScene().getWindow();
            stage.setTitle(file.getName() + " - Notepad");
            try {
                // if file doesnt exists, then create it
                if (!file.exists()) {
                    file.createNewFile();
                }
                FileWriter fw = new FileWriter(file.getAbsoluteFile());
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(content);
                bw.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //////RIGHT

    @FXML
    protected void newFileRight(ActionEvent event) {
        textpaneRight.clear();
        Stage stage = (Stage) textpaneRight.getScene().getWindow();
        stage.setTitle("Untitled - Notepad");
        fileRight = null;
    }

    @FXML
    protected void openFileRight(ActionEvent event) {
        fileRight = fileChooserRight.showOpenDialog(null);
        if (fileRight != null) {
            Stage stage = (Stage) textpaneRight.getScene().getWindow();
            stage.setTitle(fileRight.getName() + " - Notepad");
            BufferedReader br = null;
            try {
                textpaneRight.clear();

                String sCurrentLine;
                br = new BufferedReader(new FileReader(fileRight));
                while ((sCurrentLine = br.readLine()) != null) {
                    textpaneRight.appendText(sCurrentLine + "\n");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            textpaneRight.setEditable(false);
            editButtonRight.setDisable(false);
            textpaneRight.setStyle(" "); //ERASEIT!

        }


    }
    @FXML
    protected void editFileRight(ActionEvent event) {
        textpaneRight.setEditable(true);
        editButtonRight.setDisable(true);

    }
    @FXML
    protected void saveFileRight(ActionEvent event) {
        String content = textpaneRight.getText();
        if (fileRight != null) {
            try {
                // if file doesnt exists, then create it
                if (!fileRight.exists()) {
                    fileRight.createNewFile();
                }
                FileWriter fw = new FileWriter(fileRight.getAbsoluteFile());
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(content);
                bw.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            // open a file dialog box
            fileRight = fileChooserRight.showSaveDialog(null);
            if (fileRight != null) {
                Stage stage = (Stage) textpaneRight.getScene().getWindow();
                stage.setTitle(fileRight.getName() + " - SimpleMerge");
                try {
                    // if file doesnt exists, then create it
                    if (!fileRight.exists()) {
                        fileRight.createNewFile();
                    }
                    FileWriter fw = new FileWriter(fileRight.getAbsoluteFile());
                    BufferedWriter bw = new BufferedWriter(fw);
                    bw.write(content);
                    bw.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @FXML
    protected void saveasFileRight(ActionEvent event) {
        fileRight = fileChooserRight.showSaveDialog(null);

        String content = textpaneRight.getText();
        if (fileRight != null) {
            Stage stage = (Stage) textpaneRight.getScene().getWindow();
            stage.setTitle(fileRight.getName() + " - SimpleMerge");
            try {
                // if file doesnt exists, then create it
                if (!fileRight.exists()) {
                    fileRight.createNewFile();
                }
                FileWriter fw = new FileWriter(fileRight.getAbsoluteFile());
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(content);
                bw.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    @FXML
    protected void compareButtonAction(){
        ViewController fv = new ViewController();
        fv.compareButtonAction();
    }
    /**
     * Highlight left panel
     * @param from start location for highlighting
     * @param to end location for highlighting
     */
    public void highlightLine(int from, int to) {
        textpane.setStyle(from, to, "-rtfx-background-color: #ff9999; ");
    }

    /**
     * Highlight right panel
     * @param from start location for highlighting
     * @param to end location for highlighting
     */
    public void highlightLineRight(int from, int to) {
        textpaneRight.setStyle(from, to, "-rtfx-background-color: #b3ffcb; ");
    }


    @FXML
    protected void exitApp(ActionEvent event) {
        Platform.exit();

        System.out.println("EXIT APP");
        leftBackupScheduler.finish();
        rightBackupScheduler.finish();
    }
}
