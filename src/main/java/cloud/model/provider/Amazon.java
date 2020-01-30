package cloud.model.provider;

import cloud.configuration.Config;

class Amazon extends Provider implements IPricing {

    Amazon() {
        setServiceName("Amazon Web Services");
        setServiceShortName("aws");
        setPriceFile(Config.getInstance().getConfigValue("aws-prices"));
        setFreeFile(Config.getInstance().getConfigValue("aws-free-tier"));
    }

    @Override
    public void calculateCosts() {
        System.out.println(getServiceName() + " -> " + "Static Cost Calculation!");
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
