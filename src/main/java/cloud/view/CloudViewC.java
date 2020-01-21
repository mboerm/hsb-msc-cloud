package cloud.view;

import cloud.configuration.Config;
import cloud.log.Logger;
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

    // Model
    private Design design;
    private ProviderFactory providerFactory;

    // View
    private CloudView view;

    public CloudViewC() {
        this.design = DesignManager.getInstance().getDesign();
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

        view.getPaneDesignProperties().getPrimaryRegionBox().getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
            design.setPrimaryRegion(newValue);
        });

        view.getPaneDesignProperties().getNumOfInstancesSpinner().getEditor().textProperty().addListener((obs, oldValue, newValue) -> {
            design.setNumOfInstances(newValue);
        });

        view.getPaneDesignProperties().getNumOfRequestsSpinner().getEditor().textProperty().addListener((obs, oldValue, newValue) -> {
            design.setNumOfRequests(newValue);
        });

        view.getPaneDesignProperties().getPeriodOfRequestsBox().getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
            design.setPeriodOfRequests(newValue);
        });

        view.getPaneDesignProperties().getNumOfCapacitySpinner().getEditor().textProperty().addListener((obs, oldValue, newValue) -> {
            design.setNumOfCapacity(newValue);
        });

        view.getPaneDesignProperties().getPeriodOfCapacityBox().getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
            design.setPeriodOfCapacity(newValue);
        });
    }

    private void initDesignAreaHandler() {
        view.getPaneDesignArea().getServicesTable().setItems(design.getServicesList());

        ObservableList<Service> selectedItems = view.getPaneDesignArea().getServicesTable().getSelectionModel().getSelectedItems();
        selectedItems.addListener((ListChangeListener<Service>) change -> {
            int selCompIdx = view.getPaneDesignArea().getServicesTable().getSelectionModel().getSelectedIndex();
            Logger.getInstance().debug(String.valueOf(selCompIdx));
            design.setSelectedService(selCompIdx);
            view.getPaneDesignControls().getControlRemove().setDisable(false);
        });

        view.getPaneDesignArea().getServicesTable().setOnMousePressed(mouseEvent -> {
            if (mouseEvent.getButton().equals(MouseButton.PRIMARY) && mouseEvent.getClickCount() == 2) {
                Service selService = view.getPaneDesignArea().getServicesTable().getSelectionModel().getSelectedItem();
                if (selService != null) {
                    System.out.println(selService.getName());
                }
            }
        });
    }

    private void initDesignControlsHandler() {
        view.getPaneDesignControls().getControlRemove().setDisable(true);

        view.getPaneDesignControls().getControlAdd().setOnAction(actionEvent -> {
            DialogServiceC dialogServiceC = new DialogServiceC();

            /* get created service */
            Service createdService = dialogServiceC.getServiceData();

            /* add service to services list */
            design.addService(createdService);
        });

        view.getPaneDesignControls().getControlRemove().setOnAction(actionEvent -> {
            /* remove selected service from services list */
            Service selectedService = view.getPaneDesignArea().getServicesTable().getItems().get(design.getSelectedService());
            design.removeService(selectedService);
            view.getPaneDesignControls().getControlRemove().setDisable(true);

            /* clear selection */
            view.getPaneDesignArea().getServicesTable().getSelectionModel().clearSelection();
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
