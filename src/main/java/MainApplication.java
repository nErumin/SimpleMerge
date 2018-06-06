import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import utility.Action;


public class MainApplication extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            // loading the fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "MainWindowTemplate.fxml"));

            // creating and initializing the scene.
            Scene scene = new Scene(loader.load());
            primaryStage.setScene(scene);

            // setting the height and width of stage.
            primaryStage.setWidth(900);
            primaryStage.setHeight(650);
            primaryStage.setResizable(false);

            // setting the App title
            primaryStage.setTitle("SimpleMerge");
            primaryStage.getIcons().add(
                new Image(MainApplication.class.getResourceAsStream("icon.png")));
            scene.getStylesheets().add(
                getClass().getResource("MainWindowDesignTemplate.css").toExternalForm());

            // display the stage
            primaryStage.show();

            registerShortcut(loader.getController(), scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void registerShortcut(MainWindowController controller, Scene scene) {
        KeyCombination openLeftKey = new KeyCodeCombination(KeyCode.O,
            KeyCombination.CONTROL_DOWN);

        KeyCombination openRightKey = new KeyCodeCombination(KeyCode.P,
            KeyCombination.CONTROL_DOWN);

        KeyCombination editLeftKey = new KeyCodeCombination(KeyCode.E,
            KeyCombination.CONTROL_DOWN);

        KeyCombination editRightKey = new KeyCodeCombination(KeyCode.R,
            KeyCombination.CONTROL_DOWN);

        KeyCombination saveLeftKey = new KeyCodeCombination(KeyCode.S,
            KeyCombination.CONTROL_DOWN);

        KeyCombination saveRightKey = new KeyCodeCombination(KeyCode.D,
            KeyCombination.CONTROL_DOWN);

        KeyCombination saveAsLeftKey = new KeyCodeCombination(KeyCode.S,
            KeyCombination.CONTROL_DOWN,
            KeyCombination.SHIFT_DOWN);

        KeyCombination saveAsRightKey = new KeyCodeCombination(KeyCode.D,
            KeyCombination.CONTROL_DOWN,
            KeyCombination.SHIFT_DOWN);

        KeyCombination refreshLeftKey = new KeyCodeCombination(KeyCode.R,
            KeyCombination.CONTROL_DOWN);

        KeyCombination refreshRightKey = new KeyCodeCombination(KeyCode.T,
            KeyCombination.CONTROL_DOWN);

        KeyCombination compareKey = new KeyCodeCombination(KeyCode.C,
            KeyCombination.CONTROL_DOWN,
            KeyCombination.SHIFT_DOWN);

        KeyCombination viewKey = new KeyCodeCombination(KeyCode.V,
            KeyCombination.CONTROL_DOWN,
            KeyCombination.SHIFT_DOWN);

        KeyCombination leftMergeKey = new KeyCodeCombination(KeyCode.K,
            KeyCombination.CONTROL_DOWN,
            KeyCombination.SHIFT_DOWN);

        KeyCombination rightMergeKey = new KeyCodeCombination(KeyCode.L,
            KeyCombination.CONTROL_DOWN,
            KeyCombination.SHIFT_DOWN);

        KeyCombination leftSearchKey = new KeyCodeCombination(KeyCode.F,
            KeyCombination.CONTROL_DOWN);

        KeyCombination rightSearchKey = new KeyCodeCombination(KeyCode.G,
            KeyCombination.CONTROL_DOWN);

        KeyCombination quitKey = new KeyCodeCombination(KeyCode.Q,
            KeyCombination.CONTROL_DOWN);

        registerKey(scene, openLeftKey, controller::openFile);
        registerKey(scene, openRightKey, controller::openFileRight);
        registerKey(scene, editLeftKey, controller::editFile);
        registerKey(scene, editRightKey, controller::editFileRight);
        registerKey(scene, saveLeftKey, controller::saveFile);
        registerKey(scene, saveRightKey, controller::saveFileRight);
        registerKey(scene, saveAsLeftKey, controller::saveAsFile);
        registerKey(scene, saveAsRightKey, controller::saveAsFileRight);
        registerKey(scene, refreshLeftKey, controller::refreshButtonAction);
        registerKey(scene, refreshRightKey, controller::refreshButtonActionRight);
        registerKey(scene, compareKey, controller::comparePanel);
        registerKey(scene, viewKey, controller::viewButtonAction);
        registerKey(scene, leftMergeKey, controller::copyToLeft);
        registerKey(scene, rightMergeKey, controller::copyToRight);
        registerKey(scene, leftSearchKey, controller::findButton);
        registerKey(scene, rightSearchKey, controller::findButtonRight);
        registerKey(scene, quitKey, controller::exitApp);
    }

    private void registerKey(Scene scene, KeyCombination key, Action handler) {
        scene.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if (key.match(event)) {
                handler.act();
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
