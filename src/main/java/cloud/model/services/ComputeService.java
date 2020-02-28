package cloud.model.services;

/**
 * Compute service class
 */
public class ComputeService extends Service {

    private String computeType;
    private String instanceType;
    private String computeInstance;
    private String storageInstance;
    private String system;
    private int cpu;
    private int storage;
    private int data;
    private int numOne;
    private int numTwo;

    public ComputeService(String name, String computeType, String instanceType,
                          String computeInstance, String storageInstance, String system,
                          int cpu, int storage, int data, int numOne, int numTwo) {
        setName(name);
        setComputeType(computeType);
        setInstanceType(instanceType);
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

    public int getCPU() {
        return cpu;
    }
    public void setCPU(int cpu) {
        this.cpu = cpu;
    }

    public int getStorage() {
        return storage;
    }
    public void setStorage(int storage) {
        this.storage = storage;
    }

    public int getData() {
        return data;
    }
    public void setData(int data) {
        this.data = data;
    }

    public int getNumOne() {
        return numOne;
    }
    public void setNumOne(int numOne) {
        this.numOne = numOne;
    }

    public int getNumTwo() {
        return numTwo;
    }
    public void setNumTwo(int numTwo) {
        this.numTwo = numTwo;
    }
}
