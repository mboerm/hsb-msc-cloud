package cloud.model.provider;

import cloud.configuration.Config;

class Windows extends Provider {

    Windows() {
        setServiceName("Windows Azure");
        setShortName("azure");
        setPriceFile(Config.getInstance().getConfigValue("azure-price"));
        setFreeFile(Config.getInstance().getConfigValue("azure-free-tier"));
    }
}
