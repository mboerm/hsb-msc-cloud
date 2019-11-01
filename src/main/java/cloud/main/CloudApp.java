package cloud.main;

import cloud.controller.CloudVC;
import javafx.application.Application;
import javafx.stage.Stage;

public class CloudApp extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        CloudVC cloudVC = new CloudVC(primaryStage);
        cloudVC.show();
    }
}
