package cloud.model.services;

import cloud.configuration.Config;
import javafx.util.Pair;

public class AdministrationService extends Service {

    private String administrationType;
    private Integer metrics;
    private Integer apiRequests;
    private Pair<Integer, Integer> data;
    private Integer events;
    private boolean loggerState;

    public AdministrationService(String name, String administrationType, Integer metrics, Integer apiRequests, Pair<Integer, Integer> data, Integer events, boolean loggerState) {
        setName(name);
        setCategory(Config.getInstance().getConfigValuesAsArray("service-categories")[6]);
        setAdministrationType(administrationType);
        setMetrics(metrics);
        setRequests(apiRequests);
        setData(data);
        setEvents(events);
        setLoggingState(loggerState);
    }

    public String getAdministrationType() {
        return administrationType;
    }
    public void setAdministrationType(String administrationType) {
        this.administrationType = administrationType;
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
                this.getRequests().toString(),
                this.getData().getKey().toString(),
                this.getData().getValue().toString(),
                this.getEvents().toString(),
                String.valueOf(this.getLoggingState())
        };
    }
}
