package cloud.view.dialogs;

import cloud.model.components.*;
import cloud.view.components.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.layout.Pane;

public class DialogAddComponentC {

    private Component addedComponent;
    private String addedResponse;
    private PaneComputeComponent computePane;
    private PaneDatabaseComponent databasePane;
    private PaneStorageComponent storagePane;
    private PaneAnalyticComponent analyticPane;
    private PaneNetworkComponent networkPane;
    private PaneIntegrationComponent integrationPane;
    private PaneMonitoringComponent monitoringPane;

    public DialogAddComponentC() {
        DialogAddComponent<String> dialogAdd = new DialogAddComponent<>();

        dialogAdd.getComponentsBox().getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldItem, String newItem) {
                dialogAdd.toggleOKButton(false);
                dialogAdd.getComponentsDialogPane().setCenter(switchComponentPanes(newItem));
                dialogAdd.getDialogPane().getScene().getWindow().sizeToScene();
            }
        });

        dialogAdd.showAndWait().ifPresent(response -> {
            String responseValue = response.toString().substring(response.toString().lastIndexOf(':') + 2, response.toString().length() - 1);
            addedResponse = responseValue;
            addedComponent = createComponent(responseValue);
        });
    }

    public Component getAddedComponent() {
        return addedComponent;
    }

    public String getAddedResponse() {
        return addedResponse;
    }

    private PaneComponent switchComponentPanes(String item) {
        switch (item) {
            case "Compute":
                computePane = new PaneComputeComponent();
                return computePane;
            case "Database":
                databasePane = new PaneDatabaseComponent();
                return databasePane;
            case "Storage":
                storagePane = new PaneStorageComponent();
                return storagePane;
            case "Analytic":
                analyticPane = new PaneAnalyticComponent();
                return analyticPane;
            case "Network":
                networkPane = new PaneNetworkComponent();
                return networkPane;
            case "Integration":
                integrationPane = new PaneIntegrationComponent();
                return integrationPane;
            case "Monitoring":
                monitoringPane = new PaneMonitoringComponent();
                return monitoringPane;
            default:
                return null;
        }
    }

    private Component createComponent(String choice) {
        switch (choice) {
            case "Compute":
                return new ComputeComponent(
                        computePane.getName(),
                        computePane.getComputeType()
                );
            case "Database":
                return new DatabaseComponent();
            case "Storage":
                return new StorageComponent();
            case "Analytic":
                return new AnalyticComponent();
            case "Network":
                return new NetworkComponent();
            case "Integration":
                return new IntegrationComponent();
            case "Monitoring":
                return new MonitoringComponent(
                        monitoringPane.getName(),
                        monitoringPane.getMetrics(),
                        monitoringPane.getRequests(),
                        monitoringPane.getData(),
                        monitoringPane.getEvents(),
                        monitoringPane.getLoggingState()
                );
            default:
                return null;
        }
    }
}
