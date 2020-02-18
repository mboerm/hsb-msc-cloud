package cloud.model.provider;

import cloud.configuration.Config;
import cloud.model.pricing.IPricing;

class Google extends Provider implements IPricing {

    Google() {
        setServiceName("Google Cloud Platform");
        setServicesFile(Config.getInstance().getConfigValue("gcp-services"));
        setFreeFile(Config.getInstance().getConfigValue("gcp-free-tier"));
    }

    @Override
    public void calculateCosts() {
        System.out.println(getServiceName() + " -> " + "None!");
    }

    @Override
    public void optimizeCosts() {
        /*
         * TODO: implement function to optimize costs of services for google cloud platform
         */
    }
}
