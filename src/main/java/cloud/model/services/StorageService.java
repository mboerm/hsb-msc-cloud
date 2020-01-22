package cloud.model.services;

public class StorageService extends Service {

    private String storageType;
    private String mode;
    private Integer capacity;
    private Integer data;
    private Integer requests;
    private Integer queries;
    private Integer rate;

    public StorageService(String name, String storageType, String mode, Integer capacity, Integer data, Integer requests, Integer queries, Integer rate) {
        setName(name);
        setCategory("Storage");
        setDisplayName(storageType);
        setStorageType(storageType);
        setMode(mode);
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

    public String getMode() {
        return mode;
    }
    public void setMode(String mode) {
        this.mode = mode;
    }

    public Integer Integer() {
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

    public Integer getRequests() {
        return requests;
    }
    public void setRequests(Integer requests) {
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
}
