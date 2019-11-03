package cloud.components;

import static cloud.constants.Consts.*;

public class Database extends Component {

    // TODO: Parameter für Datenbanken hinzufügen

    private DATABASE_TYPES db;

    public Database() {
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
