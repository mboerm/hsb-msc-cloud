package cloud.constants;

public final class Consts {

    public static final String APP_TITLE = "Cloud Architecture Optimizer";
    public static final int WINDOW_DEFAULT_WIDTH = 1024;
    public static final int WINDOW_DEFAULT_HEIGHT = 768;
    public static final int WINDOW_MIN_WIDTH = 800;
    public static final int WINDOW_MIN_HEIGHT = 600;

    public static final String ABOUT_TEXT =
            "This tool is the result of the master thesis.";


    private Consts(){
        /*
        this prevents even the native class from
        calling this actor as well :
        */
        throw new AssertionError();
    }
}
