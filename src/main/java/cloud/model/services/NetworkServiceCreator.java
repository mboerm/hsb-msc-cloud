package cloud.model.services;

public class NetworkServiceCreator implements ServiceAbstractCreator {

    private String name;
    private String type;
    private String requests;
    private String data;
    private String dataOut;
    private String zones;

    public NetworkServiceCreator(String name, String type, String requests, String data, String dataOut, String zones) {
        this.name = name;
        this.type = type;
        this.requests = requests;
        this.data = data;
        this.dataOut = dataOut;
        this.zones = zones;
    }

    @Override
    public Service createService() {
        switch (this.type) {
            case "VPC":
            case "VPN":
            case "API":
                return new NetworkService(name, type, requests, data, "", "");
            case "CDN":
                return new NetworkService(name, type, requests, data, dataOut, "");
            case "DNS":
                return new NetworkService(name, type, requests, "", "", zones);
            default:
                return null;
        }
    }
}
