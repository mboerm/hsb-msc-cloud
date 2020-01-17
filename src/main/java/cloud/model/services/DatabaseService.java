package cloud.model.services;

class DatabaseService extends Service {

    private String dbInstanceType;
    private String dbSystemType;

    public DatabaseService() {
        setCategory("Database");
    }

    public String getDatabaseInstanceType() {
        return dbInstanceType;
    }
    public void setDatabaseInstanceType(String instanceType) {
        this.dbInstanceType = instanceType;
    }

    public String getDatabaseSystemType() {
        return this.dbSystemType;
    }
    public void setDatabaseSystemType(String systemType) {
        this.dbSystemType = systemType;
    }
}
