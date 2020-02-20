package cloud.model.services;

import cloud.configuration.Config;
import javafx.util.Pair;

public class AnalyticServiceCreator implements IServiceCreator {

    private String name;
    private String type;
    private int data;
    private int dataOut;
    private Pair<Integer, Integer> num;
    private int units;
    private String instanceType;

    public AnalyticServiceCreator(String name, String type, int data, int dataOut, Pair<Integer, Integer> num, int units, String instanceType) {
        this.name = name;
        this.type = type;
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

        if (type.equals(types[0]) || type.equals(types[1])) {
            // Data Query & Data Share
            analyticService = new AnalyticService(name, type, data, 0, new Pair<>(0, 0), 0, "");
        } else if (type.equals(types[2])) {
            // Data Transfer
            analyticService = new AnalyticService(name, type, 0, 0, num, 0, "");
        } else if (type.equals(types[3])) {
            // Data Stream
            analyticService = new AnalyticService(name, type, data, 0, new Pair<>(0, 0), units, "");
        } else if (type.equals(types[4]) || type.equals(types[5])) {
            // Data Catalog & Data Lake
            analyticService = new AnalyticService(name, type, data, 0, new Pair<>(0,0), units, "");
        } else if (type.equals(types[6])) {
            // Search Engine
            analyticService = new AnalyticService(name, type, data, dataOut, num, units, instanceType);
        } else {
            return null;
        }
        analyticService.setCategory(Config.getInstance().getConfigValuesAsArray("service-categories")[3]);
        analyticService.setIdentifier(ServiceChecker.getInstance().getServiceIdentifier(analyticService.getCategory(), type));
        return analyticService;
    }
}
