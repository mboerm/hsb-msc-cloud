package cloud.model.services;

import cloud.configuration.Config;
import javafx.util.Pair;

public class StorageServiceCreator implements IServiceCreator {

    private String name;
    private String type;
    private String mode;
    private int capacity;
    private int data;
    private Pair<Integer, Integer> requests;
    private int queries;
    private int rate;

    public StorageServiceCreator(String name, String type, String mode, int capacity, int data, Pair<Integer, Integer> requests, int queries, int rate) {
        this.name = name;
        this.type = type;
        this.mode = mode;
        this.capacity = capacity;
        this.data = data;
        this.requests = requests;
        this.queries = queries;
        this.rate = rate;
    }

    @Override
    public Service createService() {
        String[] types = Config.getInstance().getConfigValuesAsArray("storage-type");
        StorageService storageService;

        if (type.equals(types[0])) {
            storageService = new StorageService(name, type, mode, capacity, data, requests, queries, 0);
        } else if (type.equals(types[1])) {
            storageService = new StorageService(name, type, mode, capacity, data, new Pair<>(0,0), 0, 0);
        } else if (type.equals(types[2])) {
            storageService = new StorageService(name, type, mode, capacity, data, new Pair<>(0,0), 0, rate);
        } else {
            return null;
        }
        storageService.setCategory(Config.getInstance().getConfigValuesAsArray("service-categories")[2]);
        storageService.setIdentifier(ServiceChecker.getInstance().getServiceIdentifier(storageService.getCategory(), type, mode));
        return storageService;
    }
}
