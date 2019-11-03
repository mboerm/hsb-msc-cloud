package cloud.model;

import java.util.ArrayList;

public class Session {

    private ArrayList<Component> components;

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

    public void openSession() {
        // TODO: open session from file
    }

    public void saveSession() {
        // TODO: save session to file
    }
}
