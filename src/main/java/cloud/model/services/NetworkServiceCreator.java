package cloud.model.services;

import cloud.configuration.Config;

/**
 * Network service creator
 */
public class NetworkServiceCreator implements ServiceCreator {

    private String name;
    private String networkType;
    private int requests;
    private int data;
    private int dataOut;
    private int zones;

    /**
     * Constructor
     * @param name name of service
     * @param networkType network type
     * @param requests number of requests
     * @param data number of data
     * @param dataOut number of outgoing data
     * @param zones number of zones
     */
    public NetworkServiceCreator(String name, String networkType, int requests, int data, int dataOut, int zones) {
        this.name = name;
        this.networkType = networkType;
        this.requests = requests;
        this.data = data;
        this.dataOut = dataOut;
        this.zones = zones;
    }

    @Override
    public Service createService() {
        String[] types = Config.getInstance().getConfigValuesAsArray("network-type");

        NetworkService networkService;

        if (networkType.equals(types[0])) {
            // VPC
            networkService = new NetworkService(name, networkType, requests, data, dataOut, zones);
        } else if (networkType.equals(types[1])) {
            // VPN
            networkService = new NetworkService(name, networkType, requests, data, dataOut, 0);
        } else if (networkType.equals(types[2])) {
            // API
            networkService = new NetworkService(name, networkType, requests, data, 0, 0);
        } else if (networkType.equals(types[3])) {
            // CDN
            networkService = new NetworkService(name, networkType, requests, data, dataOut, 0);
        } else if (networkType.equals(types[4])) {
            // DNS
            networkService = new NetworkService(name, networkType, requests, 0, 0, zones);
        } else {
            return null;
        }
        /* set category of service */
        networkService.setCategory(Config.getInstance().getConfigValuesAsArray("service-categories")[4]);
        /* set identifier of service */
        networkService.setIdentifier(ServiceChecker.getInstance().getServiceIdentifier(networkService.getCategory(), networkType));
        return networkService;
    }
}
