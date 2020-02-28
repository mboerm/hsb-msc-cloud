package cloud.model.services;

import cloud.configuration.Config;
import javafx.util.Pair;

/**
 * Storage service creator
 */
public class StorageServiceCreator implements ServiceCreator {

    private String name;
    private String storageType;
    private String mode;
    private int capacity;
    private int data;
    private Pair<Integer, Integer> requests;
    private int rate;

    /**
     * Constructor
     * @param name name of service
     * @param storageType storage type
     * @param mode storage type mode
     * @param capacity number of capacity
     * @param data number of data
     * @param requests number of requests
     * @param rate number of data rate
     */
    public StorageServiceCreator(String name, String storageType, String mode, int capacity, int data, Pair<Integer, Integer> requests, int rate) {
        this.name = name;
        this.storageType = storageType;
        this.mode = mode;
        this.capacity = capacity;
        this.data = data;
        this.requests = requests;
        this.rate = rate;
    }

    @Override
    public Service createService() {
        String[] types = Config.getInstance().getConfigValuesAsArray("storage-type");
        StorageService storageService;

        if (storageType.equals(types[0])) {
            // Object storage
            storageService = new StorageService(name, storageType, mode, capacity, data, requests, 0);
        } else if (storageType.equals(types[1])) {
            // Block storage
            storageService = new StorageService(name, storageType, mode, capacity, data, new Pair<>(0,0), 0);
        } else if (storageType.equals(types[2])) {
            // File storage
            storageService = new StorageService(name, storageType, mode, capacity, data, new Pair<>(0,0), rate);
        } else {
            return null;
        }
        /* set category of service */
        storageService.setCategory(Config.getInstance().getConfigValuesAsArray("service-categories")[2]);
        /* set identifier of service */
        storageService.setIdentifier(ServiceChecker.getInstance().getServiceIdentifier(storageService.getCategory(), storageType, mode));
        return storageService;
    }
}
