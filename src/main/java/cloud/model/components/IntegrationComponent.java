package cloud.model.components;

public class IntegrationComponent extends Component {

    private String type;
    private String data;
    private String requests;

    /**
     * 0 = number of push messages
     * 1 = number of http messages
     * 2 = number of mail messages
     * 3 = number of sms messages
     */
    private String[] messages = new String[4];

    public IntegrationComponent(
            String name,
            String type,
            String data,
            String requests
    ) {
        setName(name);
        setCategory("Integration");
        setType(type);
        setData(data);
        setRequests(requests);
    }

    public IntegrationComponent(
            String name,
            String type,
            String data,
            String requests,
            String[] messages
    ) {
        setName(name);
        setCategory("Integration");
        setType(type);
        setData(data);
        setRequests(requests);
        setMessages(messages);
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
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
