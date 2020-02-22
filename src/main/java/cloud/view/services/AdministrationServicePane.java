package cloud.view.services;

import cloud.configuration.Config;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.util.Pair;

public class AdministrationServicePane extends ServicePropertiesPane {

    private Label administrationTypeLbl;
    private ComboBox<String> administrationTypeBox;
    private Spinner<Integer> metricsSpinner;
    private Spinner<Integer> requestsSpinner;
    private Spinner<Integer> dataCollectSpinner;
    private Spinner<Integer> dataSaveSpinner;
    private Spinner<Integer> eventsSpinner;
    private CheckBox isLoggingBox;

    public AdministrationServicePane() {
        administrationTypeLbl = new Label("Type:");
        administrationTypeBox = new ComboBox<>(Config.getInstance().getConfigValues("administration-type"));
        metricsSpinner = new Spinner<>(1, 1000000, 1);
        requestsSpinner = new Spinner<>(1, 1000000, 1);
        dataCollectSpinner = new Spinner<>(1, 1000000, 1);
        dataSaveSpinner = new Spinner<>(1, 1000000, 1);
        eventsSpinner = new Spinner<>(1, 1000000, 1);
        isLoggingBox = new CheckBox();

        metricsSpinner.setEditable(true);
        requestsSpinner.setEditable(true);
        dataCollectSpinner.setEditable(true);
        dataSaveSpinner.setEditable(true);
        eventsSpinner.setEditable(true);

        add(administrationTypeLbl, 0, 2);
        add(administrationTypeBox, 1, 2);

        administrationTypeBox.getSelectionModel().selectedItemProperty().addListener((ov, oldItem, newItem) -> {
            recoverControls();
            String[] type = Config.getInstance().getConfigValuesAsArray("administration-type");
            if (newItem.equals(type[0])) {
                setMonitoringControls();
            }
            getScene().getWindow().sizeToScene();
        });

    }

    public String getAdministrationType() {return this.administrationTypeBox.getValue();}
    public void setAdministrationType(String item) {this.administrationTypeBox.getSelectionModel().select(item);}
    public Integer getMetrics() {return this.metricsSpinner.getValue();}
    public void setMetrics(Integer value) {this.metricsSpinner.getValueFactory().setValue(value);}
    public Integer getRequests() {return this.requestsSpinner.getValue();}
    public void setRequests(Integer value) {this.requestsSpinner.getValueFactory().setValue(value);}
    public Pair<Integer, Integer> getData() {return new Pair<>(
        this.dataCollectSpinner.getValue(),
        this.dataSaveSpinner.getValue()
    );}
    public void setData(Pair<Integer,Integer> values) {
        this.dataCollectSpinner.getValueFactory().setValue(values.getKey());
        this.dataSaveSpinner.getValueFactory().setValue(values.getValue());
    }
    public Integer getEvents() {return this.eventsSpinner.getValue();}
    public void setEvents(Integer value) {this.eventsSpinner.getValueFactory().setValue(value);}
    public boolean getLoggingState() {return this.isLoggingBox.isSelected();}
    public void setLoggingState(boolean state) {this.isLoggingBox.setSelected(state);}

    @Override
    void recoverControls() {
        getChildren().clear();
        super.setControls();
        add(administrationTypeLbl, 0, 2);
        add(administrationTypeBox, 1, 2);
    }

    private void setMonitoringControls() {
        String[] labels = Config.getInstance().getConfigValuesAsArray("administration-monitoring-labels");
        add(new Label(labels[0]), 0, 3);
        add(metricsSpinner, 1, 3);
        add(new Label("per month"), 2, 3);
        add(new Label(labels[1]), 0, 4);
        add(requestsSpinner, 1, 4);
        add(new Label("per month"), 2, 4);
        add(new Label(labels[2]), 0, 5);
        add(dataCollectSpinner, 1, 5);
        add(new Label("per day"), 2, 5);
        add(new Label(labels[3]), 0, 6);
        add(dataSaveSpinner, 1, 6);
        add(new Label("per day"), 2, 6);
        add(new Label(labels[4]), 0, 7);
        add(eventsSpinner, 1, 7);
        add(new Label("per month"), 2, 7);
        add(new Label(labels[5]), 0, 8);
        add(isLoggingBox, 1, 8);
    }
}
