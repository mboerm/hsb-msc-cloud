package cloud.model.services;

import cloud.configuration.Config;

public class StorageServiceCreator implements ServiceAbstractCreator {

    private String name;
    private String type;
    private String mode;
    private Integer capacity;
    private Integer data;
    private Integer requests;
    private Integer queries;
    private Integer rate;

    public StorageServiceCreator(String name, String type, String mode, Integer capacity, Integer data, Integer requests, Integer queries, Integer rate) {
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

        if (type.equals(types[0])) {
            StorageService storageService = new StorageService(name, type, mode, capacity, data, requests, queries, 0);
            storageService.setDisplayName(type + " (" + mode + ")");
            return storageService;
        } else if (type.equals(types[1])) {
            return new StorageService(name, type, mode, capacity, data, 0, 0, 0);
        } else if (type.equals(types[2])) {
            return new StorageService(name, type, mode, capacity, data, 0, 0, rate);
        } else {
            return null;
        }
    }
}
