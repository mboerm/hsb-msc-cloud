package cloud.model.services;

class IntegrationService extends Service {

    private String integrationType;
    private String data;
    private String requests;

    /**
     * 0 = number of push messages
     * 1 = number of http messages
     * 2 = number of mail messages
     * 3 = number of sms messages
     */
    private String[] messages = new String[4];

    public IntegrationService(String name, String type, String data, String requests, String[] messages) {
        setName(name);
        setCategory("Integration");
        setService(type);
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

    public String getData() {return this.data;}
    public void setData(String data) {this.data = data;}

    public String getRequests() {return this.requests;}
    public void setRequests(String requests) {this.requests = requests;}

    public String[] getMessages() {
        return messages;
    }
    public void setMessages(String[] messages) {
        this.messages = messages;
    }
}
