import javafx.fxml.FXML;
import java.io.*;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

import javafx.scene.control.Alert;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Text;
import utility.Action;
import utility.StringUtility;

public class FindWindowController {
    private static final String ERROR_MESSAGE = "Cannot find text.";

    @FXML
    private TextField searchTextField;

    private Supplier<String> contentSupplier;
    private BiConsumer<Integer, String> resultConsumer;
    private String lastContent;
    private int lastFoundPosition;
    public FindWindowController(Supplier<String> contentSupplier,
                                BiConsumer<Integer, String> resultConsumer) {
        this.contentSupplier = contentSupplier;
        this.resultConsumer = resultConsumer;
        this.lastContent = StringUtility.EMPTY_STRING;
        this.lastFoundPosition = -1;
    }

    public FindWindowController() {

    }

    public void createFindWindow(Supplier<String> contentSupplier,
                                 BiConsumer<Integer, String> resultConsumer,
                                 Action closeAction) {
        try {
            Pane pane = FXMLLoader.load(getClass().getResource(
                "FindWindowTemplate.fxml"), null, null,
                c -> new FindWindowController(contentSupplier, resultConsumer));

            Scene scene = new Scene(pane);
            Stage stage = new Stage();
            stage.setScene(scene);
            // setting the height and width of stage.
            stage.setWidth(400);
            stage.setHeight(80);
            stage.setResizable(false);
            stage.initStyle(StageStyle.UTILITY);
            stage.getIcons().add(
                new Image(FindWindowController.class.getResourceAsStream("icon.png"))
            );
            stage.setTitle("Find");
            stage.setScene(scene);
            stage.setOnCloseRequest(event -> {
                closeAction.act();
            });
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void searchText() {
        Text contentText = new Text(contentSupplier.get());
        if (!lastContent.equals(contentText.toString())) {
            lastFoundPosition = -1;
        }

        int position = contentText.indexOf(searchTextField.getText(),
                                           lastFoundPosition + 1);
        if (position < 0) {
            // retry from start
            position = contentText.indexOf(searchTextField.getText(), 0);
            if (position < 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR, ERROR_MESSAGE);
                Stage alertStage =
                    (Stage) alert.getDialogPane().getScene().getWindow();

                alertStage.getIcons().add(
                    new Image(ViewWindowController.class.getResourceAsStream("icon.png"))
                );

                alert.showAndWait();
                return;
            }

        }

        lastFoundPosition = position;
        lastContent = contentText.toString();
        resultConsumer.accept(position, searchTextField.getText());
    }


}
