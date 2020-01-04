package cloud.constants;

public final class Consts {

    public static final String APP_TITLE = "Cloud Architecture Optimizer";
    public static final int WINDOW_MIN_WIDTH = 640;
    public static final int WINDOW_MIN_HEIGHT = 480;

    public enum COMPONENT_CATEGORY {None, Compute, Storage, Database, Analytics, Network, Integration, Management};
    public enum COMPONENT_INSTANCE_SIZE {Micro, Small, Medium, Large, XLarge};
    public enum COMPONENT_USAGE_TYPE {On_Demand, Reserved, Spot};
    public enum COMPONENT_USAGE_PERIOD {Hourly, Monthly, One_Year, Three_Years};
    public enum COMPONENT_USAGE_PREPAY {None, Partial, Full};
    public enum COMPONENT_REGION {};
    public enum COMPONENT_OPERATING_MODE {On_Premise, Off_Premise};

    public enum COMPUTE_INSTANCE_TYPE {General, Compute, RAM, Storage, Burst, GPU}

    public enum DATABASE_INSTANCE_TYPE {Standard, Storage};
    public enum DATABASE_TYPE {SQL, MariaDB, NoSQL, Document};

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
