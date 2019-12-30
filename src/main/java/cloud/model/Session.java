package cloud.model;

import java.util.ArrayList;

public class Session {

    private ArrayList<Component> components;
    private Component selectedComponent;

    public Session() {
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

    public void openSession() {
        // TODO: open session from file
    }

    public void saveSession() {
        // TODO: save session to file
    }
}
