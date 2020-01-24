package cloud.view.services;

import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;

public class MonitoringServicePane extends ServicePropertiesPane {

    private Spinner<Integer> metricsSpinner;
    private Spinner<Integer> requestsSpinner;
    private Spinner<Integer> dataSpinner;
    private Spinner<Integer> eventsSpinner;
    private CheckBox isLoggingBox;

    public MonitoringServicePane() {
        metricsSpinner = new Spinner<>(1, 1000000, 1);
        requestsSpinner = new Spinner<>(1, 1000000, 1);
        dataSpinner = new Spinner<>(1, 1000000, 1);
        eventsSpinner = new Spinner<>(1, 1000000, 1);
        isLoggingBox = new CheckBox();

        metricsSpinner.setEditable(true);
        requestsSpinner.setEditable(true);
        dataSpinner.setEditable(true);
        eventsSpinner.setEditable(true);

        setMonitoringControls();
    }

    public Integer getMetrics() {return this.metricsSpinner.getValue();}
    public void setMetrics(Integer value) {this.metricsSpinner.getValueFactory().setValue(value);}
    public Integer getRequests() {return this.requestsSpinner.getValue();}
    public void setRequests(Integer value) {this.requestsSpinner.getValueFactory().setValue(value);}
    public Integer getData() {return this.dataSpinner.getValue();}
    public void setData(Integer value) {this.dataSpinner.getValueFactory().setValue(value);}
    public Integer getEvents() {return this.eventsSpinner.getValue();}
    public void setEvents(Integer value) {this.eventsSpinner.getValueFactory().setValue(value);}
    public boolean getLoggingState() {return this.isLoggingBox.isSelected();}
    public void setLoggingState(boolean state) {this.isLoggingBox.setSelected(state);}

    private void setMonitoringControls() {
        add(new Label("# of metrics:"), 0, 2);
        add(metricsSpinner, 1, 2);
        add(new Label("per month"), 2, 2);
        add(new Label("# of requests:"), 0, 3);
        add(requestsSpinner, 1, 3);
        add(new Label("per month"), 2, 3);
        add(new Label("# of data in GB:"), 0, 4);
        add(dataSpinner, 1, 4);
        add(new Label("per month"), 2, 4);
        add(new Label("# of events:"), 0, 5);
        add(eventsSpinner, 1, 5);
        add(new Label("per month"), 2, 5);
        add(new Label("Logging:"), 0, 6);
        add(isLoggingBox, 1, 6);
    }
}
