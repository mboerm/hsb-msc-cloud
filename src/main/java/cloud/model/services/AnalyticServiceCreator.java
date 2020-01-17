package cloud.model.services;

import javafx.util.Pair;

public class AnalyticServiceCreator implements ServiceAbstractCreator {

    private String name;
    private String type;
    private String data;
    private String dataOut;
    private Pair<String,String> activities;
    private String units;

    public AnalyticServiceCreator(String name, String type, String data, String dataOut, Pair activities, String units) {
        this.name = name;
        this.type = type;
        this.data = data;
        this.dataOut = dataOut;
        this.activities = activities;
        this.units = units;
    }

    @Override
    public Service createService() {
        return new AnalyticService(name, type, data, dataOut, activities, units);
    }
}
