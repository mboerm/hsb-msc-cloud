package cloud.view.design;

import cloud.configuration.Config;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

public class DesignProperties extends GridPane {

    private ComboBox<String> providerBox;
    private TextField usagePeriodField;
    private ComboBox<String> locationBox;
    private Spinner<Integer> numOfInstancesSpinner;
    private Spinner<Integer> numOfRequestsSpinner;
    private Spinner<Integer> numOfCapacitySpinner;
    private ComboBox<String> periodOfRequestsBox;
    private ComboBox<String> periodOfCapacityBox;

    public DesignProperties() {
        setPadding(new Insets(10, 10, 10, 10));
        setVgap(10);
        setHgap(10);

        providerBox = new ComboBox<>(Config.getInstance().getConfigValues("provider-services"));

        usagePeriodField = new TextField();
        usagePeriodField.setPromptText("hh:mm - hh:mm");

        locationBox = new ComboBox<>(Config.getInstance().getConfigValues("service-location"));

        numOfInstancesSpinner = new Spinner<>(1, 100, 1);
        numOfInstancesSpinner.setEditable(true);

        numOfRequestsSpinner = new Spinner<>(1, 10000000, 1);
        numOfRequestsSpinner.setEditable(true);
        periodOfRequestsBox = new ComboBox<>(Config.getInstance().getConfigValues("service-property-period"));

        numOfCapacitySpinner = new Spinner<>(1, 1000000, 1);
        numOfCapacitySpinner.setEditable(true);
        periodOfCapacityBox = new ComboBox<>(Config.getInstance().getConfigValues("service-property-period"));

        String[] labels = Config.getInstance().getConfigValuesAsArray("design-property-labels");

        add(new Label(labels[0]), 0, 0);
        add(providerBox, 1, 0);
        add(new Label(labels[1]), 0, 1);
        add(locationBox, 1, 1);
        add(new Label(labels[2]), 0, 2);
        add(usagePeriodField, 1, 2);
        add(new Label(labels[3]), 0, 3);
        add(numOfInstancesSpinner, 1, 3);
        add(new Label(labels[4]), 0, 4);
        add(numOfRequestsSpinner, 1, 4);
        add(periodOfRequestsBox, 1, 5);
        add(new Label(labels[5]), 0, 6);
        add(numOfCapacitySpinner, 1, 6);
        add(periodOfCapacityBox, 1, 7);
    }

    public ComboBox<String> getProviderBox() {return this.providerBox;}
    public TextField getUsagePeriodField() {
        return this.usagePeriodField;
    }
    public Spinner<Integer> getNumOfInstancesSpinner() {
        return this.numOfInstancesSpinner;
    }
    public Spinner<Integer> getNumOfRequestsSpinner() {
        return this.numOfRequestsSpinner;
    }
    public Spinner<Integer> getNumOfCapacitySpinner() {
        return this.numOfCapacitySpinner;
    }
    public ComboBox<String> getPrimaryLocationBox() {
        return this.locationBox;
    }
    public ComboBox<String> getPeriodOfRequestsBox() {
        return this.periodOfRequestsBox;
    }
    public ComboBox<String> getPeriodOfCapacityBox() {
        return this.periodOfCapacityBox;
    }
}
