package cloud.view.dialogs;

import cloud.model.Design;
import cloud.model.DesignManager;
import cloud.model.services.*;
import cloud.view.services.*;

public class DialogAddServiceC {
    private Design design;
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

        serviceUsagePane = dialogAdd.getServiceUsagePropertiesPane();
        serviceUsagePane.setRegionItem(DesignManager.getInstance().getDesign().getPrimaryRegion());

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
            setServiceUsageProperties();
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
        switch (choice) {
            case "Compute":
                return ServiceCreator.getService(new ComputeServiceCreator(
                        computePane.getName(), computePane.getComputeType(), computePane.getInstanceType(), computePane.getInstanceSize(),
                        computePane.getComputeInstanceRef(), computePane.getStorageInstanceRef(), computePane.getSystem(),
                        computePane.getCPU(), computePane.getStorage(), computePane.getData(), computePane.getNumOne(), computePane.getNumTwo()
                ));
            case "Database":
                return ServiceCreator.getService(new DatabaseServiceCreator(
                        databasePane.getName(), databasePane.getDatabaseType(), databasePane.getDatabaseScheme(),
                        databasePane.getInstanceType(), databasePane.getInstanceSize(), databasePane.getDuration(),
                        databasePane.getStorage(), databasePane.getBackup(), databasePane.getData(), databasePane.getQueries()
                ));
            case "Storage":
                return ServiceCreator.getService(new StorageServiceCreator(
                        storagePane.getName(), storagePane.getStorageType(), storagePane.getStorageMode(),
                        storagePane.getCapacity(), storagePane.getData(), storagePane.getRequests(),
                        storagePane.getQueries(), storagePane.getRate()
                ));
            case "Analytic":
                return ServiceCreator.getService(new AnalyticServiceCreator(
                        analyticPane.getName(), analyticPane.getAnalyticType(), analyticPane.getData(),
                        analyticPane.getDataOut(), analyticPane.getActivities(), analyticPane.getUnits(),
                        analyticPane.getInstanceSize()
                ));
            case "Network":
                return ServiceCreator.getService(new NetworkServiceCreator(
                        networkPane.getName(), networkPane.getNetworkType(), networkPane.getRequests(),
                        networkPane.getData(), networkPane.getDataOut(), networkPane.getZones()
                ));
            case "Integration":
                return ServiceCreator.getService(new IntegrationServiceCreator(
                        integrationPane.getName(), integrationPane.getType(), integrationPane.getData(),
                        integrationPane.getRequests(), integrationPane.getMessages()
                ));
            case "Monitoring":
                return ServiceCreator.getService(new MonitoringServiceCreator(
                        monitoringPane.getName(), monitoringPane.getMetrics(), monitoringPane.getRequests(),
                        monitoringPane.getData(), monitoringPane.getEvents(), monitoringPane.getLoggingState()
                ));
            default:
                System.err.println("Empty service!");
                return null;
        }
    }

    private void setServiceUsageProperties() {
        createdService.setRegion(serviceUsagePane.getRegionText());
        createdService.setUsageType(serviceUsagePane.getUsageTypeText());
        createdService.setUsagePeriod(serviceUsagePane.getUsagePeriodText());
        createdService.setUsagePrepay(serviceUsagePane.getUsagePrepayText());
        createdService.setOpMode(serviceUsagePane.getOpModeText());
    }
}
