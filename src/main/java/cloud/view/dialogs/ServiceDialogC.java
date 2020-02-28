package cloud.view.dialogs;

import cloud.model.design.DesignManager;
import cloud.model.services.*;
import cloud.view.services.*;

/**
 * Controller for service dialog
 */
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
    private AdministrationServicePane administrationPane;

    private Service serviceData;

    /**
     * Create new dialog
     */
    public void newDialog() {
        dialogService = new ServiceDialog();
        servicePaneFactory = new ServicePaneFactory();
        serviceUsagePane = dialogService.getServiceUsagePropertiesPane();
        serviceUsagePane.setLocation(DesignManager.getInstance().getDesign().getPrimaryLocation());

        dialogService.getServiceCategoryBox().getSelectionModel().selectedItemProperty().addListener((ov, oldItem, newItem) -> {
            dialogService.setSeparator();
            /* set service properties panel by chosen category */
            try {
                dialogService.getServiceDialogPane().setRight(switchServicePropertiesPane(newItem));
            } catch (IllegalArgumentException e) {
                System.err.println("Invalid service item to switch panes!");
            }
            dialogService.getDialogPane().getScene().getWindow().sizeToScene();
            dialogService.enableOKButton();
        });
    }

    /**
     * Show new dialog
     * @return true if service was created successfully
     */
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

    /**
     * Show preset dialog if service was opened in table
     * @param data service to be opened
     * @return true if service was optionally updated successful
     */
    public boolean showPresetDialog(Service data) {
        serviceData = data;
        dialogService.selectServiceType(serviceData.getCategory());
        dialogService.getServiceCategoryBox().setDisable(true);
        setServiceUsagePane();
        setServicePropertiesPane();
        return showDialog();
    }

    public Service getServiceData() {
        return serviceData;
    }

    public void resetServiceData() {this.serviceData = null;}

    /**
     * Switch service property panels
     * @param item service category
     * @return service property panel
     */
    private ServicePropertiesPane switchServicePropertiesPane(String item) {
        if (ServiceChecker.getInstance().isComputeItem(item)) {
            return computePane = (ComputeServicePane) servicePaneFactory.getServicePane(item);
        } else if (ServiceChecker.getInstance().isDatabaseItem(item)) {
            return databasePane = (DatabaseServicePane) servicePaneFactory.getServicePane(item);
        } else if (ServiceChecker.getInstance().isStorageItem(item)) {
            return storagePane = (StorageServicePane) servicePaneFactory.getServicePane(item);
        } else if (ServiceChecker.getInstance().isAnalyticItem(item)) {
            return analyticPane = (AnalyticServicePane) servicePaneFactory.getServicePane(item);
        } else if (ServiceChecker.getInstance().isNetworkItem(item)) {
            return networkPane = (NetworkServicePane) servicePaneFactory.getServicePane(item);
        } else if (ServiceChecker.getInstance().isIntegrationItem(item)) {
            return integrationPane = (IntegrationServicePane) servicePaneFactory.getServicePane(item);
        } else if (ServiceChecker.getInstance().isAdministrationItem(item)) {
            return administrationPane = (AdministrationServicePane) servicePaneFactory.getServicePane(item);
        } else {
            throw new IllegalArgumentException("Invalid service item");
        }
    }

    /**
     * Create service by category
     * @param item service category
     * @return new service object
     */
    private Service createService(String item) {
        if (ServiceChecker.getInstance().isComputeItem(item)) {
            return ServiceFactory.getService(new ComputeServiceCreator(
                    computePane.getName(), computePane.getComputeType(), computePane.getInstanceType(),
                    computePane.getComputeInstanceRef(), computePane.getStorageInstanceRef(), computePane.getSystem(),
                    computePane.getCPU(), computePane.getStorage(), computePane.getData(), computePane.getNumOne(), computePane.getNumTwo()
            ));
        } else if (ServiceChecker.getInstance().isDatabaseItem(item)) {
            return ServiceFactory.getService(new DatabaseServiceCreator(
                    databasePane.getName(), databasePane.getDatabaseType(), databasePane.getDatabaseScheme(),
                    databasePane.getInstanceType(), databasePane.getDuration(),
                    databasePane.getStorage(), databasePane.getBackup(), databasePane.getData(), databasePane.getNum()
            ));
        } else if (ServiceChecker.getInstance().isStorageItem(item)) {
            return ServiceFactory.getService(new StorageServiceCreator(
                    storagePane.getName(), storagePane.getStorageType(), storagePane.getStorageMode(),
                    storagePane.getCapacity(), storagePane.getData(), storagePane.getRequests(),
                    storagePane.getRate()
            ));
        } else if (ServiceChecker.getInstance().isAnalyticItem(item)) {
            return ServiceFactory.getService(new AnalyticServiceCreator(
                    analyticPane.getName(), analyticPane.getAnalyticType(), analyticPane.getData(),
                    analyticPane.getDataOut(), analyticPane.getNum(), analyticPane.getUnits(),
                    analyticPane.getInstanceType()
            ));
        } else if (ServiceChecker.getInstance().isNetworkItem(item)) {
            return ServiceFactory.getService(new NetworkServiceCreator(
                    networkPane.getName(), networkPane.getNetworkType(), networkPane.getRequests(),
                    networkPane.getData(), networkPane.getDataOut(), networkPane.getZones()
            ));
        } else if (ServiceChecker.getInstance().isIntegrationItem(item)) {
            return ServiceFactory.getService(new IntegrationServiceCreator(
                    integrationPane.getName(), integrationPane.getIntegrationType(), integrationPane.getIntegrationMode(), integrationPane.getData(),
                    integrationPane.getRequests(), integrationPane.getMessages()
            ));
        } else if (ServiceChecker.getInstance().isAdministrationItem(item)) {
            return ServiceFactory.getService(new AdministrationServiceCreator(
                    administrationPane.getName(), administrationPane.getAdministrationType(), administrationPane.getMetrics(), administrationPane.getRequests(),
                    administrationPane.getData(), administrationPane.getEvents(), administrationPane.getLoggingState()
            ));
        } else {
            throw new IllegalArgumentException("Invalid service type");
        }
    }

    /**
     * Set service property pane for preset dialog
     */
    private void setServicePropertiesPane() {
        if (ServiceChecker.getInstance().isComputeItem(serviceData.getCategory()) && serviceData instanceof ComputeService) {
            computePane.setName(serviceData.getName());
            computePane.setComputeType(((ComputeService) serviceData).getComputeType());
            computePane.setInstanceType(((ComputeService) serviceData).getInstanceType());
            computePane.setComputeInstanceRef(((ComputeService) serviceData).getComputeInstance());
            computePane.setStorageInstanceRef(((ComputeService) serviceData).getStorageInstance());
            computePane.setSystem(((ComputeService) serviceData).getSystem());
            computePane.setCPU(((ComputeService) serviceData).getCPU());
            computePane.setStorage(((ComputeService) serviceData).getStorage());
            computePane.setData(((ComputeService) serviceData).getData());
            computePane.setNumOne(((ComputeService) serviceData).getNumOne());
            computePane.setNumTwo(((ComputeService) serviceData).getNumTwo());           
        } else if (ServiceChecker.getInstance().isDatabaseItem(serviceData.getCategory()) && serviceData instanceof DatabaseService) {
            databasePane.setName(serviceData.getName());
            databasePane.setDatabaseType(((DatabaseService) serviceData).getDatabaseType());
            databasePane.setDatabaseScheme(((DatabaseService) serviceData).getDatabaseScheme());
            databasePane.setInstanceType(((DatabaseService) serviceData).getInstanceType());
            databasePane.setDuration(((DatabaseService) serviceData).getDuration());
            databasePane.setStorage(((DatabaseService) serviceData).getStorage());
            databasePane.setBackup(((DatabaseService) serviceData).getBackup());
            databasePane.setData(((DatabaseService) serviceData).getData());
            databasePane.setNum(((DatabaseService) serviceData).getNum());
        } else if (ServiceChecker.getInstance().isStorageItem(serviceData.getCategory()) && serviceData instanceof StorageService) {
            storagePane.setName(serviceData.getName());
            storagePane.setStorageType(((StorageService) serviceData).getStorageType());
            storagePane.setStorageMode(((StorageService) serviceData).getStorageMode());
            storagePane.setCapacity(((StorageService) serviceData).getCapacity());
            storagePane.setData(((StorageService) serviceData).getData());
            storagePane.setRequests(((StorageService) serviceData).getRequests());
            storagePane.setRate(((StorageService) serviceData).getRate());
        } else if (ServiceChecker.getInstance().isAnalyticItem(serviceData.getCategory()) && serviceData instanceof AnalyticService) {
            analyticPane.setName(serviceData.getName());
            analyticPane.setAnalyticType(((AnalyticService) serviceData).getAnalyticType());
            analyticPane.setData(((AnalyticService) serviceData).getData());
            analyticPane.setDataOut(((AnalyticService) serviceData).getDataOut());
            analyticPane.setNum(((AnalyticService) serviceData).getNum());
            analyticPane.setUnits(((AnalyticService) serviceData).getUnits());
            analyticPane.setInstanceType(((AnalyticService) serviceData).getInstanceType());
        } else if (ServiceChecker.getInstance().isNetworkItem(serviceData.getCategory()) && serviceData instanceof NetworkService) {
            networkPane.setName(serviceData.getName());
            networkPane.setNetworkType(((NetworkService) serviceData).getNetworkType());
            networkPane.setRequests(((NetworkService) serviceData).getRequests());
            networkPane.setData(((NetworkService) serviceData).getData());
            networkPane.setDataOut(((NetworkService) serviceData).getDataOut());
            networkPane.setZones(((NetworkService) serviceData).getZones());
        } else if (ServiceChecker.getInstance().isIntegrationItem(serviceData.getCategory()) && serviceData instanceof IntegrationService) {
            integrationPane.setName(serviceData.getName());
            integrationPane.setIntegrationType(((IntegrationService) serviceData).getIntegrationType());
            integrationPane.setIntegrationMode(((IntegrationService) serviceData).getIntegrationMode());
            integrationPane.setData(((IntegrationService) serviceData).getData());
            integrationPane.setRequests(((IntegrationService) serviceData).getRequests());
            integrationPane.setMessages(((IntegrationService) serviceData).getMessages());
        } else if (ServiceChecker.getInstance().isAdministrationItem(serviceData.getCategory()) && serviceData instanceof AdministrationService) {
            administrationPane.setName(serviceData.getName());
            administrationPane.setAdministrationType(((AdministrationService) serviceData).getAdministrationType());
            administrationPane.setMetrics(((AdministrationService) serviceData).getMetrics());
            administrationPane.setRequests(((AdministrationService) serviceData).getRequests());
            administrationPane.setData(((AdministrationService) serviceData).getData());
            administrationPane.setEvents(((AdministrationService) serviceData).getEvents());
            administrationPane.setLoggingState(((AdministrationService) serviceData).getLoggingState());
        }
    }

    /**
     * Set service usage properties
     */
    private void setServiceUsageProperties() {
        serviceData.setLocation(serviceUsagePane.getLocation());
        serviceData.setUsageType(serviceUsagePane.getUsageTypeItem());
        serviceData.setUsagePeriod(serviceUsagePane.getUsagePeriodItem());
        serviceData.setUsagePrepay(serviceUsagePane.getUsagePrepayItem());
        serviceData.setOpMode(serviceUsagePane.getOpModeItem());
    }

    /**
     * Set service usage pane for preset dialog
     */
    private void setServiceUsagePane() {
        serviceUsagePane.setLocation(serviceData.getLocation());
        serviceUsagePane.setUsageTypeItem(serviceData.getUsageType());
        serviceUsagePane.setUsagePeriodItem(serviceData.getUsagePeriod());
        serviceUsagePane.setUsagePrepayItem(serviceData.getUsagePrepay());
        serviceUsagePane.setOpModeItem(serviceData.getOpMode());
    }
}
