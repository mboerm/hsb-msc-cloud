package cloud.model.services;

class MonitoringService extends Service {

    private String metrics;
    private String apiRequests;
    private String data;
    private String events;
    private boolean loggerState;

    public MonitoringService(String name, String metrics, String apiRequests, String data, String events, boolean loggerState) {
        setName(name);
        setCategory("Monitoring");
        setMetrics(metrics);
        setApiRequests(apiRequests);
        setData(data);
        setEvents(events);
        setLoggerState(loggerState);
    }

    public String getMetrics() {return this.metrics;}
    public void setMetrics(String metrics) {this.metrics = metrics;}

    public String getApiRequests() {return this.apiRequests;}
    public void setApiRequests(String requests) {this.apiRequests = requests;}

    public String getData() {return this.data;}
    public void setData(String data) {this.data = data;}

    public String getEvents() {return this.events;}
    public void setEvents(String events) {this.events = events;}

    public boolean getLoggerState() {return this.loggerState;}
    public void setLoggerState(boolean isLogger) {this.loggerState = isLogger;}
}
