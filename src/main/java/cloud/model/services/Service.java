package cloud.model.services;

public abstract class Service {
    private String name;
    private String service;
    private String providerService;
    private String category;
    private String region;
    private String usageType;
    private String usagePeriod;
    private String usagePrepay;
    private String opMode;

    public Service() {
        setName("");
        setService("");
        setProviderService("");
        setCategory("");
        setRegion("");
        setUsageType("");
        setUsagePeriod("");
        setUsagePrepay("");
        setOpMode("");
    }

    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getService() {
        return this.service;
    }
    public void setService(String service) {
        this.service = service;
    }

    public String getProviderService() {
        return this.providerService;
    }
    public void setProviderService(String match) {
        this.providerService = match;
    }

    public String getCategory() {
        return this.category;
    }
    public void setCategory(String category) {
        this.category = category;
    }

    public String getRegion() {return this.region;}
    public void setRegion(String region) {this.region = region;}

    public String getUsageType() {
        return this.usageType;
    }
    public void setUsageType(String usage) {
        this.usageType = usage;
    }

    public String getUsagePeriod() {
        return usagePeriod;
    }
    public void setUsagePeriod(String usagePeriod) {
        this.usagePeriod = usagePeriod;
    }

    public String getUsagePrepay() {
        return usagePrepay;
    }
    public void setUsagePrepay(String usagePrepay) {
        this.usagePrepay = usagePrepay;
    }

    public String getOpMode() {
        return this.opMode;
    }
    public void setOpMode(String mode) {
        this.opMode = mode;
    }
}
