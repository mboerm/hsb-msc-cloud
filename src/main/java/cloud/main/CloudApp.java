package cloud.main;

import cloud.controller.CloudVC;
import cloud.model.DataBean;
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

        // session scope /application scope Beans initialisieren!
        // muss von Controller zu Controller weitergegeben werden
        DataBean dataBean = new DataBean(primaryStage);

        CloudVC vc = new CloudVC(dataBean);
        vc.show();
    }
}
