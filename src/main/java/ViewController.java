import javafx.fxml.FXML;

import java.io.*;

import javafx.scene.control.ListView;
import javafx.scene.control.Button;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.stage.Stage;

public class ViewController {
    @FXML
    private Button compareButton;

    @FXML
    public ListView<String> listView;
    @FXML
    public SplitPane splitPane;

    @FXML
    public ListView<String> listViewRight;


    ObservableList<String> list = FXCollections.observableArrayList("abc","def","ghi");
    ObservableList<String> listRight = FXCollections.observableArrayList("shc", "ltk", "Hunde woogun");

    public void compareButtonAction() {
        try {
            listView = new ListView<String>();
            listViewRight = new ListView<String>();
            SplitPane pane = FXMLLoader.load(getClass().getResource(
                "viewtemplate.fxml"));
            listView.setItems(list);
            listViewRight.setItems(listRight);
            //listView.setCellFactory(ComboBoxListCell);

            splitPane = new SplitPane();

            Scene scene = new Scene(pane);
            Stage stage = new Stage();
            stage.setScene(scene);



            // setting the height and width of stage.
            stage.setWidth(640);
            stage.setHeight(480);
            stage.setTitle("FindView Sample");
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
