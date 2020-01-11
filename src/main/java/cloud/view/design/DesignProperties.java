package cloud.view.design;

import cloud.configuration.Config;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

public class DesignProperties extends GridPane {

    private TextField usagePeriodField;
    private ComboBox regionComboBox;
    private Spinner<Integer> numOfInstancesSpinner;
    private Spinner<Integer> numOfRequestsSpinner;
    private Spinner<Integer> numOfCapacitySpinner;
    private ComboBox periodOfRequestsComboBox;
    private ComboBox periodOfCapacityComboBox;

    public DesignProperties() {
        setPadding(new Insets(10, 10, 10, 10));
        setVgap(10);
        setHgap(10);

        Label usagePeriodLbl = new Label("Primary usage period: ");
        usagePeriodField = new TextField();
        usagePeriodField.setPromptText("hh:mm - hh:mm");

        Label regionLbl = new Label("Primary region: ");
        regionComboBox = new ComboBox();
        regionComboBox.getItems().addAll(Config.getInstance().getConfigValues("component-regions"));

        Label numOfInstancesLbl = new Label("# of compute instances: ");
        numOfInstancesSpinner = new Spinner<>(1, 100, 1);
        numOfInstancesSpinner.setEditable(true);

        Label numOfRequestsLbl = new Label("# of requests: ");
        numOfRequestsSpinner = new Spinner<>(100000, 10000000, 100000);
        numOfRequestsSpinner.setEditable(true);
        periodOfRequestsComboBox = new ComboBox();
        periodOfRequestsComboBox.getItems().addAll(Config.getInstance().getConfigValues("component-property-period"));

        Label numOfCapacityLbl = new Label("# of storage capacity: ");
        numOfCapacitySpinner = new Spinner<>(10, 10000, 100);
        numOfCapacitySpinner.setEditable(true);
        periodOfCapacityComboBox = new ComboBox();
        periodOfCapacityComboBox.getItems().addAll(Config.getInstance().getConfigValues("component-property-period"));

        add(usagePeriodLbl, 0, 0);
        add(usagePeriodField, 1, 0);
        add(regionLbl, 0, 1);
        add(regionComboBox, 1, 1);
        add(numOfInstancesLbl, 0, 2);
        add(numOfInstancesSpinner, 1, 2);
        add(numOfRequestsLbl, 0, 3);
        add(numOfRequestsSpinner, 1, 3);
        add(periodOfRequestsComboBox, 1, 4);
        add(numOfCapacityLbl, 0, 5);
        add(numOfCapacitySpinner, 1, 5);
        add(periodOfCapacityComboBox, 1, 6);
    }

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
    public ComboBox getPrimaryRegionComboBox() {
        return this.regionComboBox;
    }
    public ComboBox getPeriodOfRequestsComboBox() {
        return this.periodOfRequestsComboBox;
    }
    public ComboBox getPeriodOfCapacityComboBox() {
        return this.periodOfCapacityComboBox;
    }
}
