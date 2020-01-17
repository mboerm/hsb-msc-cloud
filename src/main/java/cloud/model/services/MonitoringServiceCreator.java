package cloud.model.services;

public class MonitoringServiceCreator implements ServiceAbstractCreator {

    private String name;
    private String metrics;
    private String apiRequests;
    private String data;
    private String events;
    private boolean loggerState;

    public MonitoringServiceCreator(String name, String metrics, String apiRequests, String data, String events, boolean loggerState) {
        this.name = name;
        this.metrics = metrics;
        this.apiRequests = apiRequests;
        this.data = data;
        this.events = events;
        this.loggerState = loggerState;
    }

    @Override
    public Service createService() {
        return new MonitoringService(name, metrics, apiRequests, data, events, loggerState);
    }
}
