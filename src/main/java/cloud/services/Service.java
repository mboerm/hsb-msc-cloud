package cloud.services;

public class Service {
    private String name;
    private serviceCategory category;
    protected enum serviceCategory {DEFAULT, COMPUTE, STORAGE, DATABASE, NETWORK, MANAGEMENT, INTEGRATION, ANALYTICS};

    Service() {
        setName("Default");
        setCategory(serviceCategory.DEFAULT);
    }

    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public serviceCategory getCategory() {
        return this.category;
    }
    public void setCategory(serviceCategory category) {
        this.category = category;
    }
}
