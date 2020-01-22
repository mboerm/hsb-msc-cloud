package cloud.view;

import cloud.configuration.Config;
import cloud.model.StageManager;
import cloud.model.design.DesignManager;
import cloud.model.services.Service;
import cloud.model.provider.ProviderFactory;
import cloud.view.dialogs.DialogServiceC;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import cloud.model.design.Design;
import javafx.scene.input.MouseButton;

import static cloud.configuration.Constants.*;

public class CloudViewC {

    // View
    private CloudView view;

    // Model
    private Design design;
    private ProviderFactory providerFactory;

    // Controller
    private DialogServiceC dialogServiceC;

    public CloudViewC() {
        this.view = new CloudView();
        this.design = DesignManager.getInstance().getDesign();
        this.providerFactory = new ProviderFactory();
        this.dialogServiceC = new DialogServiceC();

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
            view.getPaneDesignArea().getServicesTable().getItems().removeAll();
            design.clearServicesList();
        });

        view.getMenuDesignMatch().setOnAction(actionEvent -> {
            design.setMatchedServicesForDesign();
            view.getPaneDesignArea().getServicesTable().setItems(design.getServicesList());
            view.getPaneDesignArea().getServicesTable().refresh();
            view.getMenuDesignCalculate().setDisable(false);
        });

        view.getMenuDesignCalculate().setOnAction(actionEvent -> {});

        view.getMenuHelpAbout().setOnAction(actionEvent -> showAboutDialog());
    }

    private void initDesignPropertyHandler() {
        view.getPaneDesignProperties().getProviderBox().getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
            design.setProvider(providerFactory.getProvider(newValue));
            view.getMenuDesignMatch().setDisable(false);
        });

        view.getPaneDesignProperties().getUsagePeriodField().textProperty().addListener((obs, oldValue, newValue) ->
            design.setUsagePeriod(newValue));

        view.getPaneDesignProperties().getPrimaryRegionBox().getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) ->
            design.setPrimaryRegion(newValue));

        view.getPaneDesignProperties().getNumOfInstancesSpinner().valueProperty().addListener((obs, oldValue, newValue) ->
            design.setNumOfInstances(newValue));

        view.getPaneDesignProperties().getNumOfRequestsSpinner().valueProperty().addListener((obs, oldValue, newValue) ->
            design.setNumOfRequests(newValue));

        view.getPaneDesignProperties().getPeriodOfRequestsBox().getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) ->
            design.setPeriodOfRequests(newValue));

        view.getPaneDesignProperties().getNumOfCapacitySpinner().valueProperty().addListener((obs, oldValue, newValue) ->
            design.setNumOfCapacity(newValue));

        view.getPaneDesignProperties().getPeriodOfCapacityBox().getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) ->
            design.setPeriodOfCapacity(newValue));
    }

    private void initDesignAreaHandler() {
        view.getPaneDesignArea().getServicesTable().setItems(design.getServicesList());

        ObservableList<Service> selectedItems = view.getPaneDesignArea().getServicesTable().getSelectionModel().getSelectedItems();
        selectedItems.addListener((ListChangeListener<Service>) change -> {
            design.setSelectedService(view.getPaneDesignArea().getServicesTable().getSelectionModel().getSelectedIndex());
            view.getPaneDesignControls().getControlRemove().setDisable(false);
        });

        view.getPaneDesignArea().getServicesTable().setOnMousePressed(mouseEvent -> {
            if (mouseEvent.getButton().equals(MouseButton.PRIMARY) && mouseEvent.getClickCount() == 2) {
                // get selected service item
                Service selectedService = view.getPaneDesignArea().getServicesTable().getSelectionModel().getSelectedItem();

                // if selected service item really exists
                if (selectedService != null) {
                    // create new dialog
                    dialogServiceC.newDialog();
                    // show dialog with preset by service
                    if (dialogServiceC.showPresetDialog(selectedService)) {
                        Service editedService = dialogServiceC.getServiceData();
                        design.replaceService(selectedService, editedService);
                    }
                }
            }
        });
    }

    private void initDesignControlsHandler() {
        view.getPaneDesignControls().getControlRemove().setDisable(true);

        view.getPaneDesignControls().getControlAdd().setOnAction(actionEvent -> {
            dialogServiceC.newDialog();
            if (dialogServiceC.showDialog()) {
                /* add created service to services list */
                design.addService(dialogServiceC.getServiceData());
            }
        });

        view.getPaneDesignControls().getControlRemove().setOnAction(actionEvent -> {
            /* remove selected service from services list */
            Service selectedService = view.getPaneDesignArea().getServicesTable().getItems().get(design.getSelectedService());
            design.removeService(selectedService);
            /* clear selection */
            view.getPaneDesignArea().getServicesTable().getSelectionModel().clearSelection();
            view.getPaneDesignControls().getControlRemove().setDisable(true);
        });
    }

    private void showAboutDialog() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About " + APP_TITLE);
        alert.setHeaderText(null);
        alert.setContentText(Config.getInstance().getConfigValuesAsArray("about-text")[0]);
        alert.showAndWait();
    }
}
