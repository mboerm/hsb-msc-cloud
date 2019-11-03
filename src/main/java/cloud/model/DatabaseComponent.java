package cloud.model;

import cloud.model.Component;

import static cloud.constants.Consts.*;

public class DatabaseComponent extends Component {

    // TODO: Parameter für Datenbanken hinzufügen

    private DATABASE_TYPES db;

    public DatabaseComponent() {
        setName("DB");
        setCategory(COMPONENT_CATEGORIES.DATABASE);
        setDBType(db.SQL);
    }

    public DATABASE_TYPES getDBType() {
        return this.db;
    }
    public void setDBType(DATABASE_TYPES db) {
        this.db = db;
    }
}
