package cloud.model.services;

import cloud.configuration.Config;
import javafx.util.Pair;

public class DatabaseServiceCreator implements ServiceAbstractCreator {

    private String name;
    private String databaseType;
    private String databaseScheme;
    private String instanceType;
    private String instanceSize;
    private String duration;
    private String storage;
    private String backup;
    private String data;
    private Pair<String,String> queries;

    public DatabaseServiceCreator(String name, String databaseType, String databaseScheme,
                                  String instanceType, String instanceSize, String duration,
                                  String storage, String backup, String data, Pair<String,String> queries) {
        this.name = name;
        this.databaseType = databaseType;
        this.databaseScheme = databaseScheme;
        this.instanceType = instanceType;
        this.instanceSize = instanceSize;
        this.duration = duration;
        this.storage = storage;
        this.backup = backup;
        this.data = data;
        this.queries = queries;
    }

    @Override
    public Service createService() {
        String[] types = Config.getInstance().getConfigValuesAsArray("database-type");

        if (databaseType.equals(types[0])) {
            return new DatabaseService(name, databaseType, databaseScheme, instanceType, instanceSize,
                    duration, storage, backup, data, new Pair<>("",""));
        } else if (databaseType.equals(types[1])) {
            return new DatabaseService(name, databaseType, "", "", "",
                    "", storage, "", data, queries);
        } else if (databaseType.equals(types[2])) {
            return new DatabaseService(name, databaseType, "", "", "",
                    duration, storage, backup, data, new Pair<>("",""));
        } else if (databaseType.equals(types[3])) {
            return new DatabaseService(name, databaseType, "", instanceType, instanceSize,
                    duration, "", "", data, new Pair<>("",""));
        } else {
            return null;
        }
    }
}
