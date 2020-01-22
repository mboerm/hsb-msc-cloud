package cloud.model.services;

public class MonitoringService extends Service {

    private Integer metrics;
    private Integer apiRequests;
    private Integer data;
    private Integer events;
    private boolean loggerState;

    public MonitoringService(String name, Integer metrics, Integer apiRequests, Integer data, Integer events, boolean loggerState) {
        setName(name);
        setCategory("Monitoring");
        setMetrics(metrics);
        setApiRequests(apiRequests);
        setData(data);
        setEvents(events);
        setLoggerState(loggerState);
    }

    public Integer getMetrics() {return this.metrics;}
    public void setMetrics(Integer metrics) {this.metrics = metrics;}

    public Integer getApiRequests() {return this.apiRequests;}
    public void setApiRequests(Integer requests) {this.apiRequests = requests;}

    public Integer getData() {return this.data;}
    public void setData(Integer data) {this.data = data;}

    public Integer getEvents() {return this.events;}
    public void setEvents(Integer events) {this.events = events;}

    public boolean getLoggerState() {return this.loggerState;}
    public void setLoggerState(boolean isLogger) {this.loggerState = isLogger;}
}
