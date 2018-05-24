import io.sentry.Sentry;
import javafx.application.Application;
import javafx.stage.Stage;

public class MainApplication extends Application {

    public static void main(String[] args) {
        final String sentryDSN =
            "https://dfa5eedd277d47658bbc691394bef7e8@sentry.io/1201571";
        Sentry.init(sentryDSN);

        try {
            launch(args);
        } catch (Exception exception) {
            Sentry.capture(exception);
        }
    }

    @Override
    public void start(Stage primaryStage) {

    }
}
