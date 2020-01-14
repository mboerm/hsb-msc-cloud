package cloud.view.dialogs;

import cloud.configuration.Config;
import cloud.model.services.*;
import cloud.view.services.*;

public class DialogAddServiceC {

    private Service addedService;
    private String addedResponse;
    private PaneComputeService computePane;
    private PaneDatabaseService databasePane;
    private PaneStorageService storagePane;
    private PaneAnalyticService analyticPane;
    private PaneNetworkService networkPane;
    private PaneIntegrationService integrationPane;
    private PaneMonitoringService monitoringPane;

    public DialogAddServiceC() {
        DialogAddService<String> dialogAdd = new DialogAddService<>();

        dialogAdd.getServiceTypeBox().getSelectionModel().selectedItemProperty().addListener((ov, oldItem, newItem) -> {
            dialogAdd.toggleOKButton(false);
            dialogAdd.getServiceDialogPane().setCenter(switchServicePanes(newItem));
            dialogAdd.getDialogPane().getScene().getWindow().sizeToScene();
        });

        dialogAdd.showAndWait().ifPresent(response -> {
            String responseValue = response.substring(response.lastIndexOf(':') + 2, response.length() - 1);
            addedResponse = responseValue;
            addedService = createService(responseValue);
        });
    }

    public Service getAddedService() {
        return addedService;
    }

    public String getAddedResponse() {
        return addedResponse;
    }

    private PaneServiceProperties switchServicePanes(String item) {
        switch (item) {
            case "Compute":
                computePane = new PaneComputeService();
                return computePane;
            case "Database":
                databasePane = new PaneDatabaseService();
                return databasePane;
            case "Storage":
                storagePane = new PaneStorageService();
                return storagePane;
            case "Analytic":
                analyticPane = new PaneAnalyticService();
                return analyticPane;
            case "Network":
                networkPane = new PaneNetworkService();
                return networkPane;
            case "Integration":
                integrationPane = new PaneIntegrationService();
                return integrationPane;
            case "Monitoring":
                monitoringPane = new PaneMonitoringService();
                return monitoringPane;
            default:
                return null;
        }
    }

    private Service createService(String choice) {
        switch (choice) {
            case "Compute":
                return new ComputeService(
                        computePane.getName(),
                        computePane.getComputeType()
                );
            case "Database":
                return new DatabaseService();
            case "Storage":
                return new StorageService();
            case "Analytic":
                return new AnalyticService();
            case "Network":
                return new NetworkService();
            case "Integration":
                if (integrationPane.getType().equals(Config.getInstance().getConfigValues("integration-type")[0])) {
                    return new IntegrationService(
                            integrationPane.getName(),
                            integrationPane.getType(),
                            integrationPane.getData(),
                            integrationPane.getRequests(),
                            integrationPane.getMessages()
                    );
                } else {
                    return new IntegrationService(
                            integrationPane.getName(),
                            integrationPane.getType(),
                            integrationPane.getData(),
                            integrationPane.getRequests()
                    );
                }

            case "Monitoring":
                return new MonitoringService(
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
