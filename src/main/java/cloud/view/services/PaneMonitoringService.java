package cloud.view.services;

import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;

public class PaneMonitoringService extends PaneServiceProperties {

    private Spinner<String> metricsSpinner;
    private Spinner<String> requestsSpinner;
    private Spinner<String> dataSpinner;
    private Spinner<String> eventsSpinner;
    private CheckBox isLoggingBox;

    public PaneMonitoringService() {
        Label metricsLbl = new Label("# of metrics:");
        Label requestsLbl = new Label("# of requests:");
        Label dataLbl = new Label("# of data in GB:");
        Label eventsLbl = new Label("# of events:");
        Label isLoggingLbl = new Label("Logging:");

        metricsSpinner = new Spinner<>(1, 1000000, 1);
        requestsSpinner = new Spinner<>(1, 1000000, 1);
        dataSpinner = new Spinner<>(1, 1000000, 1);
        eventsSpinner = new Spinner<>(1, 1000000, 1);

        metricsSpinner.setEditable(true);
        requestsSpinner.setEditable(true);
        dataSpinner.setEditable(true);
        eventsSpinner.setEditable(true);

        isLoggingBox = new CheckBox();

        add(metricsLbl, 0, 2);
        add(metricsSpinner, 1, 2);
        add(new Label("per month"), 2, 2);
        add(requestsLbl, 0, 3);
        add(requestsSpinner, 1, 3);
        add(new Label("per month"), 2, 3);
        add(dataLbl, 0, 4);
        add(dataSpinner, 1, 4);
        add(new Label("per month"), 2, 4);
        add(eventsLbl, 0, 5);
        add(eventsSpinner, 1, 5);
        add(new Label("per month"), 2, 5);
        add(isLoggingLbl, 0, 6);
        add(isLoggingBox, 1, 6);
    }

    public String getMetrics() {return this.metricsSpinner.getEditor().getText();}
    public String getRequests() {return this.requestsSpinner.getEditor().getText();}
    public String getData() {return this.dataSpinner.getEditor().getText();}
    public String getEvents() {return this.eventsSpinner.getEditor().getText();}
    public Boolean getLoggingState() {return this.isLoggingBox.isSelected();}
}
