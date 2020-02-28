package cloud.configuration;

import java.text.*;

/**
 * Constants class
 */
public final class Constants {
    private Constants(){
        /*
        this prevents even the native class from
        calling this actor as well :
        */
        throw new AssertionError();
    }

    /* Window and dialog size */
    public static final int WINDOW_MIN_WIDTH = 900;
    public static final int WINDOW_MIN_HEIGHT = 500;
    public static final int DIALOG_MIN_WIDTH = 400;
    public static final int DIALOG_MIN_HEIGHT = 200;

    /* Path to config file and separator */
    public static final String CONFIG_FILE = "xml/config.xml";
    public static final String CONFIG_SEPARATOR = ";";

    /* Decimal and Date format */
    public static final DecimalFormat DOUBLE_FORMAT_2 = new DecimalFormat("#0.00");
    public static final DateFormat DATE_FORMAT_FILE = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
    public static final DateFormat DATE_FORMAT_TITLE = new SimpleDateFormat("d. MMMM yyyy");

    /* Cost calculation factors */
    public static final int HOUR_SECONDS = 3600;
    public static final int MONTH_SECONDS = 2628000;
    public static final int MONTH_HOURS = 730;
    public static final int MONTH_DAYS = 30;
    public static final int DATA_FACTOR = 1024;
    public static final double K_FACTOR = Math.pow(10,3);
    public static final double M_FACTOR = Math.pow(10,6);
}
