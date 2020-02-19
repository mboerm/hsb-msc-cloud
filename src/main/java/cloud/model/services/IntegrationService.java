package cloud.model.services;

import cloud.configuration.Config;

public class IntegrationService extends Service {

    private String integrationType;
    private String integrationMode;
    private int data;
    private int requests;

    /**
     * 0 = number of push messages
     * 1 = number of http messages
     * 2 = number of mail messages
     * 3 = number of sms messages
     */
    private int[] messages = new int[4];

    public IntegrationService(String name, String type, String mode, int data, int requests, int[] messages) {
        setName(name);
        setCategory(Config.getInstance().getConfigValuesAsArray("service-categories")[5]);
        setIdentifier(type);
        setIntegrationType(type);
        setIntegrationMode(mode);
        setData(data);
        setRequests(requests);
        setMessages(messages);
    }

    public String getIntegrationType() {
        return integrationType;
    }
    public void setIntegrationType(String type) {
        this.integrationType = type;
    }

    public String getIntegrationMode() {
        return integrationMode;
    }
    public void setIntegrationMode(String integrationMode) {
        this.integrationMode = integrationMode;
    }

    public int getData() {return this.data;}
    public void setData(int data) {this.data = data;}

    public int getRequests() {return this.requests;}
    public void setRequests(int requests) {this.requests = requests;}

    public int[] getMessages() {
        return messages;
    }
    public void setMessages(int[] messages) {
        this.messages = messages;
    }
}
