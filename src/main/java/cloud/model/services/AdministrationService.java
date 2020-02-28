package cloud.model.services;

import javafx.util.Pair;

/**
 * Administration service class
 */
public class AdministrationService extends Service {

    private String administrationType;
    private int metrics;
    private int apiRequests;
    private Pair<Integer, Integer> data;
    private int events;
    private boolean loggerState;

    public AdministrationService(String name, String administrationType, int metrics, int apiRequests, Pair<Integer, Integer> data, int events, boolean loggerState) {
        setName(name);
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

    public int getMetrics() {return this.metrics;}
    public void setMetrics(int metrics) {this.metrics = metrics;}

    public int getRequests() {return this.apiRequests;}
    public void setRequests(int requests) {this.apiRequests = requests;}

    public Pair<Integer, Integer> getData() {return this.data;}
    public void setData(Pair<Integer, Integer> data) {this.data = data;}

    public int getEvents() {return this.events;}
    public void setEvents(int events) {this.events = events;}

    public boolean getLoggingState() {return this.loggerState;}
    public void setLoggingState(boolean isLogger) {this.loggerState = isLogger;}
}
