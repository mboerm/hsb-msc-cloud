package cloud.model;

import static cloud.constants.Consts.*;

public class Component {

    private String name;
    private String service;
    private COMPONENT_CATEGORIES category;
    private COMPONENT_USAGE usage;

    Component() {
        name = "Default";
        service = "Default";
        category = COMPONENT_CATEGORIES.None;
        usage = COMPONENT_USAGE.On_Premise;
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

    public COMPONENT_CATEGORIES getCategory() {
        return this.category;
    }
    public void setCategory(COMPONENT_CATEGORIES category) {
        this.category = category;
    }

    public COMPONENT_USAGE getUsage() {
        return this.usage;
    }
    public void setUsage(COMPONENT_USAGE usage) {
        this.usage = usage;
    }
}
