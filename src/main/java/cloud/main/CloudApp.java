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

        // set primarystage in stage manager
        StageManager.getInstance().setPrimaryStage(primaryStage);

        // create new tool session to save data
        Session session = new Session();

        // create view controller and show ui
        CloudVC vc = new CloudVC(session);
        vc.show();
    }
}
