package cloud.view.dialogs;

import cloud.model.design.DesignManager;
import cloud.model.services.*;
import cloud.view.services.*;

public class ServiceDialogC {
    private ServiceDialog dialogService;

    private ServicePaneFactory servicePaneFactory;
    private ServiceUsagePropertiesPane serviceUsagePane;
    private ComputeServicePane computePane;
    private DatabaseServicePane databasePane;
    private StorageServicePane storagePane;
    private AnalyticServicePane analyticPane;
    private NetworkServicePane networkPane;
    private IntegrationServicePane integrationPane;
    private MonitoringServicePane monitoringPane;

    private Service serviceData;

    public void newDialog() {
        dialogService = new ServiceDialog();
        servicePaneFactory = new ServicePaneFactory();
        serviceUsagePane = dialogService.getServiceUsagePropertiesPane();
        serviceUsagePane.setRegionItem(DesignManager.getInstance().getDesign().getPrimaryRegion());

        dialogService.getServiceTypeBox().getSelectionModel().selectedItemProperty().addListener((ov, oldItem, newItem) -> {
            dialogService.setSeparator();
            try {
                dialogService.getServiceDialogPane().setRight(switchServicePropertiesPane(newItem));
            } catch (IllegalArgumentException e) {
                System.err.println("Invalid service item to switch panes!");
            }
            dialogService.getDialogPane().getScene().getWindow().sizeToScene();
            dialogService.enableOKButton();
        });
    }

    public boolean showDialog() {
        dialogService.showAndWait().ifPresent(response ->  {
            try {
                serviceData = createService(response);
            } catch (IllegalArgumentException e) {
                System.out.println("Service creation cancelled");
            }
        });

        if (serviceData != null) {
            setServiceUsageProperties();
            return true;
        }
        return false;
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

    public void resetServiceData() {this.serviceData = null;}

    private ServicePropertiesPane switchServicePropertiesPane(String item) {
        if (ServiceChecker.isComputeItem(item)) {
            return computePane = (ComputeServicePane) servicePaneFactory.getServicePane(item);
        } else if (ServiceChecker.isDatabaseItem(item)) {
            return databasePane = (DatabaseServicePane) servicePaneFactory.getServicePane(item);
        } else if (ServiceChecker.isStorageItem(item)) {
            return storagePane = (StorageServicePane) servicePaneFactory.getServicePane(item);
        } else if (ServiceChecker.isAnalyticItem(item)) {
            return analyticPane = (AnalyticServicePane) servicePaneFactory.getServicePane(item);
        } else if (ServiceChecker.isNetworkItem(item)) {
            return networkPane = (NetworkServicePane) servicePaneFactory.getServicePane(item);
        } else if (ServiceChecker.isIntegrationItem(item)) {
            return integrationPane = (IntegrationServicePane) servicePaneFactory.getServicePane(item);
        } else if (ServiceChecker.isMonitoringItem(item)) {
            return monitoringPane = (MonitoringServicePane) servicePaneFactory.getServicePane(item);
        } else {
            throw new IllegalArgumentException("Invalid service item");
        }
    }

    private Service createService(String item) {
        if (ServiceChecker.isComputeItem(item)) {
            return ServiceFactory.getService(new ComputeServiceCreator(
                    computePane.getName(), computePane.getComputeType(), computePane.getInstanceType(), computePane.getInstanceSize(),
                    computePane.getComputeInstanceRef(), computePane.getStorageInstanceRef(), computePane.getSystem(),
                    computePane.getCPU(), computePane.getStorage(), computePane.getData(), computePane.getNumOne(), computePane.getNumTwo()
            ));
        } else if (ServiceChecker.isDatabaseItem(item)) {
            return ServiceFactory.getService(new DatabaseServiceCreator(
                    databasePane.getName(), databasePane.getDatabaseType(), databasePane.getDatabaseScheme(),
                    databasePane.getInstanceType(), databasePane.getInstanceSize(), databasePane.getDuration(),
                    databasePane.getStorage(), databasePane.getBackup(), databasePane.getData(), databasePane.getNum()
            ));
        } else if (ServiceChecker.isStorageItem(item)) {
            return ServiceFactory.getService(new StorageServiceCreator(
                    storagePane.getName(), storagePane.getStorageType(), storagePane.getStorageMode(),
                    storagePane.getCapacity(), storagePane.getData(), storagePane.getRequests(),
                    storagePane.getQueries(), storagePane.getRate()
            ));
        } else if (ServiceChecker.isAnalyticItem(item)) {
            return ServiceFactory.getService(new AnalyticServiceCreator(
                    analyticPane.getName(), analyticPane.getAnalyticType(), analyticPane.getData(),
                    analyticPane.getDataOut(), analyticPane.getNum(), analyticPane.getUnits(),
                    analyticPane.getInstanceType(), analyticPane.getInstanceSize()
            ));
        } else if (ServiceChecker.isNetworkItem(item)) {
            return ServiceFactory.getService(new NetworkServiceCreator(
                    networkPane.getName(), networkPane.getNetworkType(), networkPane.getRequests(),
                    networkPane.getData(), networkPane.getDataOut(), networkPane.getZones()
            ));
        } else if (ServiceChecker.isIntegrationItem(item)) {
            return ServiceFactory.getService(new IntegrationServiceCreator(
                    integrationPane.getName(), integrationPane.getIntegrationType(), integrationPane.getData(),
                    integrationPane.getRequests(), integrationPane.getMessages()
            ));
        } else if (ServiceChecker.isMonitoringItem(item)) {
            return ServiceFactory.getService(new MonitoringServiceCreator(
                    monitoringPane.getName(), monitoringPane.getMetrics(), monitoringPane.getRequests(),
                    monitoringPane.getData(), monitoringPane.getEvents(), monitoringPane.getLoggingState()
            ));
        } else {
            throw new IllegalArgumentException("Invalid service type");
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
            databasePane.setNum(((DatabaseService) serviceData).getNum());
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
            analyticPane.setNum(((AnalyticService) serviceData).getNum());
            analyticPane.setUnits(((AnalyticService) serviceData).getUnits());
            analyticPane.setInstanceType(((AnalyticService) serviceData).getInstanceType());
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
