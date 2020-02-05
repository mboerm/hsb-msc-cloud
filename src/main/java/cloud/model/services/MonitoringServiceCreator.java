package cloud.model.services;

import cloud.configuration.Config;
import javafx.util.Pair;

public class MonitoringServiceCreator implements IServiceCreator {

    private String name;
    private Integer metrics;
    private Integer apiRequests;
    private Pair<Integer, Integer> data;
    private Integer events;
    private boolean loggerState;

    public MonitoringServiceCreator(String name, Integer metrics, Integer apiRequests, Pair<Integer, Integer> data, Integer events, boolean loggerState) {
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
