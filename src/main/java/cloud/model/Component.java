package cloud.model;

import static cloud.constants.Consts.*;

public class Component {

    private String name;
    private String service;
    private COMPONENT_CATEGORY category;
    private COMPONENT_INSTANCE_SIZE size;
    private COMPONENT_USAGE_TYPE usageType;
    private COMPONENT_USAGE_PERIOD usagePeriod;
    private COMPONENT_USAGE_PREPAY usagePrepay;
    private COMPONENT_OPERATING_MODE opMode;

    Component() {
        setName("Default");
        setService("Default");
        setCategory(COMPONENT_CATEGORY.None);
        setSize(COMPONENT_INSTANCE_SIZE.Medium);
        setUsageType(COMPONENT_USAGE_TYPE.On_Demand);
        setUsagePeriod(COMPONENT_USAGE_PERIOD.Monthly);
        setUsagePrepay(COMPONENT_USAGE_PREPAY.None);
        setOpMode(COMPONENT_OPERATING_MODE.On_Premise);
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

    public COMPONENT_CATEGORY getCategory() {
        return this.category;
    }
    public void setCategory(COMPONENT_CATEGORY category) {
        this.category = category;
    }

    public COMPONENT_INSTANCE_SIZE getSize() {
        return size;
    }
    public void setSize(COMPONENT_INSTANCE_SIZE size) {
        this.size = size;
    }

    public COMPONENT_USAGE_TYPE getUsageType() {
        return this.usageType;
    }
    public void setUsageType(COMPONENT_USAGE_TYPE usage) {
        this.usageType = usage;
    }

    public COMPONENT_USAGE_PERIOD getUsagePeriod() {
        return usagePeriod;
    }
    public void setUsagePeriod(COMPONENT_USAGE_PERIOD usagePeriod) {
        this.usagePeriod = usagePeriod;
    }

    public COMPONENT_USAGE_PREPAY getUsagePrepay() {
        return usagePrepay;
    }
    public void setUsagePrepay(COMPONENT_USAGE_PREPAY usagePrepay) {
        this.usagePrepay = usagePrepay;
    }

    public COMPONENT_OPERATING_MODE getOpMode() {
        return this.opMode;
    }
    public void setOpMode(COMPONENT_OPERATING_MODE mode) {
        this.opMode = mode;
    }
}
