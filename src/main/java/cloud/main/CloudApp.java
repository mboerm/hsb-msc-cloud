package cloud.main;

import cloud.controller.CloudVC;
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
        CloudVC cloudVC = new CloudVC(primaryStage);
        cloudVC.show();
    }
}
