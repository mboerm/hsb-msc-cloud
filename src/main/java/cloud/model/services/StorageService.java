package cloud.model.services;

class StorageService extends Service {

    private String type;
    private String mode;
    private String capacity;
    private String data;
    private String requests;
    private String queries;
    private String rate;

    public StorageService(String name, String type, String mode, String capacity, String data, String requests, String queries, String rate) {
        setName(name);
        setCategory("Storage");
        setStorageType(type);
        setMode(mode);
        setCapacity(capacity);
        setData(data);
        setRequests(requests);
        setQueries(queries);
        setRate(rate);
    }

    public String getStorageType() {
        return type;
    }
    public void setStorageType(String type) {
        this.type = type;
    }

    public String getMode() {
        return mode;
    }
    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getCapacity() {
        return capacity;
    }
    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getData() {
        return data;
    }
    public void setData(String data) {
        this.data = data;
    }

    public String getRequests() {
        return requests;
    }
    public void setRequests(String requests) {
        this.requests = requests;
    }

    public String getQueries() {
        return queries;
    }
    public void setQueries(String queries) {
        this.queries = queries;
    }

    public String getRate() {
        return rate;
    }
    public void setRate(String rate) {
        this.rate = rate;
    }
}
