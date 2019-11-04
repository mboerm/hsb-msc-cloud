package cloud.provider;

public class ProviderFactory {

    public Provider getProvider(String provider) {
        if (provider.equals("Amazon")) {
            return new Amazon();
        }
        else if (provider.equals("Windows")) {
            return new Windows();
        }
        else if (provider.equals("Google")) {
            return new Google();
        }
        else {
            System.err.println("Not valid!");
            return null;
        }
    }
}
