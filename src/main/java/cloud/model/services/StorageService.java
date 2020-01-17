package cloud.model.services;

class StorageService extends Service {

    private String type;
    private String mode;
    private String capacity;
    private String data;
    private String rate;
    private String requests;

    public StorageService() {
        setCategory("Storage");
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
}
