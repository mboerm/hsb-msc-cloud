package cloud.model.provider;

import cloud.configuration.Config;

class Windows extends Provider {

    Windows() {
        setServiceName("Windows Azure");
        setServiceFile(Config.getInstance().getConfigValue("azure-service-file"));
        setPriceFile(Config.getInstance().getConfigValue("azure-price-file"));
        setFreeFile(Config.getInstance().getConfigValue("azure-free-file"));
    }
}
