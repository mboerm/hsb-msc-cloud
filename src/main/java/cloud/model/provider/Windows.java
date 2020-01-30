package cloud.model.provider;

import cloud.configuration.Config;
import cloud.model.pricing.IPricing;

class Windows extends Provider implements IPricing {

    Windows() {
        setServiceName("Windows Azure");
        setServiceShortName("azure");
        setPriceFile(Config.getInstance().getConfigValue("azure-prices"));
        setFreeFile(Config.getInstance().getConfigValue("azure-free-tier"));
    }

    @Override
    public void calculateCosts() {
        System.out.println(getServiceName() + " -> " + "None!");
    }

    @Override
    public void optimizeCosts() {

    }
}
