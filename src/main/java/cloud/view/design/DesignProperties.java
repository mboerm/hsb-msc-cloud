package cloud.view.design;

import cloud.configuration.Config;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

public class DesignProperties extends GridPane {

    private ComboBox<String> providerBox;
    private TextField usagePeriodField;
    private ComboBox<String> regionBox;
    private Spinner<Integer> numOfInstancesSpinner;
    private Spinner<Integer> numOfRequestsSpinner;
    private Spinner<Integer> numOfCapacitySpinner;
    private ComboBox<String> periodOfRequestsBox;
    private ComboBox<String> periodOfCapacityBox;

    public DesignProperties() {
        setPadding(new Insets(10, 10, 10, 10));
        setVgap(10);
        setHgap(10);

        Label providerLbl = new Label("Provider:");
        providerBox = new ComboBox<>(Config.getInstance().getConfigValues("provider-services"));

        Label usagePeriodLbl = new Label("Primary usage period:");
        usagePeriodField = new TextField();
        usagePeriodField.setPromptText("hh:mm - hh:mm");

        Label regionLbl = new Label("Primary region:");
        regionBox = new ComboBox<>(Config.getInstance().getConfigValues("service-regions"));

        Label numOfInstancesLbl = new Label("# of compute instances:");
        numOfInstancesSpinner = new Spinner<>(1, 100, 1);
        numOfInstancesSpinner.setEditable(true);

        Label numOfRequestsLbl = new Label("# of requests:");
        numOfRequestsSpinner = new Spinner<>(100000, 10000000, 100000);
        numOfRequestsSpinner.setEditable(true);
        periodOfRequestsBox = new ComboBox<>(Config.getInstance().getConfigValues("service-property-period"));

        Label numOfCapacityLbl = new Label("# of storage capacity:");
        numOfCapacitySpinner = new Spinner<>(10, 10000, 100);
        numOfCapacitySpinner.setEditable(true);
        periodOfCapacityBox = new ComboBox<>(Config.getInstance().getConfigValues("service-property-period"));

        add(providerLbl, 0, 0);
        add(providerBox, 1, 0);
        add(usagePeriodLbl, 0, 1);
        add(usagePeriodField, 1, 1);
        add(regionLbl, 0, 2);
        add(regionBox, 1, 2);
        add(numOfInstancesLbl, 0, 3);
        add(numOfInstancesSpinner, 1, 3);
        add(numOfRequestsLbl, 0, 4);
        add(numOfRequestsSpinner, 1, 4);
        add(periodOfRequestsBox, 1, 5);
        add(numOfCapacityLbl, 0, 6);
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
    public ComboBox<String> getPrimaryRegionBox() {
        return this.regionBox;
    }
    public ComboBox<String> getPeriodOfRequestsBox() {
        return this.periodOfRequestsBox;
    }
    public ComboBox<String> getPeriodOfCapacityBox() {
        return this.periodOfCapacityBox;
    }
}
