package cloud.model;

import cloud.model.components.Component;

import java.util.ArrayList;

public class Design {

    private ArrayList<Component> components;
    private Component selectedComponent;

    public Design() {
        components = new ArrayList<>();
    }

    public void addComponent(Component comp) {
        this.components.add(comp);
    }

    public void removeComponent(Component comp) {
        this.components.remove(comp);
    }

    public void clearComponents() {
        this.components.clear();
    }

    public Component getSelectedComponent() {
        return this.selectedComponent;
    }
    public void setSelectedComponent(Component comp) {
        this.selectedComponent = comp;
    }
}
