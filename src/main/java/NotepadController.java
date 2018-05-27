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

public class NotepadController{

    private FileChooser fileChooser = new FileChooser();
    private File file;

    private FileChooser fileChooserRight = new FileChooser();
    private File fileRight;
    @FXML
    private TextArea textpane;
    @FXML
    private TextArea textpaneRight;

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
                    textpane.appendText(sCurrentLine );
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
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
                stage.setTitle(fileRight.getName() + " - Notepad");
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
            stage.setTitle(fileRight.getName() + " - Notepad");
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
    protected void exitApp(ActionEvent event) {
        Platform.exit();
    }

}
