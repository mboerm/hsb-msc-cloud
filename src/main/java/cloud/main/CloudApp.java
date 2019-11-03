package cloud.main;

import cloud.controller.CloudVC;
import cloud.model.Session;
import cloud.model.StageManager;
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

        StageManager.getInstance().setPrimaryStage(primaryStage);

        Session session = new Session();

        CloudVC vc = new CloudVC(session);
        vc.show();
    }
}
