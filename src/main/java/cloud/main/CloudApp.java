package cloud.main;

import cloud.ui.CloudUI;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.Locale;

public class CloudApp extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Locale.setDefault(new Locale("en"));
        CloudUI ui = new CloudUI();
        ui.show(primaryStage);
    }
}
