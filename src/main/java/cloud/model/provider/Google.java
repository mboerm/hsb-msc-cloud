package cloud.model.provider;

import cloud.configuration.Config;
import cloud.model.pricing.Pricing;

/**
 * Protected Google provider class
 */
class Google extends Provider implements Pricing {

    /**
     * Protected constructor
     */
    Google() {
        setServiceName("Google Cloud Platform");
        setServiceFile(Config.getInstance().getConfigValue("gcp-services"));
    }

    @Override
    public void calculateCosts() {
        /*
         * TODO: implement api call for cost calculation
         */
    }

    @Override
    public void calculateStaticCosts() {
        /*
         * TODO: optionally implement static cost calculation
         */
    }

    @Override
    public void optimizeCosts() {
        /*
         * TODO: implement function to optimize costs of services for google cloud platform
         */
    }
}
