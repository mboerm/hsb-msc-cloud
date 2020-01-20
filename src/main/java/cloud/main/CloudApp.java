package cloud.main;

import cloud.model.design.DesignManager;
import cloud.model.design.Design;
import cloud.view.CloudViewC;
import javafx.application.Application;
import javafx.stage.Stage;
import cloud.model.*;

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

        // create new design in design manager
        DesignManager.getInstance().setDesign(new Design());

        // create view controller and show ui
        CloudViewC vc = new CloudViewC();
        vc.show();
    }
}
