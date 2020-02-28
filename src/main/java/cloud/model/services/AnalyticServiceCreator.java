package cloud.model.services;

import cloud.configuration.Config;
import javafx.util.Pair;

/**
 * Analytic service creator
 */
public class AnalyticServiceCreator implements ServiceCreator {

    private String name;
    private String analyticType;
    private int data;
    private int dataOut;
    private Pair<Integer, Integer> num;
    private int units;
    private String instanceType;

    /**
     * Constructor
     * @param name name of service
     * @param analyticType analytic type
     * @param data number of data
     * @param dataOut number of outgoing data
     * @param num num pair
     * @param units number of units
     * @param instanceType instance type
     */
    public AnalyticServiceCreator(String name, String analyticType, int data, int dataOut, Pair<Integer, Integer> num, int units, String instanceType) {
        this.name = name;
        this.analyticType = analyticType;
        this.data = data;
        this.dataOut = dataOut;
        this.num = num;
        this.units = units;
        this.instanceType = instanceType;
    }

    @Override
    public Service createService() {
        String[] types = Config.getInstance().getConfigValuesAsArray("analytic-type");

        AnalyticService analyticService;

        if (analyticType.equals(types[0]) || analyticType.equals(types[1])) {
            // Data Query & Data Share
            analyticService = new AnalyticService(name, analyticType, data, 0, new Pair<>(0, 0), 0, "");
        } else if (analyticType.equals(types[2])) {
            // Data Transfer
            analyticService = new AnalyticService(name, analyticType, 0, 0, num, 0, "");
        } else if (analyticType.equals(types[3])) {
            // Data Stream
            analyticService = new AnalyticService(name, analyticType, data, 0, new Pair<>(0, 0), units, "");
        } else if (analyticType.equals(types[4]) || analyticType.equals(types[5])) {
            // Data Catalog & Data Lake
            analyticService = new AnalyticService(name, analyticType, data, 0, new Pair<>(0,0), units, "");
        } else if (analyticType.equals(types[6])) {
            // Search Engine
            analyticService = new AnalyticService(name, analyticType, data, dataOut, num, units, instanceType);
        } else {
            return null;
        }
        /* set category of service */
        analyticService.setCategory(Config.getInstance().getConfigValuesAsArray("service-categories")[3]);
        /* set identifier of service */
        analyticService.setIdentifier(ServiceChecker.getInstance().getServiceIdentifier(analyticService.getCategory(), analyticType));
        return analyticService;
    }
}
