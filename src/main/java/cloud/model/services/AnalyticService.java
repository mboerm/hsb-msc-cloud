package cloud.model.services;

import cloud.configuration.Config;
import javafx.util.Pair;

public class AnalyticService extends Service {

    private String analyticType;
    private int data;
    private int dataOut;
    private Pair<Integer,Integer> num;
    private int units;
    private String instanceType;
    private String instanceSize;

    public AnalyticService(String name, String type, int data, int dataOut, Pair<Integer,Integer> num, int units, String instanceType, String instanceSize) {
        setName(name);
        setCategory(Config.getInstance().getConfigValuesAsArray("service-categories")[3]);
        setIdentifier(type);
        setAnalyticType(type);
        setData(data);
        setDataOut(dataOut);
        setNum(num);
        setUnits(units);
        setInstanceType(instanceType);
        setInstanceSize(instanceSize);
    }

    public String getAnalyticType() {
        return analyticType;
    }
    public void setAnalyticType(String type) {
        this.analyticType = type;
    }

    public int getData() {
        return data;
    }
    public void setData(int data) {
        this.data = data;
    }

    public int getDataOut() {
        return dataOut;
    }
    public void setDataOut(int dataOut) {
        this.dataOut = dataOut;
    }

    public Pair<Integer, Integer> getNum() {
        return num;
    }
    public void setNum(Pair<Integer, Integer> num) {
        this.num = num;
    }

    public int getUnits() {
        return units;
    }
    public void setUnits(int units) {
        this.units = units;
    }

    public String getInstanceType() {
        return instanceType;
    }
    public void setInstanceType(String instanceType) {
        this.instanceType = instanceType;
    }

    public String getInstanceSize() {
        return instanceSize;
    }
    public void setInstanceSize(String instanceSize) {
        this.instanceSize = instanceSize;
    }
}
