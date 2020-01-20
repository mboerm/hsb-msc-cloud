package cloud.model.provider;

import cloud.configuration.Config;

class Google extends Provider {

    Google() {
        setServiceName("Google Cloud Platform");
        setShortName("gcp");
        setPriceFile(Config.getInstance().getConfigValue("gcp-price"));
        setFreeFile(Config.getInstance().getConfigValue("gcp-free-tier"));
    }
}
