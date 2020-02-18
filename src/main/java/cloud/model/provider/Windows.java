package cloud.model.provider;

import cloud.configuration.Config;
import cloud.model.pricing.IPricing;

class Windows extends Provider implements IPricing {

    Windows() {
        setServiceName("Windows Azure");
        setServicesFile(Config.getInstance().getConfigValue("azure-services"));
    }

    @Override
    public void calculateCosts() {
        System.out.println(getServiceName() + " -> " + "None!");
    }

    @Override
    public void optimizeCosts() {
        /*
         * TODO: implement function to optimize costs of services for windows azure
         */
    }
}
