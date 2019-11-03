package cloud.components;

public class Component {

    private String name;
    private componentCategory category;
    protected enum componentCategory {DEFAULT, VM, STORAGE, DATABASE};

    Component() {
        name = "Default";
        category = componentCategory.DEFAULT;
    }

    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public componentCategory getCategory() {
        return this.category;
    }
    public void setCategory(componentCategory category) {
        this.category = category;
    }
}
