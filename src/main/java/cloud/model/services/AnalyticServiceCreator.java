package cloud.model.services;

import cloud.configuration.Config;
import javafx.util.Pair;

public class AnalyticServiceCreator implements IServiceCreator {

    private String name;
    private String type;
    private Integer data;
    private Integer dataOut;
    private Pair<Integer,Integer> activities;
    private Integer units;
    private String instanceSize;

    public AnalyticServiceCreator(String name, String type, Integer data, Integer dataOut, Pair<Integer,Integer> activities, Integer units, String instanceSize) {
        this.name = name;
        this.type = type;
        this.data = data;
        this.dataOut = dataOut;
        this.activities = activities;
        this.units = units;
        this.instanceSize = instanceSize;
    }

    @Override
    public Service createService() {
        String[] types = Config.getInstance().getConfigValuesAsArray("analytic-type");

        if (type.equals(types[0]) || type.equals(types[1])) {
            return new AnalyticService(name, type, data, 0, new Pair<>(0, 0), 0, "");
        } else if (type.equals(types[2])) {
            return new AnalyticService(name, type, 0, 0, activities, 0, "");
        } else if (type.equals(types[3]) || type.equals(types[4])) {
            return new AnalyticService(name, type, data, 0, new Pair<>(0,0), units, "");
        } else if (type.equals(types[5])) {
            return new AnalyticService(name, type, data, dataOut, new Pair<>(0,0), units, instanceSize);
        } else {
            return null;
        }
    }
}
