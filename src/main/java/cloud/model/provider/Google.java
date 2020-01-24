package cloud.model.provider;

import cloud.configuration.Config;

class Google extends Provider implements Pricing {

    Google() {
        setServiceName("Google Cloud Platform");
        setServiceShortName("gcp");
        setPriceFile(Config.getInstance().getConfigValue("gcp-prices"));
        setFreeFile(Config.getInstance().getConfigValue("gcp-free-tier"));
    }

    @Override
    public void calculateCosts() {

    }

    @Override
    public void optimizeCosts() {

    }

    @Override
    public void scaleCosts() {

    }

    @Override
    public void compareCosts() {

    }
}
