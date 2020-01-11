package cloud.model.components;

public class DatabaseComponent extends Component {

    private String dbInstanceType;
    private String dbSystemType;

    public DatabaseComponent() {
        setCategory("Database");
        setDatabaseInstanceType("Standard");
        setDatabaseSystemType("SQL");
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
