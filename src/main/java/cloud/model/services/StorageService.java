package cloud.model.services;

import cloud.configuration.Config;
import javafx.util.Pair;

public class StorageService extends Service {

    private String storageType;
    private String mode;
    private Integer capacity;
    private Integer data;
    private Pair<Integer, Integer> requests;
    private Integer queries;
    private Integer rate;

    public StorageService(String name, String storageType, String mode, Integer capacity, Integer data, Pair<Integer, Integer> requests, Integer queries, Integer rate) {
        setName(name);
        setCategory(Config.getInstance().getConfigValuesAsArray("service-categories")[2]);
        setIdentifier(storageType);
        setStorageType(storageType);
        setStorageMode(mode);
        setCapacity(capacity);
        setData(data);
        setRequests(requests);
        setQueries(queries);
        setRate(rate);
    }

    public String getStorageType() {
        return storageType;
    }
    public void setStorageType(String type) {
        this.storageType = type;
    }

    public String getStorageMode() {
        return mode;
    }
    public void setStorageMode(String mode) {
        this.mode = mode;
    }

    public Integer getCapacity() {
        return capacity;
    }
    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getData() {
        return data;
    }
    public void setData(Integer data) {
        this.data = data;
    }

    public Pair<Integer, Integer> getRequests() {
        return requests;
    }
    public void setRequests(Pair<Integer, Integer> requests) {
        this.requests = requests;
    }

    public Integer getQueries() {
        return queries;
    }
    public void setQueries(Integer queries) {
        this.queries = queries;
    }

    public Integer getRate() {
        return rate;
    }
    public void setRate(Integer rate) {
        this.rate = rate;
    }

    @Override
    public String[] getSpecificProperties() {
        return new String[0];
    }
}
