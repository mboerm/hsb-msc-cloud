package cloud.model.provider;

import cloud.configuration.Config;

class Google extends Provider {

    Google() {
        setServiceName("Google Cloud Platform");
        setServiceFile(Config.getInstance().getConfigValue("gcp-service-file"));
        setPriceFile(Config.getInstance().getConfigValue("gcp-price-file"));
        setFreeFile(Config.getInstance().getConfigValue("gcp-free-file"));
    }
}
