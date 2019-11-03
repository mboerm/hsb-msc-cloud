package cloud.design;

import cloud.components.Component;

import java.util.ArrayList;

public class DesignSession {

    private ArrayList<Component> components;

    DesignSession() {

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

    public void saveSession() {
        // TODO: save session to file
    }
}