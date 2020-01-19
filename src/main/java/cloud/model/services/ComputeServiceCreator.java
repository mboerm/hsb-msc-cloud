package cloud.model.services;

import cloud.configuration.Config;

public class ComputeServiceCreator implements ServiceAbstractCreator {

    private String name;
    private String computeType;
    private String instanceType;
    private String instanceSize;
    private String computeInstance;
    private String storageInstance;
    private String system;
    private String cpu;
    private String storage;
    private String data;
    private String numOne;
    private String numTwo;

    public ComputeServiceCreator(String name, String computeType, String instanceType, String instanceSize, String system,
                                 String computeInstance, String storageInstance, String cpu, String storage,
                                 String data, String numOne, String numTwo) {
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
                    system, "", "", "", "", "");
        } else if (computeType.equals(types[1])) {
            /* Container */
            return new ComputeService(name, computeType, "", "", "", "",
                    "", cpu, storage, data, numOne, numTwo);
        } else if (computeType.equals(types[2])) {
            /* App */
            return new ComputeService(name, computeType, "", "", computeInstance, storageInstance,
                    system, "", "", "", "", "");
        } else if (computeType.equals(types[3])) {
            /* Batch */
            return new ComputeService(name, computeType, "", "", computeInstance, "",
                    "", "", "", "", "", "");
        } else if (computeType.equals(types[4])) {
            /* Code */
            return new ComputeService(name, computeType, "", "", "", "",
                    "", cpu, storage, "", numOne, numTwo);
        } else if (computeType.equals(types[5])) {
            /* Load Balancing */
            return new ComputeService(name, computeType, "", "", computeInstance, "",
                    system, "", "", data, numOne, "");
        } else {
            return null;
        }
    }
}
