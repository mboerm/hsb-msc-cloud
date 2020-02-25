package cloud.model.services;

import cloud.configuration.Config;
import javafx.util.Pair;

public class DatabaseServiceCreator implements ServiceCreator {

    private String name;
    private String databaseType;
    private String databaseScheme;
    private String instanceType;
    private int duration;
    private int storage;
    private int backup;
    private int data;
    private Pair<Integer, Integer> num;

    public DatabaseServiceCreator(String name, String databaseType, String databaseScheme,
                                  String instanceType, int duration,
                                  int storage, int backup, int data, Pair<Integer, Integer> num) {
        this.name = name;
        this.databaseType = databaseType;
        this.databaseScheme = databaseScheme;
        this.instanceType = instanceType;
        this.duration = duration;
        this.storage = storage;
        this.backup = backup;
        this.data = data;
        this.num = num;
    }

    @Override
    public Service createService() {
        String[] types = Config.getInstance().getConfigValuesAsArray("database-system-type");

        DatabaseService databaseService;

        if (databaseType.equals(types[0])) {
            // SQL
            databaseService = new DatabaseService(name, databaseType, databaseScheme, instanceType,
                    duration, storage, backup, data, num);
        } else if (databaseType.equals(types[1])) {
            // NoSQL
            databaseService = new DatabaseService(name, databaseType, "", "",
                    0, storage, 0, data, num);
        } else if (databaseType.equals(types[2])) {
            // Document
            databaseService = new DatabaseService(name, databaseType, "", "",
                    0, storage, 0, data, num);
        } else if (databaseType.equals(types[3])) {
            // Cache
            databaseService = new DatabaseService(name, databaseType, "", "",
                    duration, 0, 0, data, num);
        } else {
            return null;
        }
        databaseService.setCategory(Config.getInstance().getConfigValuesAsArray("service-categories")[1]);
        databaseService.setIdentifier(ServiceChecker.getInstance().getServiceIdentifier(databaseService.getCategory(), databaseType, databaseScheme));
        return databaseService;
    }
}
