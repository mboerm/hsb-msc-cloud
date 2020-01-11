package cloud.view.components;

import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;

public class PaneMonitoringComponent extends PaneComponent {

    private Spinner metricsSpinner;
    private Spinner requestsSpinner;
    private Spinner dataSpinner;
    private Spinner eventsSpinner;
    private CheckBox isLoggingBox;

    public PaneMonitoringComponent() {
        setIdentifier("Monitoring");

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

        add(metricsLbl, 0, 1);
        add(metricsSpinner, 1, 1);
        add(new Label("per month"), 2, 1);
        add(requestsLbl, 0, 2);
        add(requestsSpinner, 1, 2);
        add(new Label("per month"), 2, 2);
        add(dataLbl, 0, 3);
        add(dataSpinner, 1, 3);
        add(new Label("per month"), 2, 3);
        add(eventsLbl, 0, 4);
        add(eventsSpinner, 1, 4);
        add(new Label("per month"), 2, 4);
        add(isLoggingLbl, 0, 5);
        add(isLoggingBox, 1, 5);
    }

    public String getMetrics() {return this.metricsSpinner.getEditor().getText();}
    public String getRequests() {return this.requestsSpinner.getEditor().getText();}
    public String getData() {return this.dataSpinner.getEditor().getText();}
    public String getEvents() {return this.eventsSpinner.getEditor().getText();}
    public Boolean getLoggingState() {return this.isLoggingBox.isSelected();}
}
