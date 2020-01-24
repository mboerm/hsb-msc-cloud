package cloud.model.provider;

import cloud.configuration.Config;

public class ProviderFactory {

    public Provider getProvider(String provider) {
        String[] providerNames = Config.getInstance().getConfigValuesAsArray("provider-services");
        if (provider.equals(providerNames[0])) {
            return new Amazon();
        } else if (provider.equals(providerNames[1])) {
            return new Windows();
        } else if (provider.equals(providerNames[2])) {
            return new Google();
        } else {
            throw new IllegalArgumentException("Invalid provider name!");
        }
    }
}
