package cloud.model.provider;

public abstract class Provider {

    private String serviceName;
    private String shortName;
    private String priceFile;
    private String freeFile;

    public String getServiceName() {
        return this.serviceName;
    }
    void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getShortName() {
        return shortName;
    }
    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getPriceFile() {
        return priceFile;
    }
    public void setPriceFile(String priceFile) {
        this.priceFile = priceFile;
    }

    public String getFreeFile() {
        return freeFile;
    }
    public void setFreeFile(String freeFile) {
        this.freeFile = freeFile;
    }
}
