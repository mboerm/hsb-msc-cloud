package cloud.model.provider;

import cloud.configuration.Config;

class Google extends Provider implements IPricing {

    Google() {
        setServiceName("Google Cloud Platform");
        setServiceShortName("gcp");
        setPriceFile(Config.getInstance().getConfigValue("gcp-prices"));
        setFreeFile(Config.getInstance().getConfigValue("gcp-free-tier"));
    }

    @Override
    public void calculateCosts() {
        System.out.println(getServiceName() + " -> " + "None!");
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
