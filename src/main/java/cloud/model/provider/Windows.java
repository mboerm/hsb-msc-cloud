package cloud.model.provider;

import cloud.configuration.Config;

class Windows extends Provider implements Costs {

    Windows() {
        setServiceName("Windows Azure");
        setServiceShortName("azure");
        setPriceFile(Config.getInstance().getConfigValue("azure-prices"));
        setFreeFile(Config.getInstance().getConfigValue("azure-free-tier"));
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
