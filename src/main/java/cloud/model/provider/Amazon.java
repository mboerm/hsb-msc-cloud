package cloud.model.provider;

import cloud.configuration.Config;

class Amazon extends Provider implements Costs {

    Amazon() {
        setServiceName("Amazon Web Services");
        setServiceFile(Config.getInstance().getConfigValue("aws-service-file"));
        setPriceFile(Config.getInstance().getConfigValue("aws-price-file"));
        setFreeFile(Config.getInstance().getConfigValue("aws-free-file"));
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
