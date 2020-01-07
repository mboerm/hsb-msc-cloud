package cloud.constants;

public final class Constants {

    public static final String APP_TITLE = "Cloud Architecture Optimizer";
    public static final int WINDOW_MIN_WIDTH = 640;
    public static final int WINDOW_MIN_HEIGHT = 480;

    public static final String ABOUT_TEXT =
            "This tool is the result of the master thesis.";

    public static final String CONFIG_FILE = "config.xml";
    public static final String STRING_SEPARATOR = ",";

    private Constants(){
        /*
        this prevents even the native class from
        calling this actor as well :
        */
        throw new AssertionError();
    }
}
