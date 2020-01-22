package cloud.model.provider;

import cloud.configuration.Config;

class Amazon extends Provider implements Costs {

    Amazon() {
        setServiceName("Amazon Web Services");
        setShortName("aws");
        setPriceFile(Config.getInstance().getConfigValue("aws-prices"));
        setFreeFile(Config.getInstance().getConfigValue("aws-free-tier"));
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
