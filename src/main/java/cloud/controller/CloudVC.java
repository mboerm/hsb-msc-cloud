package cloud.controller;

import cloud.provider.ProviderFactory;
import cloud.view.dialogs.DialogAddComponent;
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
    private ProviderFactory providerFactory = null;

    // View
    private CloudView view;

    public CloudVC(Session session) {
        this.session = session;
        this.providerFactory = new ProviderFactory();
        this.view = new CloudView();
        initViewHandler();
    }

    private void initViewHandler() {
        view.getMenuFileNew().setOnAction(actionEvent -> newSession());

        view.getMenuFileOpen().setOnAction(actionEvent -> {
            FileChooser fileChooser = new FileChooser();
            File selectedFile = fileChooser.showOpenDialog(StageManager.getInstance().getPrimaryStage());
        });

        view.getMenuFileSave().setOnAction(actionEvent -> {
            FileChooser fileChooser = new FileChooser();
            File savedFile = fileChooser.showSaveDialog(StageManager.getInstance().getPrimaryStage());
        });

        view.getMenuFileExit().setOnAction(actionEvent -> System.exit(0));

        view.getMenuDesignReset().setOnAction(actionEvent -> {
            view.getDesignArea().getComponentsTable().getItems().removeAll();
            getSession().clearComponents();
        });

        view.getMenuServicesAmazon().setOnAction(actionEvent -> providerFactory.getProvider("Amazon"));
        view.getMenuServicesWindows().setOnAction(actionEvent -> providerFactory.getProvider("Windows"));
        view.getMenuServicesGoogle().setOnAction(actionEvent -> providerFactory.getProvider("Google"));

        view.getMenuHelpAbout().setOnAction(actionEvent -> showAboutDialog());

        view.getDesignArea().getComponentsTable().getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                getSession().setSelectedComponent(newSelection);
                view.getDesignArea().getComponentsTable().getSelectionModel().clearSelection();
            }
        });

        view.getDesignArea().getControlAdd().setOnAction(actionEvent -> {
            DialogAddComponent dialogAdd = new DialogAddComponent();
        });
        view.getDesignArea().getControlRemove().setOnAction(actionEvent -> {
            view.getDesignArea().getComponentsTable().getItems().remove(getSession().getSelectedComponent());
        });
        view.getDesignArea().getControlEdit().setOnAction(actionEvent -> {});
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
