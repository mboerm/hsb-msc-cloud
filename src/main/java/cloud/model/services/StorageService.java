package cloud.model.services;

import javafx.util.Pair;

/**
 * Storage service class
 */
public class StorageService extends Service {

    private String storageType;
    private String mode;
    private int capacity;
    private int data;
    private Pair<Integer, Integer> requests;
    private int rate;

    public StorageService(String name, String storageType, String mode, int capacity, int data, Pair<Integer, Integer> requests, int rate) {
        setName(name);
        setStorageType(storageType);
        setStorageMode(mode);
        setCapacity(capacity);
        setData(data);
        setRequests(requests);
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

    public int getRate() {
        return rate;
    }
    public void setRate(int rate) {
        this.rate = rate;
    }
}
