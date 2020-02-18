package cloud.model.services;

import cloud.configuration.Config;

public class IntegrationService extends Service {

    private String integrationType;
    private Integer data;
    private Integer requests;

    /**
     * 0 = number of push messages
     * 1 = number of http messages
     * 2 = number of mail messages
     * 3 = number of sms messages
     */
    private Integer[] messages = new Integer[4];

    public IntegrationService(String name, String type, Integer data, Integer requests, Integer[] messages) {
        setName(name);
        setCategory(Config.getInstance().getConfigValuesAsArray("service-categories")[5]);
        setIdentifier(type);
        setIntegrationType(type);
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

    public Integer getData() {return this.data;}
    public void setData(Integer data) {this.data = data;}

    public Integer getRequests() {return this.requests;}
    public void setRequests(Integer requests) {this.requests = requests;}

    public Integer[] getMessages() {
        return messages;
    }
    public void setMessages(Integer[] messages) {
        this.messages = messages;
    }
}
