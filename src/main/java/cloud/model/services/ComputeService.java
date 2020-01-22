package cloud.model.services;

public class ComputeService extends Service {

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

    public ComputeService(String name, String computeType, String instanceType, String instanceSize,
                          String computeInstance, String storageInstance, String system,
                          Integer cpu, Integer storage, Integer data, Integer numOne, Integer numTwo) {
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

    public Integer getCPU() {
        return cpu;
    }
    public void setCPU(Integer cpu) {
        this.cpu = cpu;
    }

    public Integer getStorage() {
        return storage;
    }
    public void setStorage(Integer storage) {
        this.storage = storage;
    }

    public Integer getData() {
        return data;
    }
    public void setData(Integer data) {
        this.data = data;
    }

    public Integer getNumOne() {
        return numOne;
    }
    public void setNumOne(Integer numOne) {
        this.numOne = numOne;
    }

    public Integer getNumTwo() {
        return numTwo;
    }
    public void setNumTwo(Integer numTwo) {
        this.numTwo = numTwo;
    }
}
