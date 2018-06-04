import javafx.fxml.FXML;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyCode;

import javafx.scene.input.KeyCodeCombination;
import java.io.*;

import javafx.scene.control.ListView;
import javafx.scene.control.Button;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class FindController {
    @FXML
    private Button findButton;
    @FXML
    private Button nextButton;
    @FXML
    private Button prevButton;

    public void findWindow() {
        try {

            Pane pane = FXMLLoader.load(getClass().getResource(
                "findtemplate.fxml"));

            Scene scene = new Scene(pane);
            Stage stage = new Stage();
            stage.setScene(scene);

            // setting the height and width of stage.
            stage.setWidth(400);
            stage.setHeight(60);
            stage.setTitle("Find");
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
