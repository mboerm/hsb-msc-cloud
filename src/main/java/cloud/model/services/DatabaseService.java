package cloud.model.services;

import javafx.util.Pair;

public class DatabaseService extends Service {

    private String databaseType;
    private String databaseScheme;
    private String instanceType;
    private String instanceSize;
    private int duration;
    private int storage;
    private int backup;
    private int data;
    private Pair<Integer,Integer> num;

    public DatabaseService(String name, String databaseType, String databaseScheme,
                           String instanceType, String instanceSize, int duration,
                           int storage, int backup, int data, Pair<Integer,Integer> num) {
        setName(name);
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

    public int getDuration() {
        return duration;
    }
    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getStorage() {
        return storage;
    }
    public void setStorage(int storage) {
        this.storage = storage;
    }

    public int getBackup() {
        return backup;
    }
    public void setBackup(int backup) {
        this.backup = backup;
    }

    public int getData() {
        return data;
    }
    public void setData(int data) {
        this.data = data;
    }

    public Pair<Integer, Integer> getNum() {
        return num;
    }
    public void setNum(Pair<Integer, Integer> num) {
        this.num = num;
    }
}
