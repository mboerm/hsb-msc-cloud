package cloud.config;

import cloud.log.Logger;

public class AWSServices {
    private Logger _log = Logger.getInstance();

    private static volatile AWSServices INSTANCE = null;

    public static AWSServices getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AWSServices();
        }
        return INSTANCE;
    }

    /**
     * Get properties of service
     * @param serviceName
     * @return values as string
     */
    private String getAWSService(String serviceName) {
        return "";
    }
}


