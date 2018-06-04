import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.input.KeyEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;




public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {

        try {
            // loading the fxml
            Pane pane = FXMLLoader.load(getClass().getResource(
                "fxtemplate.fxml"));

            // creating and initializing the scene.
            Scene scene = new Scene(pane);
            primaryStage.setScene(scene);

            // setting the height and width of stage.
            primaryStage.setWidth(800);
            primaryStage.setHeight(650);
            primaryStage.setResizable(false);

            // setting the App title
            primaryStage.setTitle("SimpleMerge");
            primaryStage.getIcons().add(
                new Image(Main.class.getResourceAsStream("icon.png")));
            scene.getStylesheets().add(
                getClass().getResource("fxtemplate.css").toExternalForm());

            // display the stage
            primaryStage.show();


        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    public static void main(String[] args) {
        launch(args);
    }
}

//import io.sentry.Sentry;
//
//public class Main {
//    public static int returnOne() {
//        return 1;
//    }
//
//    public static void main(String[] args) {
//        String sentryDSN =
//            "https://dfa5eedd277d47658bbc691394bef7e8@sentry.io/1201571";
//        Sentry.init(sentryDSN);
//
//        try {
//            System.out.println("Hello, world!");
//        } catch (Exception exception) {
//            Sentry.capture(exception);
//        }
//    }
//}
