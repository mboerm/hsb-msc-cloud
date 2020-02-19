package cloud.model.services;

import cloud.configuration.Config;
import javafx.util.Pair;

public class StorageService extends Service {

    private String storageType;
    private String mode;
    private int capacity;
    private int data;
    private Pair<Integer, Integer> requests;
    private int queries;
    private int rate;

    public StorageService(String name, String storageType, String mode, int capacity, int data, Pair<Integer, Integer> requests, int queries, int rate) {
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

    public int getCapacity() {
        return capacity;
    }
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getData() {
        return data;
    }
    public void setData(int data) {
        this.data = data;
    }

    public Pair<Integer, Integer> getRequests() {
        return requests;
    }
    public void setRequests(Pair<Integer, Integer> requests) {
        this.requests = requests;
    }

    public int getQueries() {
        return queries;
    }
    public void setQueries(int queries) {
        this.queries = queries;
    }

    public int getRate() {
        return rate;
    }
    public void setRate(int rate) {
        this.rate = rate;
    }
}
