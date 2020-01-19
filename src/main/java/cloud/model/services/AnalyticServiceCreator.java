package cloud.model.services;

import cloud.configuration.Config;
import javafx.util.Pair;

public class AnalyticServiceCreator implements ServiceAbstractCreator {

    private String name;
    private String type;
    private String data;
    private String dataOut;
    private Pair<String,String> activities;
    private String units;
    private String instanceSize;

    public AnalyticServiceCreator(String name, String type, String data, String dataOut, Pair<String,String> activities, String units, String instanceSize) {
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
            return new AnalyticService(name, type, data, "", new Pair<>("", ""), "", "");
        } else if (type.equals(types[2])) {
            return new AnalyticService(name, type, "", "", activities, "", "");
        } else if (type.equals(types[3]) || type.equals(types[4])) {
            return new AnalyticService(name, type, data, "", new Pair<>("",""), units, "");
        } else if (type.equals(types[5])) {
            return new AnalyticService(name, type, data, dataOut, new Pair<>("",""), units, instanceSize);
        } else {
            return null;
        }
    }
}
