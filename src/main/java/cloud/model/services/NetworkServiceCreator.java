package cloud.model.services;

import cloud.configuration.Config;

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
        String[] types = Config.getInstance().getConfigValuesAsArray("network-type");

        if (type.equals(types[0]) || type.equals(types[1]) || type.equals(types[2])) {
            return new NetworkService(name, type, requests, data, "", "");
        } else if (type.equals(types[3])) {
            return new NetworkService(name, type, requests, data, dataOut, "");
        } else if (type.equals(types[4])) {
            return new NetworkService(name, type, requests, "", "", zones);
        } else {
            return null;
        }
    }
}
