package cloud.view.dialogs;

import cloud.configuration.Config;
import cloud.model.components.*;
import cloud.view.components.*;

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

        dialogAdd.getComponentsBox().getSelectionModel().selectedItemProperty().addListener((ov, oldItem, newItem) -> {
            dialogAdd.toggleOKButton(false);
            dialogAdd.getComponentsDialogPane().setCenter(switchComponentPanes(newItem));
            dialogAdd.getDialogPane().getScene().getWindow().sizeToScene();
        });

        dialogAdd.showAndWait().ifPresent(response -> {
            String responseValue = response.substring(response.lastIndexOf(':') + 2, response.length() - 1);
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
                if (integrationPane.getType().equals(Config.getInstance().getConfigValues("integration-type")[0])) {
                    return new IntegrationComponent(
                            integrationPane.getName(),
                            integrationPane.getType(),
                            integrationPane.getData(),
                            integrationPane.getRequests(),
                            integrationPane.getMessages()
                    );
                } else {
                    return new IntegrationComponent(
                            integrationPane.getName(),
                            integrationPane.getType(),
                            integrationPane.getData(),
                            integrationPane.getRequests()
                    );
                }

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
