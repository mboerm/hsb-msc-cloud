package cloud.view;

import cloud.configuration.Config;
import cloud.model.StageManager;
import cloud.model.services.Service;
import cloud.model.provider.ProviderFactory;
import cloud.view.dialogs.DialogAddServiceC;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import cloud.model.Design;

import static cloud.configuration.Constants.*;

public class CloudViewC {

    // Model
    private Design design;
    private ProviderFactory providerFactory;

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
            design.clearServicesList();
        });

        view.getMenuProviderAmazon().setOnAction(actionEvent -> providerFactory.getProvider("Amazon"));
        view.getMenuProviderWindows().setOnAction(actionEvent -> providerFactory.getProvider("Windows"));
        view.getMenuProviderGoogle().setOnAction(actionEvent -> providerFactory.getProvider("Google"));

        view.getMenuHelpAbout().setOnAction(actionEvent -> showAboutDialog());
    }

    private void initDesignPropertyHandler() {
        view.getPaneDesignProperties().getUsagePeriodField().textProperty().addListener((obs, oldValue, newValue) ->
            design.setUsagePeriod(newValue));

        view.getPaneDesignProperties().getPrimaryRegionComboBox().getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
            design.setPrimaryRegion(newValue.toString());
        });

        view.getPaneDesignProperties().getNumOfInstancesSpinner().getEditor().textProperty().addListener((obs, oldValue, newValue) -> {
            design.setNumOfInstances(newValue);
        });

        view.getPaneDesignProperties().getNumOfRequestsSpinner().getEditor().textProperty().addListener((obs, oldValue, newValue) -> {
            design.setNumOfRequests(newValue);
        });

        view.getPaneDesignProperties().getPeriodOfRequestsComboBox().getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
            design.setPeriodOfRequests(newValue.toString());
        });

        view.getPaneDesignProperties().getNumOfCapacitySpinner().getEditor().textProperty().addListener((obs, oldValue, newValue) -> {
            design.setNumOfCapacity(newValue);
        });

        view.getPaneDesignProperties().getPeriodOfCapacityComboBox().getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
            design.setPeriodOfCapacity(newValue.toString());
        });
    }

    private void initDesignAreaHandler() {
        view.getPaneDesignArea().getComponentsTable().setItems(design.getServicesList());

        ObservableList<Service> selectedItems = view.getPaneDesignArea().getComponentsTable().getSelectionModel().getSelectedItems();
        selectedItems.addListener((ListChangeListener<Service>) change -> {
            int selCompIdx = view.getPaneDesignArea().getComponentsTable().getSelectionModel().getSelectedIndex();
            design.setSelectedService(selCompIdx);
        });
    }

    private void initDesignControlsHandler() {
        view.getPaneDesignControls().getControlAdd().setOnAction(actionEvent -> {
            DialogAddServiceC dialogAddComponentController = new DialogAddServiceC();
            view.setTaskBarText("Added " + dialogAddComponentController.getAddedResponse() + " component");

            /* get created component */
            Service createdComponent = dialogAddComponentController.getAddedService();

            /* add component to components list */
            design.addService(createdComponent);
        });

        view.getPaneDesignControls().getControlRemove().setOnAction(actionEvent -> {
            /* remove selected component from components list */
            Service selectedComponent = view.getPaneDesignArea().getComponentsTable().getItems().get(design.getSelectedService());
            design.removeService(selectedComponent);

            /* clear selection */
            view.getPaneDesignArea().getComponentsTable().getSelectionModel().clearSelection();
        });

        view.getPaneDesignControls().getControlEdit().setOnAction(actionEvent -> {});
    }

    private void showAboutDialog() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About " + APP_TITLE);
        alert.setHeaderText(null);
        alert.setContentText(Config.getInstance().getConfigValues("about-text")[0]);
        alert.showAndWait();
    }
}
