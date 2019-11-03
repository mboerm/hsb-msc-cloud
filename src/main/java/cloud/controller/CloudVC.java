package cloud.controller;

import cloud.model.Session;
import cloud.model.StageManager;
import cloud.view.CloudView;
import javafx.stage.FileChooser;

import java.io.File;

public class CloudVC {

    // Model
    private Session session = null;

    // View
    private CloudView view;

    public CloudVC(Session session) {
        this.session = session;
        this.view = new CloudView();

        view.getMenuFile().getItems().get(0).setOnAction(actionEvent -> {
        });

        view.getMenuFile().getItems().get(1).setOnAction(actionEvent -> {
            FileChooser fileChooser = new FileChooser();
            File selectedFile = fileChooser.showOpenDialog(StageManager.getInstance().getPrimaryStage());
        });

        view.getMenuFile().getItems().get(2).setOnAction(actionEvent -> {
            FileChooser fileChooser = new FileChooser();
            File savedFile = fileChooser.showSaveDialog(StageManager.getInstance().getPrimaryStage());
        });

        view.getMenuFile().getItems().get(3).setOnAction(actionEvent -> System.exit(0));
    }

    public void newSession() {
        this.session = new Session();
    }
    public Session getSession() {
        return this.session;
    }

    public void show() {
        view.show(StageManager.getInstance().getPrimaryStage());
    }
}
