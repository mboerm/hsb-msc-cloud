package cloud.model.services;

import cloud.configuration.Config;
import javafx.util.Pair;

public class DatabaseService extends Service {

    private String databaseType;
    private String databaseScheme;
    private String instanceType;
    private String instanceSize;
    private Integer duration;
    private Integer storage;
    private Integer backup;
    private Integer data;
    private Pair<Integer,Integer> num;

    public DatabaseService(String name, String databaseType, String databaseScheme,
                           String instanceType, String instanceSize, Integer duration,
                           Integer storage, Integer backup, Integer data, Pair<Integer,Integer> num) {
        setName(name);
        setCategory(Config.getInstance().getConfigValuesAsArray("service-categories")[1]);
        setIdentifier(databaseType);
        setDatabaseType(databaseType);
        setDatabaseScheme(databaseScheme);
        setInstanceType(instanceType);
        setInstanceSize(instanceSize);
        setDuration(duration);
        setStorage(storage);
        setBackup(backup);
        setData(data);
        setNum(num);
    }

    public String getDatabaseType() {
        return databaseType;
    }
    public void setDatabaseType(String databaseType) {
        this.databaseType = databaseType;
    }

    public String getDatabaseScheme() {
        return databaseScheme;
    }
    public void setDatabaseScheme(String databaseScheme) {
        this.databaseScheme = databaseScheme;
    }

    public String getInstanceType() {
        return instanceType;
    }
    public void setInstanceType(String instanceType) {
        this.instanceType = instanceType;
    }

    public String getInstanceSize() {
        return this.instanceSize;
    }
    public void setInstanceSize(String systemType) {
        this.instanceSize = systemType;
    }

    public Integer getDuration() {
        return duration;
    }
    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getStorage() {
        return storage;
    }
    public void setStorage(Integer storage) {
        this.storage = storage;
    }

    public Integer getBackup() {
        return backup;
    }
    public void setBackup(Integer backup) {
        this.backup = backup;
    }

    public Integer getData() {
        return data;
    }
    public void setData(Integer data) {
        this.data = data;
    }

    public Pair<Integer, Integer> getNum() {
        return num;
    }
    public void setNum(Pair<Integer, Integer> num) {
        this.num = num;
    }
}
