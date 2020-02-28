package cloud.model.services;

/**
 * Abstract service class
 */
public abstract class Service {
    private String name;
    private String identifier;
    private String providerService;
    private String category;
    private String location;
    private String usageType;
    private String usagePeriod;
    private String usagePrepay;
    private String opMode;

    public Service() {
        this.setName("");
        this.setIdentifier("");
        this.setProviderService("");
        this.setCategory("");
        this.setLocation("");
        this.setUsageType("");
        this.setUsagePeriod("");
        this.setUsagePrepay("");
        this.setOpMode("");
    }

    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getIdentifier() {
        return this.identifier;
    }
    public void setIdentifier(String id) {
        this.identifier = id;
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

    public String getLocation() {return this.location;}
    public void setLocation(String location) {this.location = location;}

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

    /**
     * Get general properties of service
     * @return string array
     */
    public String[] getGeneralProperties() {
        return new String[] {
                this.getName() == null ? "-" : this.getName(),
                this.getCategory(),
                this.getProviderService(),
                this.getIdentifier()
        };
    }

    /**
     * Get usage properties of service
     * @return string array
     */
    public String[] getUsageProperties() {
        return new String[] {
                this.getLocation() == null ? "-" : this.getLocation(),
                this.getUsageType() == null ? "-" : this.getUsageType(),
                this.getUsagePeriod() == null ? "-" : this.getUsagePeriod(),
                this.getUsagePrepay() == null ? "-" : this.getUsagePrepay(),
                this.getOpMode() == null ? "-" : this.getOpMode(),
        };
    }
}
