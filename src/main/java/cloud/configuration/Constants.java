package cloud.configuration;

import java.text.*;
import java.util.Date;

public final class Constants {
    private Constants(){
        /*
        this prevents even the native class from
        calling this actor as well :
        */
        throw new AssertionError();
    }

    public static final String APP_TITLE = "Cloud Architecture Optimizer";
    public static final String DIALOG_SERVICE_TITLE = "Service Dialog";
    public static final String DIALOG_SERVICE_HEADER = "Select service category and define properties";
    public static final String DIALOG_COSTS_TITLE = "Cost Calculation Report";
    public static final int WINDOW_MIN_WIDTH = 900;
    public static final int WINDOW_MIN_HEIGHT = 500;
    public static final int DIALOG_MIN_WIDTH = 400;
    public static final int DIALOG_MIN_HEIGHT = 200;

    public static final String CONFIG_FILE = "xml/config.xml";
    public static final String CONFIG_SEPARATOR = ";";

    public static final DecimalFormat DOUBLE_FORMAT_2 = new DecimalFormat("#0.00");
    public static final DateFormat DATE_FORMAT_FILE = new SimpleDateFormat("dd-MM-yy_HH-mm-ss");
    public static final DateFormat DATE_FORMAT_TITLE = new SimpleDateFormat("d. MMMM yyyy");
//            DateFormat.getDateInstance(DateFormat.MEDIUM);

    public static final int HOUR_SECONDS = 3600;
    public static final int MONTH_SECONDS = 2628000;
    public static final int MONTH_HOURS = 730;
    public static final int MONTH_DAYS = 30;
    public static final int DATA_FACTOR = 1024;
    public static final double K_FACTOR = Math.pow(10,3);
    public static final double M_FACTOR = Math.pow(10,6);
}
