package cloud.model.services;

import cloud.configuration.Config;
import javafx.util.Pair;

public class AnalyticService extends Service {

    private String analyticType;
    private Integer data;
    private Integer dataOut;
    private Pair<Integer,Integer> num;
    private Integer units;
    private String instanceType;
    private String instanceSize;

    public AnalyticService(String name, String type, Integer data, Integer dataOut, Pair<Integer,Integer> num, Integer units, String instanceType, String instanceSize) {
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

    public Pair<Integer, Integer> getNum() {
        return num;
    }
    public void setNum(Pair<Integer, Integer> num) {
        this.num = num;
    }

    public Integer getUnits() {
        return units;
    }
    public void setUnits(Integer units) {
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

    @Override
    public String[] getSpecificProperties() {
        return new String[] {
                this.getAnalyticType(),
                this.getInstanceType(),
                this.getInstanceSize(),
                this.getUnits().toString(),
                this.getData().toString(),
                this.getDataOut().toString(),
                this.getNum().getKey().toString(),
                this.getNum().getValue().toString()
        };
    }
}
