package cloud.provider;

public class Provider {

    private String name = "";
    private String serviceName = "";

    void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }

    void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
    public String getServiceName() {
        return this.serviceName;
    }
}
