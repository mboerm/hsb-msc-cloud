package cloud.constants;

public final class Consts {

    public static final String APP_TITLE = "Cloud Architecture Optimizer";
    public static final int WINDOW_DEFAULT_WIDTH = 800;
    public static final int WINDOW_DEFAULT_HEIGHT = 600;
    public static final int WINDOW_MIN_WIDTH = 640;
    public static final int WINDOW_MIN_HEIGHT = 480;

    public enum COMPONENT_CATEGORIES {None, Compute, Storage, Database, Analytics, Network, Integration, Management};
    public enum INSTANCE_TYPES {OnDemand, Reserved, Spot};
    public enum DATABASE_TYPES {SQL, MariaDB, NoSQL, Document};
    public enum COMPONENT_USAGE {On_Premise, Off_Premise};

    public static final String ABOUT_TEXT =
            "This tool is the result of the master thesis.";

    public static final String CONFIG_FILE = "config.xml";

    private Consts(){
        /*
        this prevents even the native class from
        calling this actor as well :
        */
        throw new AssertionError();
    }
}
