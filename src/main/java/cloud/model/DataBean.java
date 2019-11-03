package cloud.model;

import javafx.stage.Stage;

public class DataBean {
    private Session cloudSession;

    public DataBean() {
        this.cloudSession = new Session();
    }

    public Session getSession() {
        return this.cloudSession;
    }
}