package cloud.model.services;

/**
 * Network service class
 */
public class NetworkService extends Service {

    private String networkType;
    private int requests;
    private int data;
    private int dataOut;
    private int numOne;
    private int numTwo;

    public NetworkService(String name, String type, int requests, int data, int dataOut, int numOne, int numTwo) {
        setName(name);
        setNetworkType(type);
        setRequests(requests);
        setData(data);
        setDataOut(dataOut);
        setNumOne(numOne);
        setNumTwo(numTwo);
    }

    public String getNetworkType() {
        return networkType;
    }
    public void setNetworkType(String type) {
        this.networkType = type;
    }

    public int getRequests() {
        return requests;
    }
    public void setRequests(int requests) {
        this.requests = requests;
    }

    public int getData() {
        return data;
    }
    public void setData(int data) {
        this.data = data;
    }

    public int getDataOut() {
        return dataOut;
    }
    public void setDataOut(int dataOut) {
        this.dataOut = dataOut;
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
