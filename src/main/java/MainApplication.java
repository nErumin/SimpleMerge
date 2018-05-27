import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {

        try {
            // loading the fxml
            Pane pane = FXMLLoader.load(getClass().getResource(
                "First.fxml"));

            // creating and initializing the scene.
            Scene scene = new Scene(pane);
            primaryStage.setScene(scene);

            // setting the height and width of stage.
            primaryStage.setWidth(1024);
            primaryStage.setHeight(768);

            // setting the App title
            primaryStage.setTitle("Simplemerge");
            scene.getStylesheets().add(getClass().getResource("notepad.css").toExternalForm());


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
