package cloud.view.services;

import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;

public class PaneMonitoringService extends PaneServiceProperties {

    private Spinner<Integer> metricsSpinner;
    private Spinner<Integer> requestsSpinner;
    private Spinner<Integer> dataSpinner;
    private Spinner<Integer> eventsSpinner;
    private CheckBox isLoggingBox;

    public PaneMonitoringService() {
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

    public String getMetrics() {return this.metricsSpinner.getEditor().getText();}
    public String getRequests() {return this.requestsSpinner.getEditor().getText();}
    public String getData() {return this.dataSpinner.getEditor().getText();}
    public String getEvents() {return this.eventsSpinner.getEditor().getText();}
    public Boolean getLoggingState() {return this.isLoggingBox.isSelected();}

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
