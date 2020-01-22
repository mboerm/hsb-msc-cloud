package cloud.view.dialogs;

import cloud.model.design.DesignManager;
import cloud.model.services.*;
import cloud.view.services.*;

public class DialogServiceC {
    DialogService dialogService;

    private PaneServiceUsageProperties serviceUsagePane;
    private PaneComputeService computePane;
    private PaneDatabaseService databasePane;
    private PaneStorageService storagePane;
    private PaneAnalyticService analyticPane;
    private PaneNetworkService networkPane;
    private PaneIntegrationService integrationPane;
    private PaneMonitoringService monitoringPane;

    private Service serviceData;

    public void newDialog() {
        dialogService = new DialogService();
        serviceData = null;
        serviceUsagePane = dialogService.getServiceUsagePropertiesPane();
        serviceUsagePane.setRegionItem(DesignManager.getInstance().getDesign().getPrimaryRegion());

        dialogService.getServiceTypeBox().getSelectionModel().selectedItemProperty().addListener((ov, oldItem, newItem) -> {
            dialogService.setSeparator();
            dialogService.getServiceDialogPane().setRight(switchServicePropertiesPane(newItem));
            dialogService.getDialogPane().getScene().getWindow().sizeToScene();
            dialogService.enableOKButton();
        });
    }

    public boolean showDialog() {
        dialogService.showAndWait().ifPresent(response -> serviceData = createService(response));

        if (serviceData != null) {
            setServiceUsageProperties();
            return true;
        } else {
            return false;
        }
    }

    public boolean showPresetDialog(Service data) {
        serviceData = data;
        dialogService.selectServiceType(serviceData.getCategory());
        dialogService.getServiceTypeBox().setDisable(true);
        setServiceUsagePane();
        setServicePropertiesPane();
        return showDialog();
    }

    public Service getServiceData() {
        return serviceData;
    }

    private PaneServiceProperties switchServicePropertiesPane(String item) {
        if (ServiceChecker.isComputeItem(item)) {
            computePane = new PaneComputeService();
            return computePane;
        } else if (ServiceChecker.isDatabaseItem(item)) {
            databasePane = new PaneDatabaseService();
            return databasePane;
        } else if (ServiceChecker.isStorageItem(item)) {
            storagePane = new PaneStorageService();
            return storagePane;
        } else if (ServiceChecker.isAnalyticItem(item)) {
            analyticPane = new PaneAnalyticService();
            return analyticPane;
        } else if (ServiceChecker.isNetworkItem(item)) {
            networkPane = new PaneNetworkService();
            return networkPane;
        } else if (ServiceChecker.isIntegrationItem(item)) {
            integrationPane = new PaneIntegrationService();
            return integrationPane;
        } else if (ServiceChecker.isMonitoringItem(item)) {
            monitoringPane = new PaneMonitoringService();
            return monitoringPane;
        } else {
            return new PaneServiceProperties();
        }
    }

    private Service createService(String item) {
        if (ServiceChecker.isComputeItem(item)) {
            return ServiceCreator.getService(new ComputeServiceCreator(
                    computePane.getName(), computePane.getComputeType(), computePane.getInstanceType(), computePane.getInstanceSize(),
                    computePane.getComputeInstanceRef(), computePane.getStorageInstanceRef(), computePane.getSystem(),
                    computePane.getCPU(), computePane.getStorage(), computePane.getData(), computePane.getNumOne(), computePane.getNumTwo()
            ));
        } else if (ServiceChecker.isDatabaseItem(item)) {
            return ServiceCreator.getService(new DatabaseServiceCreator(
                    databasePane.getName(), databasePane.getDatabaseType(), databasePane.getDatabaseScheme(),
                    databasePane.getInstanceType(), databasePane.getInstanceSize(), databasePane.getDuration(),
                    databasePane.getStorage(), databasePane.getBackup(), databasePane.getData(), databasePane.getQueries()
            ));
        } else if (ServiceChecker.isStorageItem(item)) {
            return ServiceCreator.getService(new StorageServiceCreator(
                    storagePane.getName(), storagePane.getStorageType(), storagePane.getStorageMode(),
                    storagePane.getCapacity(), storagePane.getData(), storagePane.getRequests(),
                    storagePane.getQueries(), storagePane.getRate()
            ));
        } else if (ServiceChecker.isAnalyticItem(item)) {
            return ServiceCreator.getService(new AnalyticServiceCreator(
                    analyticPane.getName(), analyticPane.getAnalyticType(), analyticPane.getData(),
                    analyticPane.getDataOut(), analyticPane.getActivities(), analyticPane.getUnits(),
                    analyticPane.getInstanceSize()
            ));
        } else if (ServiceChecker.isNetworkItem(item)) {
            return ServiceCreator.getService(new NetworkServiceCreator(
                    networkPane.getName(), networkPane.getNetworkType(), networkPane.getRequests(),
                    networkPane.getData(), networkPane.getDataOut(), networkPane.getZones()
            ));
        } else if (ServiceChecker.isIntegrationItem(item)) {
            return ServiceCreator.getService(new IntegrationServiceCreator(
                    integrationPane.getName(), integrationPane.getIntegrationType(), integrationPane.getData(),
                    integrationPane.getRequests(), integrationPane.getMessages()
            ));
        } else if (ServiceChecker.isMonitoringItem(item)) {
            return ServiceCreator.getService(new MonitoringServiceCreator(
                    monitoringPane.getName(), monitoringPane.getMetrics(), monitoringPane.getRequests(),
                    monitoringPane.getData(), monitoringPane.getEvents(), monitoringPane.getLoggingState()
            ));
        } else {
            return null;
        }
    }

