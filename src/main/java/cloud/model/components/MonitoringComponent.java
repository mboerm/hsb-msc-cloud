package cloud.model.components;

public class MonitoringComponent extends Component {

    private String metrics;
    private String apiRequests;
    private String data;
    private String events;
    private boolean isLogger;

    public MonitoringComponent(
            String name,
            String metrics,
            String requests,
            String data,
            String events,
            boolean isLogger
    ) {
        setName(name);
        setCategory("Monitoring");
        setMetrics(metrics);
        setApiRequests(requests);
        setData(data);
        setEvents(events);
        setIsLogger(isLogger);
    }

    public String getMetrics() {return this.metrics;}
    public void setMetrics(String metrics) {this.metrics = metrics;}

    public String getApiRequests() {return this.apiRequests;}
    public void setApiRequests(String requests) {this.apiRequests = requests;}

    public String getData() {return this.data;}
    public void setData(String data) {this.data = data;}

    public String getEvents() {return this.events;}
    public void setEvents(String events) {this.events = events;}

    public boolean getIsLogger() {return this.isLogger;}
    public void setIsLogger(boolean isLogger) {this.isLogger = isLogger;}
}
