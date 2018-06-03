import javafx.fxml.FXML;

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




    public void main() {
        try {

            Pane pane = FXMLLoader.load(getClass().getResource(
                "findtemplate.fxml"));

            Scene scene = new Scene(pane);
            Stage stage = new Stage();
            stage.setScene(scene);



            // setting the height and width of stage.
            stage.setWidth(400);
            stage.setHeight(40);
            stage.setTitle("FindView Sample");
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
