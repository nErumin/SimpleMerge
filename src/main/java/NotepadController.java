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
    @FXML
    private TextArea textpane;

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
                String sCurrentLine;
                br = new BufferedReader(new FileReader(file));
                while ((sCurrentLine = br.readLine()) != null) {
                    textpane.appendText(sCurrentLine + "\n");
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
    @FXML
    protected void exitApp(ActionEvent event) {
        Platform.exit();
    }

}
