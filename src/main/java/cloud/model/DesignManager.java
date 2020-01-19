package cloud.model;

public class DesignManager {

    private static DesignManager instance;
    private static Design design;

    /** Private constructor to prevent class to be instantiated from public */
    private DesignManager() {
    }

    /** Singleton instance */
    public static DesignManager getInstance() {
        if (instance == null) {
            instance = new DesignManager();
        }
        return instance;
    }

    public Design getDesign() {
        return this.design;
    }
    public void setDesign(Design design) {
        this.design = design;
    }
}
