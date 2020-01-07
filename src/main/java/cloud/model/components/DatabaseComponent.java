package cloud.model.components;

import static cloud.constants.Consts.*;

public class DatabaseComponent extends Component {

    private DATABASE_INSTANCE_TYPE databaseInstanceType;
    private DATABASE_TYPE dbType;

    public DatabaseComponent() {
        setCategory("Database");
        setDatabaseInstanceType(DATABASE_INSTANCE_TYPE.Standard);
        setDatabaseBType(DATABASE_TYPE.SQL);
    }

    public DATABASE_INSTANCE_TYPE getDatabaseInstanceType() {
        return databaseInstanceType;
    }
    public void setDatabaseInstanceType(DATABASE_INSTANCE_TYPE databaseInstanceType) {
        this.databaseInstanceType = databaseInstanceType;
    }

    public DATABASE_TYPE getDatabaseType() {
        return this.dbType;
    }
    public void setDatabaseBType(DATABASE_TYPE db) {
        this.dbType = db;
    }
}
