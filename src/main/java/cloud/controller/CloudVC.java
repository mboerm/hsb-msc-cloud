package cloud.controller;

import cloud.provider.ProviderFactory;
import cloud.view.dialogs.DialogComponent;
import javafx.scene.control.Alert;
import cloud.model.Design;
import cloud.model.StageManager;
import cloud.view.CloudView;

import static cloud.constants.Constants.*;

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
        initMenuHandler();
        initDesignAreaHandler();
    }

    private void initMenuHandler() {

        view.getMenuFileExit().setOnAction(actionEvent -> System.exit(0));

        view.getMenuDesignReset().setOnAction(actionEvent -> {
            view.getPaneDesignArea().getComponentsTable().getItems().removeAll();
            getDesign().clearComponents();
        });

        view.getMenuServicesAmazon().setOnAction(actionEvent -> providerFactory.getProvider("Amazon"));
        view.getMenuServicesWindows().setOnAction(actionEvent -> providerFactory.getProvider("Windows"));
        view.getMenuServicesGoogle().setOnAction(actionEvent -> providerFactory.getProvider("Google"));

        view.getMenuHelpAbout().setOnAction(actionEvent -> showAboutDialog());
    }

    private void initDesignAreaHandler() {
        view.getPaneDesignArea().getComponentsTable().getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                getDesign().setSelectedComponent(newSelection);
                view.getPaneDesignArea().getComponentsTable().getSelectionModel().clearSelection();
            }
        });

        view.getPaneDesignArea().getControlAdd().setOnAction(actionEvent -> {
            DialogComponent dialogAdd = new DialogComponent();
        });
        view.getPaneDesignArea().getControlRemove().setOnAction(actionEvent -> {
            view.getPaneDesignArea().getComponentsTable().getItems().remove(getDesign().getSelectedComponent());
        });
        view.getPaneDesignArea().getControlEdit().setOnAction(actionEvent -> {});
    }

    private void initDesignPropertyHandler() {
        view.getPaneDesignProperties().getUsagePeriodField().textProperty().addListener((obs, oldValue, newValue) ->
            getDesign().setUsagePeriod(newValue));

        view.getPaneDesignProperties().getPrimaryRegionComboBox().getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
            getDesign().setPrimaryRegion(newValue.toString());
        });

        view.getPaneDesignProperties().getNumOfInstancesSpinner().getEditor().textProperty().addListener((obs, oldValue, newValue) -> {
            getDesign().setNumOfInstances(newValue);
        });

        view.getPaneDesignProperties().getNumOfRequestsSpinner().getEditor().textProperty().addListener((obs, oldValue, newValue) -> {
            getDesign().setNumOfRequests(newValue);
        });

        view.getPaneDesignProperties().getPeriodOfRequestsComboBox().getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
            getDesign().setPeriodOfRequests(newValue.toString());
        });

        view.getPaneDesignProperties().getNumOfCapacitySpinner().getEditor().textProperty().addListener((obs, oldValue, newValue) -> {
            getDesign().setNumOfCapacity(newValue);
        });

        view.getPaneDesignProperties().getPeriodOfCapacityComboBox().getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
            getDesign().setPeriodOfCapacity(newValue.toString());
        });
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