    private void setServicePropertiesPane() {
        if (ServiceChecker.isComputeItem(serviceData.getCategory()) && serviceData instanceof ComputeService) {
            computePane.setName(serviceData.getName());
            computePane.setComputeType(((ComputeService) serviceData).getComputeType());
            computePane.setInstanceType(((ComputeService) serviceData).getInstanceType());
            computePane.setInstanceSize(((ComputeService) serviceData).getInstanceSize());
            computePane.setComputeInstanceRef(((ComputeService) serviceData).getComputeInstance());
            computePane.setStorageInstanceRef(((ComputeService) serviceData).getStorageInstance());
            computePane.setSystem(((ComputeService) serviceData).getSystem());
            computePane.setCPU(((ComputeService) serviceData).getCPU());
            computePane.setStorage(((ComputeService) serviceData).getStorage());
            computePane.setData(((ComputeService) serviceData).getData());
            computePane.setNumOne(((ComputeService) serviceData).getNumOne());
            computePane.setNumTwo(((ComputeService) serviceData).getNumTwo());           
        } else if (ServiceChecker.isDatabaseItem(serviceData.getCategory()) && serviceData instanceof DatabaseService) {
            databasePane.setName(serviceData.getName());
            databasePane.setDatabaseType(((DatabaseService) serviceData).getDatabaseType());
            databasePane.setDatabaseScheme(((DatabaseService) serviceData).getDatabaseScheme());
            databasePane.setInstanceType(((DatabaseService) serviceData).getInstanceType());
            databasePane.setInstanceSize(((DatabaseService) serviceData).getInstanceSize());
            databasePane.setDuration(((DatabaseService) serviceData).getDuration());
            databasePane.setStorage(((DatabaseService) serviceData).getStorage());
            databasePane.setBackup(((DatabaseService) serviceData).getBackup());
            databasePane.setData(((DatabaseService) serviceData).getData());
            databasePane.setQueries(((DatabaseService) serviceData).getQueries());
        } else if (ServiceChecker.isStorageItem(serviceData.getCategory()) && serviceData instanceof StorageService) {
            storagePane.setName(serviceData.getName());
            storagePane.setStorageType(((StorageService) serviceData).getStorageType());
            storagePane.setStorageMode(((StorageService) serviceData).getStorageMode());
            storagePane.setCapacity(((StorageService) serviceData).getCapacity());
            storagePane.setData(((StorageService) serviceData).getData());
            storagePane.setRequests(((StorageService) serviceData).getRequests());
            storagePane.setQueries(((StorageService) serviceData).getQueries());
            storagePane.setRate(((StorageService) serviceData).getRate());
        } else if (ServiceChecker.isAnalyticItem(serviceData.getCategory()) && serviceData instanceof AnalyticService) {
            analyticPane.setName(serviceData.getName());
            analyticPane.setAnalyticType(((AnalyticService) serviceData).getAnalyticType());
            analyticPane.setData(((AnalyticService) serviceData).getData());
            analyticPane.setDataOut(((AnalyticService) serviceData).getDataOut());
            analyticPane.setActivities(((AnalyticService) serviceData).getActivities());
            analyticPane.setUnits(((AnalyticService) serviceData).getUnits());
            analyticPane.setInstanceSize(((AnalyticService) serviceData).getInstanceSize());
        } else if (ServiceChecker.isNetworkItem(serviceData.getCategory()) && serviceData instanceof NetworkService) {
            networkPane.setName(serviceData.getName());
            networkPane.setNetworkType(((NetworkService) serviceData).getNetworkType());
            networkPane.setRequests(((NetworkService) serviceData).getRequests());
            networkPane.setData(((NetworkService) serviceData).getData());
            networkPane.setDataOut(((NetworkService) serviceData).getDataOut());
            networkPane.setZones(((NetworkService) serviceData).getZones());
        } else if (ServiceChecker.isIntegrationItem(serviceData.getCategory()) && serviceData instanceof IntegrationService) {
            integrationPane.setName(serviceData.getName());
            integrationPane.setIntegrationType(((IntegrationService) serviceData).getIntegrationType());
            integrationPane.setData(((IntegrationService) serviceData).getData());
            integrationPane.setRequests(((IntegrationService) serviceData).getRequests());
            integrationPane.setMessages(((IntegrationService) serviceData).getMessages());
        } else if (ServiceChecker.isMonitoringItem(serviceData.getCategory()) && serviceData instanceof MonitoringService) {
            monitoringPane.setName(serviceData.getName());
            monitoringPane.setMetrics(((MonitoringService) serviceData).getMetrics());
            monitoringPane.setRequests(((MonitoringService) serviceData).getRequests());
            monitoringPane.setData(((MonitoringService) serviceData).getData());
            monitoringPane.setEvents(((MonitoringService) serviceData).getEvents());
            monitoringPane.setLoggingState(((MonitoringService) serviceData).getLoggingState());
        }
    }

    private void setServiceUsageProperties() {
        serviceData.setRegion(serviceUsagePane.getRegionItem());
        serviceData.setUsageType(serviceUsagePane.getUsageTypeItem());
        serviceData.setUsagePeriod(serviceUsagePane.getUsagePeriodItem());
        serviceData.setUsagePrepay(serviceUsagePane.getUsagePrepayItem());
        serviceData.setOpMode(serviceUsagePane.getOpModeItem());
    }

    private void setServiceUsagePane() {
        serviceUsagePane.setRegionItem(serviceData.getRegion());
        serviceUsagePane.setUsageTypeItem(serviceData.getUsageType());
        serviceUsagePane.setUsagePeriodItem(serviceData.getUsagePeriod());
        serviceUsagePane.setUsagePrepayItem(serviceData.getUsagePrepay());
        serviceUsagePane.setOpModeItem(serviceData.getOpMode());
    }
}
