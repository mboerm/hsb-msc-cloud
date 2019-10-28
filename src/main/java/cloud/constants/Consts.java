package cloud.constants;

public final class Consts {

    public static final String TITLE = "Cloud Architecture Optimizer";

    public static final String ABOUT =
            "This tool is the result of the master thesis.";


    private Consts(){
        //this prevents even the native class from
        //calling this ctor as well :
        throw new AssertionError();
    }
}
