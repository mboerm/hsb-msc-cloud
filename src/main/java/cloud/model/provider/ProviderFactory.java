package cloud.model.provider;

public class ProviderFactory {

    public Provider getProvider(String provider) {
        if (provider.equals("Amazon Web Services")) {
            return new Amazon();
        } else if (provider.equals("Windows Azure")) {
            return new Windows();
        } else if (provider.equals("Google Cloud Platform")) {
            return new Google();
        } else {
            System.err.println("Not valid!");
            return null;
        }
    }
}
