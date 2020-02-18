package cloud.model.services;

import cloud.configuration.Config;
import javafx.util.Pair;

public class DatabaseServiceCreator implements IServiceCreator {

    private String name;
    private String databaseType;
    private String databaseScheme;
    private String instanceType;
    private String instanceSize;
    private Integer duration;
    private Integer storage;
    private Integer backup;
    private Integer data;
    private Pair<Integer,Integer> num;

    public DatabaseServiceCreator(String name, String databaseType, String databaseScheme,
                                  String instanceType, String instanceSize, Integer duration,
                                  Integer storage, Integer backup, Integer data, Pair<Integer,Integer> num) {
        this.name = name;
        this.databaseType = databaseType;
        this.databaseScheme = databaseScheme;
        this.instanceType = instanceType;
        this.instanceSize = instanceSize;
        this.duration = duration;
        this.storage = storage;
        this.backup = backup;
        this.data = data;
        this.num = num;
    }

    @Override
    public Service createService() {
        String[] types = Config.getInstance().getConfigValuesAsArray("database-system-type");

        if (databaseType.equals(types[0])) {
            // SQL
            DatabaseService dbService = new DatabaseService(name, databaseType, databaseScheme, instanceType, instanceSize,
                    duration, storage, backup, data, num);
            dbService.setIdentifier(databaseType + " (" + databaseScheme + ")");
            return dbService;
        } else if (databaseType.equals(types[1])) {
            // NoSQL
            return new DatabaseService(name, databaseType, "", "", "",
                    0, storage, 0, data, num);
        } else if (databaseType.equals(types[2])) {
            // Document
            return new DatabaseService(name, databaseType, "", "", "",
                    duration, storage, backup, data, new Pair<>(0,0));
        } else if (databaseType.equals(types[3])) {
            // Cache
            return new DatabaseService(name, databaseType, "", instanceType, instanceSize,
                    duration, 0, 0, data, num);
        } else {
            return null;
        }
    }
}
