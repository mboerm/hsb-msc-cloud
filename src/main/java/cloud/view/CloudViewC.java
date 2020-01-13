package cloud.view;

import cloud.configuration.Config;
import cloud.model.StageManager;
import cloud.model.components.Component;
import cloud.model.provider.ProviderFactory;
import cloud.view.dialogs.DialogAddComponentC;
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
            design.clearComponents();
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
        view.getPaneDesignArea().getComponentsTable().setItems(design.getComponentsList());

        ObservableList<Component> selectedItems = view.getPaneDesignArea().getComponentsTable().getSelectionModel().getSelectedItems();
        selectedItems.addListener((ListChangeListener<Component>) change -> {
            int selCompIdx = view.getPaneDesignArea().getComponentsTable().getSelectionModel().getSelectedIndex();
            design.setSelectedComponent(selCompIdx);
        });
    }

    private void initDesignControlsHandler() {
        view.getPaneDesignControls().getControlAdd().setOnAction(actionEvent -> {
            DialogAddComponentC dialogAddComponentController = new DialogAddComponentC();
            view.setTaskBarText("Added " + dialogAddComponentController.getAddedResponse() + " component");

            /* get created component */
            Component createdComponent = dialogAddComponentController.getAddedComponent();

            /* add component to components list */
            design.addComponent(createdComponent);
        });

        view.getPaneDesignControls().getControlRemove().setOnAction(actionEvent -> {
            /* remove selected component from components list */
            Component selectedComponent = view.getPaneDesignArea().getComponentsTable().getItems().get(design.getSelectedComponent());
            design.removeComponent(selectedComponent);

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
