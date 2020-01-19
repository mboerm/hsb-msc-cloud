package cloud.model.services;

import javafx.util.Pair;

class DatabaseService extends Service {

    private String databaseType;
    private String databaseScheme;
    private String instanceType;
    private String instanceSize;
    private String duration;
    private String storage;
    private String backup;
    private String data;
    private Pair<String,String> queries;

    public DatabaseService(String name, String databaseType, String databaseScheme,
                           String instanceType, String instanceSize, String duration,
                           String storage, String backup, String data, Pair<String,String> queries) {
        setName(name);
        setCategory("Database");
        setService(databaseType);
        setDatabaseType(databaseType);
        setDatabaseScheme(databaseScheme);
        setInstanceType(instanceType);
        setInstanceSize(instanceSize);
        setDuration(duration);
        setStorage(storage);
        setBackup(backup);
        setData(data);
        setQueries(queries);
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

    public String getDuration() {
        return duration;
    }
    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getStorage() {
        return storage;
    }
    public void setStorage(String storage) {
        this.storage = storage;
    }

    public String getBackup() {
        return backup;
    }
    public void setBackup(String backup) {
        this.backup = backup;
    }

    public String getData() {
        return data;
    }
    public void setData(String data) {
        this.data = data;
    }

    public Pair<String, String> getQueries() {
        return queries;
    }
    public void setQueries(Pair<String, String> queries) {
        this.queries = queries;
    }
}
