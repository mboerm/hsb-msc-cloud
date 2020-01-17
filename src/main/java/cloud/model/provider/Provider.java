package cloud.model.provider;

abstract class Provider {

    private String serviceName;
    private String serviceFile;
    private String priceFile;
    private String freeFile;

    public String getServiceName() {
        return this.serviceName;
    }
    void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceFile() {
        return serviceFile;
    }
    public void setServiceFile(String serviceFile) {
        this.serviceFile = serviceFile;
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
