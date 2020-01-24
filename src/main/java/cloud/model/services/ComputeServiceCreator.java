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
    private Integer cpu;
    private Integer storage;
    private Integer data;
    private Integer numOne;
    private Integer numTwo;

    public ComputeServiceCreator(String name, String computeType, String instanceType, String instanceSize,
                                 String computeInstance, String storageInstance, String system,
                                 Integer cpu, Integer storage, Integer data, Integer numOne, Integer numTwo) {
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

        if (computeType.equals(types[0])) {
            /* VM */
            return new ComputeService(name, computeType, instanceType, instanceSize, "", "",
                    system, 0, 0, 0, 0, 0);
        } else if (computeType.equals(types[1])) {
            /* Container */
			ComputeService computeService = new ComputeService(name, computeType, "", "", "", "",
                    system, cpu, storage, data, numOne, numTwo);
			computeService.setDisplayName(computeType + " (" + system + ")");
            return computeService;
        } else if (computeType.equals(types[2])) {
            /* App */
            return new ComputeService(name, computeType, "", "", computeInstance, storageInstance,
                    system, 0, 0, 0, 0, 0);
        } else if (computeType.equals(types[3])) {
            /* Batch */
            return new ComputeService(name, computeType, "", "", computeInstance, "",
                    "", 0, 0, 0, 0, 0);
        } else if (computeType.equals(types[4])) {
            /* Code */
            return new ComputeService(name, computeType, "", "", "", "",
                    "", cpu, storage, 0, numOne, numTwo);
        } else if (computeType.equals(types[5])) {
            /* Load Balancing */
            return new ComputeService(name, computeType, "", "", computeInstance, "",
                    system, 0, 0, data, numOne, 0);
        } else {
            throw new IllegalArgumentException("Invalid compute type");
        }
    }
}
