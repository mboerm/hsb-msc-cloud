package cloud.model;

import static cloud.constants.Consts.*;

public class Component {

    private String name;
    private COMPONENT_CATEGORIES category;

    Component() {
        name = "Default";
        category = COMPONENT_CATEGORIES.DEFAULT;
    }

    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public COMPONENT_CATEGORIES getCategory() {
        return this.category;
    }
    public void setCategory(COMPONENT_CATEGORIES category) {
        this.category = category;
    }
}
