package cloud.model;

import cloud.log.Logger;

public class Services {
    private Logger _log = Logger.getInstance();

    private static volatile Services INSTANCE = null;

    public static Services getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Services();
        }
        return INSTANCE;
    }

    /**
     * Get properties of service
     * @param serviceName
     * @return values as string
     */
    private String getService(String serviceName) {
        return "";
    }
}


