package cloud.view.services;

import cloud.configuration.Config;

public class ServicePaneFactory {
    public ServicePropertiesPane getServicePane(String service) {
        String[] serviceCategories = Config.getInstance().getConfigValuesAsArray("service-categories");
        if (service.equals(serviceCategories[0])) {
            return new ComputeServicePane();
        } else if (service.equals(serviceCategories[1])) {
            return new DatabaseServicePane();
        } else if (service.equals(serviceCategories[2])) {
            return new StorageServicePane();
        } else if (service.equals(serviceCategories[3])) {
            return new AnalyticServicePane();
        } else if (service.equals(serviceCategories[4])) {
            return new NetworkServicePane();
        } else if (service.equals(serviceCategories[5])) {
            return new IntegrationServicePane();
        } else if (service.equals(serviceCategories[6])) {
            return new AdministrationServicePane();
        } else {
            throw new IllegalArgumentException("Invalid service category!");
        }
    }
}
