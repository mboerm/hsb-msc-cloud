package cloud.configuration;

import java.text.DecimalFormat;

public final class Constants {
    private Constants(){
        /*
        this prevents even the native class from
        calling this actor as well :
        */
        throw new AssertionError();
    }

    public static final String APP_TITLE = "Cloud Architecture Optimizer";
    public static final int WINDOW_MIN_WIDTH = 900;
    public static final int WINDOW_MIN_HEIGHT = 500;

    public static final String CONFIG_FILE = "xml/config.xml";
    public static final String CONFIG_SEPARATOR = ";";

    public static final DecimalFormat DF2 = new DecimalFormat("#.00");
}
