package cloud.controller;

import cloud.provider.ProviderFactory;
import cloud.view.dialogs.DialogAddComponent;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import cloud.model.Design;
import cloud.model.StageManager;
import cloud.view.CloudView;

import static cloud.constants.Consts.*;

import java.io.File;

public class CloudVC {

    // Model
    private Design design = null;
    private ProviderFactory providerFactory = null;

    // View
    private CloudView view;

    public CloudVC(Design design) {
        this.design = design;
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
            view.getPaneDesignArea().getComponentsTable().getItems().removeAll();
            getDesign().clearComponents();
        });

        view.getMenuServicesAmazon().setOnAction(actionEvent -> providerFactory.getProvider("Amazon"));
        view.getMenuServicesWindows().setOnAction(actionEvent -> providerFactory.getProvider("Windows"));
        view.getMenuServicesGoogle().setOnAction(actionEvent -> providerFactory.getProvider("Google"));

        view.getMenuHelpAbout().setOnAction(actionEvent -> showAboutDialog());

        view.getPaneDesignArea().getComponentsTable().getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                getDesign().setSelectedComponent(newSelection);
                view.getPaneDesignArea().getComponentsTable().getSelectionModel().clearSelection();
            }
        });

        view.getPaneDesignArea().getControlAdd().setOnAction(actionEvent -> {
            DialogAddComponent dialogAdd = new DialogAddComponent();
        });
        view.getPaneDesignArea().getControlRemove().setOnAction(actionEvent -> {
            view.getPaneDesignArea().getComponentsTable().getItems().remove(getDesign().getSelectedComponent());
        });
        view.getPaneDesignArea().getControlEdit().setOnAction(actionEvent -> {});
    }

    private void newSession() {
        this.design = new Design();
    }
    private Design getDesign() {
        return this.design;
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
