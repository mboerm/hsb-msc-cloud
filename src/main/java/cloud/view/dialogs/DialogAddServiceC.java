package cloud.view.dialogs;

import cloud.model.services.*;
import cloud.view.services.*;

public class DialogAddServiceC {
    private Service createdService;
    private String dialogResponse;

    private PaneServiceUsageProperties serviceUsagePane;

    private PaneComputeService computePane;
    private PaneDatabaseService databasePane;
    private PaneStorageService storagePane;
    private PaneAnalyticService analyticPane;
    private PaneNetworkService networkPane;
    private PaneIntegrationService integrationPane;
    private PaneMonitoringService monitoringPane;

    public DialogAddServiceC() {
        DialogAddService dialogAdd = new DialogAddService();

        serviceUsagePane = dialogAdd.getUsagePropertiesPane();

        dialogAdd.getServiceTypeBox().getSelectionModel().selectedItemProperty().addListener((ov, oldItem, newItem) -> {
            dialogAdd.getServiceDialogPane().setRight(switchServicePanes(newItem));
            dialogAdd.getDialogPane().getScene().getWindow().sizeToScene();
            dialogAdd.enableOKButton();
        });

        dialogAdd.showAndWait().ifPresent(response -> {
            String responseValue = response.toString().substring(
                    response.toString().lastIndexOf(':') + 2,
                    response.toString().length() - 1);
            dialogResponse = responseValue;
            createdService = setCreatedService(responseValue);
        });
    }

    public Service getCreatedService() {
        return createdService;
    }
    public String getDialogResponse() {
        return dialogResponse;
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
                return new PaneServiceProperties();
        }
    }

    private Service setCreatedService(String choice) {
        if (choice.equals("Compute")) {
            return null;
        } else if (choice.equals("Database")) {
            return null;
        } else if (choice.equals("Storage")) {
            return null;
        } else if (choice.equals("Analytic")) {
            return null;
        } else if (choice.equals("Network")) {
            return ServiceCreator.getService(new NetworkServiceCreator(
                    networkPane.getName(), networkPane.getNetworkType(), networkPane.getRequests(),
                    networkPane.getData(), networkPane.getDataOut(), networkPane.getZones()
            ));
        } else if (choice.equals("Integration")) {
            return ServiceCreator.getService(new IntegrationServiceCreator(
                    integrationPane.getName(),integrationPane.getType(),integrationPane.getData(),
                    integrationPane.getRequests(),integrationPane.getMessages()
            ));
        } else if (choice.equals("Monitoring")) {
            return ServiceCreator.getService(new MonitoringServiceCreator(
                    monitoringPane.getName(),monitoringPane.getMetrics(),monitoringPane.getRequests(),
                    monitoringPane.getData(),monitoringPane.getEvents(),monitoringPane.getLoggingState()
            ));
        } else {
            System.err.println("Empty service!");
            return null;
        }
    }
}
