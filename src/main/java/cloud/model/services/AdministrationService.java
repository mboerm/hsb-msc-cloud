package cloud.model.services;

import cloud.configuration.Config;
import javafx.util.Pair;

public class AdministrationService extends Service {

    private Integer metrics;
    private Integer apiRequests;
    private Pair<Integer, Integer> data;
    private Integer events;
    private boolean loggerState;

    public AdministrationService(String name, Integer metrics, Integer apiRequests, Pair<Integer, Integer> data, Integer events, boolean loggerState) {
        setName(name);
        setCategory(Config.getInstance().getConfigValuesAsArray("service-categories")[6]);
        setMetrics(metrics);
        setRequests(apiRequests);
        setData(data);
        setEvents(events);
        setLoggingState(loggerState);
    }

    public Integer getMetrics() {return this.metrics;}
    public void setMetrics(Integer metrics) {this.metrics = metrics;}

    public Integer getRequests() {return this.apiRequests;}
    public void setRequests(Integer requests) {this.apiRequests = requests;}

    public Pair<Integer, Integer> getData() {return this.data;}
    public void setData(Pair<Integer, Integer> data) {this.data = data;}

    public Integer getEvents() {return this.events;}
    public void setEvents(Integer events) {this.events = events;}

    public boolean getLoggingState() {return this.loggerState;}
    public void setLoggingState(boolean isLogger) {this.loggerState = isLogger;}

    @Override
    public String[] getSpecificProperties() {
        return new String[] {
                this.getMetrics().toString(),
                this.getMetrics().toString(),
                this.getEvents().toString(),
                this.getData().getKey().toString(),
                this.getData().getValue().toString(),
                String.valueOf(this.getLoggingState())
        };
    }
}
