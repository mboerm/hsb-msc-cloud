package cloud.model.services;

class ComputeService extends Service {

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

    public ComputeService(String name, String computeType, String instanceType, String instanceSize,
                          String computeInstance, String storageInstance, String system,
                          String cpu, String storage, String data, String numOne, String numTwo) {
        setName(name);
        setDisplayName(computeType);
        setCategory("Compute");
        setComputeType(computeType);
        setInstanceType(instanceType);
        setInstanceSize(instanceSize);
        setComputeInstance(computeInstance);
        setStorageInstance(storageInstance);
        setSystem(system);
        setCPU(cpu);
        setStorage(storage);
        setData(data);
        setNumOne(numOne);
        setNumTwo(numTwo);
    }

    public String getComputeType() {
        return computeType;
    }
    public void setComputeType(String computeType) {
        this.computeType = computeType;
    }

    public String getInstanceType() {
        return instanceType;
    }
    public void setInstanceType(String instanceType) {
        this.instanceType = instanceType;
    }

    public String getInstanceSize() {
        return instanceSize;
    }
    public void setInstanceSize(String instanceSize) {
        this.instanceSize = instanceSize;
    }

    public String getComputeInstance() {
        return computeInstance;
    }
    public void setComputeInstance(String computeInstance) {
        this.computeInstance = computeInstance;
    }

    public String getStorageInstance() {
        return storageInstance;
    }
    public void setStorageInstance(String storageInstance) {
        this.storageInstance = storageInstance;
    }

    public String getSystem() {
        return system;
    }
    public void setSystem(String system) {
        this.system = system;
    }

    public String getCPU() {
        return cpu;
    }
    public void setCPU(String cpu) {
        this.cpu = cpu;
    }

    public String getStorage() {
        return storage;
    }
    public void setStorage(String storage) {
        this.storage = storage;
    }

    public String getData() {
        return data;
    }
    public void setData(String data) {
        this.data = data;
    }

    public String getNumOne() {
        return numOne;
    }
    public void setNumOne(String numOne) {
        this.numOne = numOne;
    }

    public String getNumTwo() {
        return numTwo;
    }
    public void setNumTwo(String numTwo) {
        this.numTwo = numTwo;
    }
}
