package cloud.model.services;

import cloud.configuration.Config;

public class NetworkService extends Service {

    private String networkType;
    private Integer requests;
    private Integer data;
    private Integer dataOut;
    private Integer zones;

    public NetworkService(String name, String type, Integer requests, Integer data, Integer dataOut, Integer zones) {
        setName(name);
        setCategory(Config.getInstance().getConfigValuesAsArray("service-categories")[4]);
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

    public Integer getRequests() {
        return requests;
    }
    public void setRequests(Integer requests) {
        this.requests = requests;
    }

    public Integer getData() {
        return data;
    }
    public void setData(Integer data) {
        this.data = data;
    }

    public Integer getDataOut() {
        return dataOut;
    }
    public void setDataOut(Integer dataOut) {
        this.dataOut = dataOut;
    }

    public Integer getZones() {
        return zones;
    }
    public void setZones(Integer zones) {
        this.zones = zones;
    }
}
