package cloud.model.services;

import javafx.util.Pair;

class AnalyticService extends Service {

    private String type;
    private String data;
    private String dataOut;
    private Pair<String,String> activities;
    private String units;

    public AnalyticService(String name, String type, String data, String dataOut, Pair activities, String units) {
        setName(name);
        setCategory("Analytics");
        setAnalyticType(type);
        setData(data);
        setDataOut(dataOut);
        setActivities(activities);
        setUnits(units);
    }

    public String getAnalyticType() {
        return type;
    }
    public void setAnalyticType(String type) {
        this.type = type;
    }

    public String getData() {
        return data;
    }
    public void setData(String data) {
        this.data = data;
    }

    public String getDataOut() {
        return dataOut;
    }
    public void setDataOut(String dataOut) {
        this.dataOut = dataOut;
    }

    public Pair<String, String> getActivities() {
        return activities;
    }
    public void setActivities(Pair<String, String> activities) {
        this.activities = activities;
    }

    public String getUnits() {
        return units;
    }
    public void setUnits(String units) {
        this.units = units;
    }
}
