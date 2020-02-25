package cloud.view;

import cloud.configuration.Config;
import cloud.main.StageManager;
import cloud.report.IReport;
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
    private int selectedServiceID;
    private static ProviderFactory providerFactory;
    private static IReport report;

    // Controller
    private ServiceDialogC dialogServiceC;
    private CostDialogC costReportC;

    public DesignViewC() {
        this.view = new DesignView();
        providerFactory = new ProviderFactory();
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

        view.getMenuDesignMatch().setOnAction(actionEvent -> updateServiceMatches());

        view.getMenuDesignCalculate().setOnAction(actionEvent -> {
            DesignManager.getInstance().getDesign().clearServicesCosts();
            DesignManager.getInstance().getDesign().getProvider().calculateStaticCosts();
            if (costReportC.showCostReport()) {
                report = new PDFReport();
                report.createReport();
            }
        });

        view.getMenuDesignScale().setOnAction(actionEvent -> DesignManager.getInstance().getDesign().scaleCosts());

        view.getMenuDesignOptimize().setOnAction(actionEvent -> DesignManager.getInstance().getDesign().getProvider().optimizeCosts());

        view.getMenuDesignCompare().setOnAction(actionEvent -> DesignManager.getInstance().getDesign().compareCosts());

        view.getMenuDesignReset().setOnAction(actionEvent -> {
            view.getPaneDesignArea().getServicesTable().getItems().removeAll();
            DesignManager.getInstance().getDesign().clearServicesList();
            DesignManager.getInstance().getDesign().clearServicesCosts();
        });

        view.getMenuHelpAbout().setOnAction(actionEvent -> showAboutDialog());
    }

    private void initDesignPropertyHandler() {
        view.getPaneDesignProperties().getProviderBox().getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
            try {
                DesignManager.getInstance().getDesign().setProvider(providerFactory.getProvider(newValue));
            } catch (IllegalArgumentException e) {
                System.err.println("Invalid provider!");
            }
        });

        view.getPaneDesignProperties().getUsagePeriodField().textProperty().addListener((obs, oldValue, newValue) ->
            DesignManager.getInstance().getDesign().setUsagePeriod(newValue));

        view.getPaneDesignProperties().getPrimaryLocationBox().getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) ->
            DesignManager.getInstance().getDesign().setPrimaryRegion(newValue));

        view.getPaneDesignProperties().getNumOfInstancesSpinner().valueProperty().addListener((obs, oldValue, newValue) ->
            DesignManager.getInstance().getDesign().setNumOfInstances(newValue));

        view.getPaneDesignProperties().getNumOfRequestsSpinner().valueProperty().addListener((obs, oldValue, newValue) ->
            DesignManager.getInstance().getDesign().setNumOfRequests(newValue));

        view.getPaneDesignProperties().getPeriodOfRequestsBox().getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) ->
            DesignManager.getInstance().getDesign().setPeriodOfRequests(newValue));

        view.getPaneDesignProperties().getNumOfCapacitySpinner().valueProperty().addListener((obs, oldValue, newValue) ->
            DesignManager.getInstance().getDesign().setNumOfCapacity(newValue));

        view.getPaneDesignProperties().getPeriodOfCapacityBox().getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) ->
            DesignManager.getInstance().getDesign().setPeriodOfCapacity(newValue));
    }

    private void initDesignAreaHandler() {
        view.getPaneDesignArea().getServicesTable().setItems(DesignManager.getInstance().getDesign().getServicesList());

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
                        DesignManager.getInstance().getDesign().replaceService(selectedService, editedService);
                        updateServiceMatches();
                    }
                }
                selectedServiceID = view.getPaneDesignArea().getServicesTable().getItems().indexOf(selectedService);
            }
        });
    }

    private void initDesignControlsHandler() {
        view.getPaneDesignControls().getControlRemove().setDisable(true);

        view.getPaneDesignControls().getControlAdd().setOnAction(actionEvent -> {
            dialogServiceC.newDialog();
            if (dialogServiceC.showDialog()) {
                /* add created service to services list */
                DesignManager.getInstance().getDesign().addService(dialogServiceC.getServiceData());
            }
            dialogServiceC.resetServiceData();
        });

        view.getPaneDesignControls().getControlRemove().setOnAction(actionEvent -> {
            /* remove selected service from services list */
            Service selectedService = view.getPaneDesignArea().getServicesTable().getItems().get(selectedServiceID);
            DesignManager.getInstance().getDesign().removeService(selectedService);
            /* clear selection */
            view.getPaneDesignArea().getServicesTable().getSelectionModel().clearSelection();
            view.getPaneDesignControls().getControlRemove().setDisable(true);
        });
    }

    private void updateServiceMatches() {
        DesignManager.getInstance().getDesign().matchServices();
        view.getPaneDesignArea().getServicesTable().setItems(DesignManager.getInstance().getDesign().getServicesList());
        view.getPaneDesignArea().getServicesTable().refresh();
    }

    private void showAboutDialog() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About " + APP_TITLE);
        alert.setHeaderText(null);
        alert.setContentText(Config.getInstance().getConfigValuesAsArray("about-text")[0]);
        alert.showAndWait();
    }
}
