package cloud.model.services;

import cloud.configuration.Config;

public class MonitoringServiceCreator implements ServiceAbstractCreator {

    private String name;
    private Integer metrics;
    private Integer apiRequests;
    private Integer data;
    private Integer events;
    private boolean loggerState;

    public MonitoringServiceCreator(String name, Integer metrics, Integer apiRequests, Integer data, Integer events, boolean loggerState) {
        this.name = name;
        this.metrics = metrics;
        this.apiRequests = apiRequests;
        this.data = data;
        this.events = events;
        this.loggerState = loggerState;
    }

    @Override
    public Service createService() {
        MonitoringService monitor = new MonitoringService(name, metrics, apiRequests, data, events, loggerState);
        String[] types = Config.getInstance().getConfigValuesAsArray("monitoring-type");
        if (loggerState) {
            monitor.setDisplayName(types[0]);
        } else {
            monitor.setDisplayName(types[1]);
        }
        return monitor;
    }
}
