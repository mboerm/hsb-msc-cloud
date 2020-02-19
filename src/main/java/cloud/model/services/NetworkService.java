package cloud.model.services;

import cloud.configuration.Config;

public class NetworkService extends Service {

    private String networkType;
    private int requests;
    private int data;
    private int dataOut;
    private int zones;

    public NetworkService(String name, String type, int requests, int data, int dataOut, int zones) {
        setName(name);
        setIdentifier(type);
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

    public int getRequests() {
        return requests;
    }
    public void setRequests(int requests) {
        this.requests = requests;
    }

    public int getData() {
        return data;
    }
    public void setData(int data) {
        this.data = data;
    }

    public int getDataOut() {
        return dataOut;
    }
    public void setDataOut(int dataOut) {
        this.dataOut = dataOut;
    }

    public int getZones() {
        return zones;
    }
    public void setZones(int zones) {
        this.zones = zones;
    }
}
