package cloud.model.components;

import static cloud.constants.Constants.*;

public class Component {

    private String name;
    private String service;
    private String category;
    private String size;
    private String usageType;
    private String usagePeriod;
    private String usagePrepay;
    private String opMode;

    Component() {
        setName("Default");
        setService("Default");
        setCategory("Default");
        setSize("Medium");
        setUsageType("On-Demand");
        setUsagePeriod("Monthly");
        setUsagePrepay("None");
        setOpMode("On-Premise");
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

    public String getCategory() {
        return this.category;
    }
    public void setCategory(String category) {
        this.category = category;
    }

    public String getSize() {
        return size;
    }
    public void setSize(String size) {
        this.size = size;
    }

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
