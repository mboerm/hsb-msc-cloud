package cloud.model.services;

class NetworkService extends Service {

    private String networkType;
    private String requests;
    private String data;
    private String dataOut;
    private String zones;

    public NetworkService(String name, String type, String requests, String data, String dataOut, String zones) {
        setName(name);
        setCategory("Network");
        setService(type);
        setNetworkType(type);
        setRequests(requests);
        setData(data);
        setDataOut(dataOut);
        setZones(zones);
    }

    public String getNetworkType() {
        return networkType;
    }
    public void setNetworkType(String type) {
        this.networkType = type;
    }

    public String getRequests() {
        return requests;
    }
    public void setRequests(String requests) {
        this.requests = requests;
    }

    public String getData() {
        return data;
    }
    public void setData(String data) {
        this.data = data;
    }

    public String getDataOut() {
        return dataOut;
    }
    public void setDataOut(String dataOut) {
        this.dataOut = dataOut;
    }

    public String getZones() {
        return zones;
    }
    public void setZones(String zones) {
        this.zones = zones;
    }
}
