package cloud.model.services;

import cloud.configuration.Config;

public class ServiceChecker {
    private static String[] serviceTypes = Config.getInstance().getConfigValuesAsArray("service-categories");

    public static boolean isComputeItem(String item) {
        return item.equals(serviceTypes[0]);
    }

    public static boolean isDatabaseItem(String item) {
        return item.equals(serviceTypes[1]);
    }

    public static boolean isStorageItem(String item) {
        return item.equals(serviceTypes[2]);
    }

    public static boolean isAnalyticItem(String item) {
        return item.equals(serviceTypes[3]);
    }

    public static boolean isNetworkItem(String item) {
        return item.equals(serviceTypes[4]);
    }

    public static boolean isIntegrationItem(String item) {
        return item.equals(serviceTypes[5]);
    }

    public static boolean isMonitoringItem(String item) {
        return item.equals(serviceTypes[6]);
    }
}
