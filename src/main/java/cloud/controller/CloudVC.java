package cloud.controller;

import cloud.ui.CloudUI;
import javafx.stage.Stage;

public class CloudVC {
    private Stage primaryStage = null;
    private CloudUI ui;

    public CloudVC(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.ui = new CloudUI();
    }

    public void show(){
        ui.show(primaryStage);
    }
}
