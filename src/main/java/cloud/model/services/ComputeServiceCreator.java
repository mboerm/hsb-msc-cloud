package cloud.model.services;

import cloud.configuration.Config;

public class ComputeServiceCreator implements IServiceCreator {

    private String name;
    private String computeType;
    private String instanceType;
    private String instanceSize;
    private String computeInstance;
    private String storageInstance;
    private String system;
    private int cpu;
    private int storage;
    private int data;
    private int numOne;
    private int numTwo;

    public ComputeServiceCreator(String name, String computeType, String instanceType, String instanceSize,
                                 String computeInstance, String storageInstance, String system,
                                 int cpu, int storage, int data, int numOne, int numTwo) {
        this.name = name;
        this.computeType = computeType;
        this.instanceType = instanceType;
        this.instanceSize = instanceSize;
        this.computeInstance = computeInstance;
        this.storageInstance = storageInstance;
        this.system = system;
        this.cpu = cpu;
        this.storage = storage;
        this.data = data;
        this.numOne = numOne;
        this.numTwo = numTwo;
    }

    @Override
    public Service createService() {
        String[] types = Config.getInstance().getConfigValuesAsArray("compute-type");

        ComputeService computeService;

        if (computeType.equals(types[0])) {
            /* VM */
            computeService = new ComputeService(name, computeType, instanceType, instanceSize, "", "",
                    system, cpu, storage, data, numOne, 0);
        } else if (computeType.equals(types[1])) {
            /* Container */
            computeService = new ComputeService(name, computeType, "", "", "", "",
                    system, cpu, storage, data, numOne, numTwo);
			computeService.setIdentifier(computeType + " (" + system + ")");
        } else if (computeType.equals(types[2])) {
            /* App */
            computeService = new ComputeService(name, computeType, "", "", computeInstance, storageInstance,
                    system, 0, 0, 0, 0, 0);
        } else if (computeType.equals(types[3])) {
            /* Batch */
            computeService = new ComputeService(name, computeType, "", "", computeInstance, "",
                    "", 0, 0, 0, 0, 0);
        } else if (computeType.equals(types[4])) {
            /* Code */
            computeService = new ComputeService(name, computeType, "", "", "", "",
                    "", 0, storage, 0, numOne, numTwo);
        } else if (computeType.equals(types[5])) {
            /* Load Balancing */
            computeService = new ComputeService(name, computeType, "", "", "", "",
                    "", 0, 0, data, numOne, 0);
        } else {
            throw new IllegalArgumentException("Invalid compute type");
        }
        computeService.setCategory(Config.getInstance().getConfigValuesAsArray("service-categories")[0]);
        return computeService;
    }
}
