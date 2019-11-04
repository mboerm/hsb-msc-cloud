package cloud.controller;

import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import cloud.model.Session;
import cloud.model.StageManager;
import cloud.view.CloudView;

import static cloud.constants.Consts.*;

import java.io.File;

public class CloudVC {

    // Model
    private Session session = null;

    // View
    private CloudView view;

    public CloudVC(Session session) {
        this.session = session;
        this.view = new CloudView();
        initViewHandler();
    }

    private void initViewHandler() {
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

        view.getMenuDesign().getItems().get(2).setOnAction(actionEvent -> {

        });

        view.getMenuHelp().getItems().get(0).setOnAction(actionEvent -> showAboutDialog());
    }

    private void newSession() {
        this.session = new Session();
    }
    private Session getSession() {
        return this.session;
    }

    private void showAboutDialog() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About " + APP_TITLE);
        alert.setHeaderText(null);
        alert.setContentText(ABOUT_TEXT);
        alert.showAndWait();
    }

    public void show() {
        view.show(StageManager.getInstance().getPrimaryStage());
    }
}
