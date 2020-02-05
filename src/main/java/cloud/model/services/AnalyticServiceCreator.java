package cloud.model.services;

import cloud.configuration.Config;
import javafx.util.Pair;

public class AnalyticServiceCreator implements IServiceCreator {

    private String name;
    private String type;
    private Integer data;
    private Integer dataOut;
    private Pair<Integer,Integer> num;
    private Integer units;
    private String instanceType;
    private String instanceSize;

    public AnalyticServiceCreator(String name, String type, Integer data, Integer dataOut, Pair<Integer,Integer> num, Integer units, String instanceType, String instanceSize) {
        this.name = name;
        this.type = type;
        this.data = data;
        this.dataOut = dataOut;
        this.num = num;
        this.units = units;
        this.instanceType = instanceType;
        this.instanceSize = instanceSize;
    }

    @Override
    public Service createService() {
        String[] types = Config.getInstance().getConfigValuesAsArray("analytic-type");

        if (type.equals(types[0]) || type.equals(types[1])) {
            // Data Query & Data Share
            return new AnalyticService(name, type, data, 0, new Pair<>(0, 0), 0, "", "");
        } else if (type.equals(types[2])) {
            // Data Transfer
            return new AnalyticService(name, type, 0, 0, num, 0, "", "");
        } else if (type.equals(types[3])) {
            // Data Stream
            return new AnalyticService(name, type, data, 0, new Pair<>(0, 0), units, "", "");
        } else if (type.equals(types[4]) || type.equals(types[5])) {
            // Data Catalog & Data Lake
            return new AnalyticService(name, type, data, 0, new Pair<>(0,0), units, "", "");
        } else if (type.equals(types[6])) {
            // Search Engine
            return new AnalyticService(name, type, data, dataOut, num, units, instanceType, instanceSize);
        } else {
            return null;
        }
    }
}
