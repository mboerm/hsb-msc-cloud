package cloud.view;

import cloud.configuration.Config;
import cloud.main.StageManager;
import cloud.report.PDFReport;
import cloud.model.services.Service;
import cloud.model.design.*;
import cloud.model.provider.ProviderFactory;

import cloud.view.dialogs.CostDialogC;
import cloud.view.dialogs.ServiceDialogC;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseButton;

import static cloud.configuration.Constants.*;

public class DesignViewC {

    // View
    private DesignView view;

    // Model
    private Design design;
    private ProviderFactory providerFactory;
    private int selectedServiceID;

    // Controller
    private ServiceDialogC dialogServiceC;
    private CostDialogC costReportC;

    public DesignViewC() {
        this.view = new DesignView();
        this.design = DesignManager.getInstance().getDesign();
        this.providerFactory = new ProviderFactory();
        this.dialogServiceC = new ServiceDialogC();
        this.costReportC = new CostDialogC();

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
            design.clearServicesCosts();
            view.getMenuDesignMatch().setDisable(true);
            view.getMenuDesignCalculate().setDisable(true);
            view.getMenuDesignOptimize().setDisable(true);
        });

        view.getMenuDesignMatch().setOnAction(actionEvent -> {
            design.matchServices();
            view.getPaneDesignArea().getServicesTable().setItems(design.getServicesList());
            view.getPaneDesignArea().getServicesTable().refresh();
            view.getMenuDesignCalculate().setDisable(false);
        });

        view.getMenuDesignCalculate().setOnAction(actionEvent -> {
            design.clearServicesCosts();
            design.getProvider().calculateCosts();
            if (costReportC.showCostReport()) {
                new PDFReport();
            }
        });

        view.getMenuHelpAbout().setOnAction(actionEvent -> showAboutDialog());
    }

    private void initDesignPropertyHandler() {
        view.getPaneDesignProperties().getProviderBox().getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
            try {
                design.setProvider(providerFactory.getProvider(newValue));
                view.getMenuDesignMatch().setDisable(false);
            } catch (IllegalArgumentException e) {
                System.err.println("Invalid provider!");
            }
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
            selectedServiceID = view.getPaneDesignArea().getServicesTable().getSelectionModel().getSelectedIndex();
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
            dialogServiceC.resetServiceData();
        });

        view.getPaneDesignControls().getControlRemove().setOnAction(actionEvent -> {
            /* remove selected service from services list */
            Service selectedService = view.getPaneDesignArea().getServicesTable().getItems().get(selectedServiceID);
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
