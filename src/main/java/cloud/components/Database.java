package cloud.components;

public class Database extends Component {

    // TODO: Parameter für Datenbanken hinzufügen

    private enum dbType {DEFAULT, SQL, MySQL, NoSQL, Document};
    private dbType db;

    public Database() {
        setName("DB");
        setCategory(componentCategory.DATABASE);
        setDBType(db.DEFAULT);
    }

    public dbType getDBType() {
        return this.db;
    }
    public void setDBType(dbType db) {
        this.db = db;
    }
}
