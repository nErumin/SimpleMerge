package controller;

import javafx.fxml.FXML;

import java.io.*;
import java.util.List;

import javafx.scene.control.ListView;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Pair;

public class ViewWindowController {
    @FXML
    private ListView<String> listView;
    @FXML
    private ListView<String> listViewRight;
    private Pair<List<String>, List<String>> diffPair;
    private List<String> diffLines;

    public ViewWindowController(Pair<List<String>, List<String>> diffPair,
                                List<String> diffLines) {
        this.diffPair = diffPair;
        this.diffLines = diffLines;
    }

    public ViewWindowController() {

    }

    public void createViewWindow(Pair<List<String>, List<String>> diffPair,
                                 List<String> diffLines) {
        try {
            SplitPane pane = FXMLLoader.load(getClass().getResource(
                "ViewWindowTemplate.fxml"), null, null,
                (c) -> new ViewWindowController(diffPair, diffLines));

            Scene scene = new Scene(pane);
            Stage stage = new Stage();
            stage.setScene(scene);
            // setting the height and width of stage.
            stage.setWidth(600);
            stage.setHeight(445);
            stage.setResizable(false);
            stage.getIcons().add(
                new Image(ViewWindowController.class.getResourceAsStream("icon.png"))
            );
            stage.setTitle("Different Lines View");
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void initialize() {
        for (String diffLine : diffLines) {
            int lineNumber = Integer.parseInt(diffLine);
            listView.getItems().addAll(diffPair.getKey().get(lineNumber));
            listViewRight.getItems().addAll(diffPair.getValue().get(lineNumber));
        }
    }
}
