package cloud.model.services;

import cloud.configuration.Config;

public class NetworkServiceCreator implements IServiceCreator {

    private String name;
    private String type;
    private int requests;
    private int data;
    private int dataOut;
    private int zones;

    public NetworkServiceCreator(String name, String type, int requests, int data, int dataOut, int zones) {
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
            return new NetworkService(name, type, requests, data, 0, 0);
        } else if (type.equals(types[3])) {
            return new NetworkService(name, type, requests, data, dataOut, 0);
        } else if (type.equals(types[4])) {
            return new NetworkService(name, type, requests, 0, 0, zones);
        } else {
            return null;
        }
    }
}
