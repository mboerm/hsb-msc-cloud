package cloud.view;

import cloud.configuration.Config;
import cloud.model.StageManager;
import cloud.model.provider.ProviderFactory;
import cloud.view.dialogs.DialogAddComponentC;
import javafx.scene.control.Alert;
import cloud.model.Design;

import static cloud.configuration.Constants.*;

public class CloudViewC {

    // Model
    private Design design = null;
    private ProviderFactory providerFactory = null;

    // View
    private CloudView view;

    public CloudViewC(Design design) {
        this.design = design;
        this.providerFactory = new ProviderFactory();
        this.view = new CloudView();
        initMenuHandler();
        initDesignPropertyHandler();
        initDesignAreaHandler();
        initDesignControlsHandler();
    }

    public void show() {
        view.show(StageManager.getInstance().getPrimaryStage());
    }

    private void initMenuHandler() {
        view.getMenuFileExit().setOnAction(actionEvent -> System.exit(0));

        view.getMenuDesignReset().setOnAction(actionEvent -> {
            view.getPaneDesignArea().getComponentsTable().getItems().removeAll();
            getDesign().clearComponents();
        });

        view.getMenuProviderAmazon().setOnAction(actionEvent -> providerFactory.getProvider("Amazon"));
        view.getMenuProviderWindows().setOnAction(actionEvent -> providerFactory.getProvider("Windows"));
        view.getMenuProviderGoogle().setOnAction(actionEvent -> providerFactory.getProvider("Google"));

        view.getMenuHelpAbout().setOnAction(actionEvent -> showAboutDialog());
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

    private void initDesignAreaHandler() {
        view.getPaneDesignArea().getComponentsTable().getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
//                getDesign().setSelectedComponent(newSelection);
                view.getPaneDesignArea().getComponentsTable().getSelectionModel().clearSelection();
            }
        });
    }

    private void initDesignControlsHandler() {
        view.getPaneDesignControls().getControlAdd().setOnAction(actionEvent -> {
            DialogAddComponentC dialogAddComponentController = new DialogAddComponentC();
            view.setTaskBarText("Added " + dialogAddComponentController.getAddedResponse() + " component");
            design.addComponent(dialogAddComponentController.getAddedComponent());
        });

        view.getPaneDesignControls().getControlRemove().setOnAction(actionEvent -> {
            view.getPaneDesignArea().getComponentsTable().getItems().remove(getDesign().getSelectedComponent());
        });

        view.getPaneDesignControls().getControlEdit().setOnAction(actionEvent -> {});
    }

    private Design getDesign() {
        return this.design;
    }

    private void showAboutDialog() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About " + APP_TITLE);
        alert.setHeaderText(null);
        alert.setContentText(Config.getInstance().getConfigValues("about-text")[0]);
        alert.showAndWait();
    }
}
