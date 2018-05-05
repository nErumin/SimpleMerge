import io.sentry.Sentry;

public class Main {
    public static int returnOne() {
        return 1;
    }

    public static void main(String[] args) {
        String sentryDSN =
            "https://dfa5eedd277d47658bbc691394bef7e8@sentry.io/1201571";
        Sentry.init(sentryDSN);

        try {
            System.out.println("Hello, world!");
        } catch (Exception exception) {
            Sentry.capture(exception);
        }
    }
}
