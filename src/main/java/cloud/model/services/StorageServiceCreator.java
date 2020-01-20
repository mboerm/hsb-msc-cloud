package cloud.model.services;

import cloud.configuration.Config;

public class StorageServiceCreator implements ServiceAbstractCreator {

    private String name;
    private String type;
    private String mode;
    private String capacity;
    private String data;
    private String requests;
    private String queries;
    private String rate;

    public StorageServiceCreator(String name, String type, String mode, String capacity, String data, String requests, String queries, String rate) {
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
            StorageService storageService = new StorageService(name, type, mode, capacity, data, requests, queries, "");
            storageService.setDisplayName(type + " (" + mode + ")");
            return storageService;
        } else if (type.equals(types[1])) {
            return new StorageService(name, type, mode, capacity, data, "", "", "");
        } else if (type.equals(types[2])) {
            return new StorageService(name, type, mode, capacity, data, "", "", rate);
        } else {
            return null;
        }
    }
}
