package cloud.model.services;

import javafx.util.Pair;

public class AnalyticService extends Service {

    private String analyticType;
    private Integer data;
    private Integer dataOut;
    private Pair<Integer,Integer> activities;
    private Integer units;
    private String instanceSize;

    public AnalyticService(String name, String type, Integer data, Integer dataOut, Pair<Integer,Integer> activities, Integer units, String instanceSize) {
        setName(name);
        setCategory("Analytics");
        setDisplayName(type);
        setAnalyticType(type);
        setData(data);
        setDataOut(dataOut);
        setActivities(activities);
        setUnits(units);
        setInstanceSize(instanceSize);
    }

    public String getAnalyticType() {
        return analyticType;
    }
    public void setAnalyticType(String type) {
        this.analyticType = type;
    }

    public Integer getData() {
        return data;
    }
    public void setData(Integer data) {
        this.data = data;
    }

    public Integer getDataOut() {
        return dataOut;
    }
    public void setDataOut(Integer dataOut) {
        this.dataOut = dataOut;
    }

    public Pair<Integer, Integer> getActivities() {
        return activities;
    }
    public void setActivities(Pair<Integer, Integer> activities) {
        this.activities = activities;
    }

    public Integer getUnits() {
        return units;
    }
    public void setUnits(Integer units) {
        this.units = units;
    }

    public String getInstanceSize() {
        return instanceSize;
    }
    public void setInstanceSize(String instanceSize) {
        this.instanceSize = instanceSize;
    }
}
