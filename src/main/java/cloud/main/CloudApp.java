package cloud.main;

import cloud.model.DataBean;
import cloud.controller.CloudVC;
import javafx.application.Application;
import javafx.stage.Stage;

public class CloudApp extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // session scope /application scope Beans initialisieren!
        // muss von Controller zu Controller weitergegeben werden
        DataBean dataBean = new DataBean(primaryStage);

        CloudVC cloudVC = new CloudVC(dataBean);
        cloudVC.show();
    }
}
