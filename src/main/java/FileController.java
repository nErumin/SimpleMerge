import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import java.awt.*;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.stage.*;
import java.io.*;
import javafx.application.Platform;
import javafx.event.*;
import javafx.stage.*;
import javafx.scene.control.Button;

import org.fxmisc.richtext.InlineCssTextArea;


public class FileController{

    private FileChooser fileChooser = new FileChooser();
    private File file;

    private FileChooser fileChooserRight = new FileChooser();
    private File fileRight;
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


    public void initialize() {
        editButtonRight.setDisable(false);
        editButton.setDisable(false);
        loadButtonRight.setDisable(false);
        loadButton.setDisable(false);
        saveButtonRight.setDisable(false);
        saveButton.setDisable(false);
        textpaneRight.setEditable(false);
        textpane.setEditable(false);
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
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        textpane.setEditable(false);
        editButton.setDisable(false);
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

        }
        textpaneRight.setEditable(false);
        editButtonRight.setDisable(false);

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

    /**
     * Highlight left panel
     * @param from start location for highlighting
     * @param to end location for highlighting
     */
    public void highlightLine(int from, int to) {

        textpane.setStyle(from,to,"-rtfx-background-color: lightgreen; ");

    }

    /**
     * Highlight right panel
     * @param from start location for highlighting
     * @param to end location for highlighting
     */
    public void highlightLineRight(int from, int to) {
        textpaneRight.setStyle(from,to,"-rtfx-background-color: lightgreen; ");
    }


    @FXML
    protected void exitApp(ActionEvent event) {
        Platform.exit();
    }

}
