package cloud.model;

import javafx.stage.Stage;

public class DataBean {
    private Stage primaryStage = null;

    private Session cloudSession;

    public DataBean(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.cloudSession = new Session();
    }

    public Session getSession() {
        return this.cloudSession;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }
}