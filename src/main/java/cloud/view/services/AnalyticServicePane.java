package cloud.view.services;

import cloud.configuration.Config;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.util.Pair;

public class AnalyticServicePane extends ServicePropertiesPane {

    private Label analyticTypeLbl;

    private ComboBox<String> analyticTypeBox;
    private ComboBox<String> instanceSizeBox;
    private Spinner<Integer> dataSpinner;
    private Spinner<Integer> dataOutSpinner;
    private Spinner<Integer> activitiesOnSpinner;
    private Spinner<Integer> activitiesOffSpinner;
    private Spinner<Integer> unitsSpinner;

    public AnalyticServicePane() {
        analyticTypeLbl = new Label("Type:");
        analyticTypeBox = new ComboBox<>(Config.getInstance().getConfigValues("analytic-type"));

        instanceSizeBox = new ComboBox<>(Config.getInstance().getConfigValues("service-instance-size"));
        dataSpinner = new Spinner<>(1, 1000000, 1);
        dataOutSpinner = new Spinner<>(1, 1000000, 1);
        activitiesOnSpinner = new Spinner<>(1, 1000000, 1);
        activitiesOffSpinner = new Spinner<>(1, 1000000, 1);
        unitsSpinner = new Spinner<>(1, 1000000, 1);

        instanceSizeBox.setEditable(true);
        dataSpinner.setEditable(true);
        dataOutSpinner.setEditable(true);
        activitiesOnSpinner.setEditable(true);
        activitiesOffSpinner.setEditable(true);
        unitsSpinner.setEditable(true);

        add(analyticTypeLbl, 0, 2);
        add(analyticTypeBox, 1, 2);

        analyticTypeBox.getSelectionModel().selectedItemProperty().addListener((ov, oldItem, newItem) -> {
            recoverControls();
            String[] type = Config.getInstance().getConfigValuesAsArray("analytic-type");
            if (newItem.equals(type[0]) || newItem.equals(type[1])) {
                setDataQueryControls();
            } else if (newItem.equals(type[2])) {
                setDataTransferControls();
            } else if (newItem.equals(type[3])) {
                setDataStreamControls();
            } else if (newItem.equals(type[4])) {
                setDataCatalogControls();
            } else if (newItem.equals(type[5])) {
                setDataLakeControls();
            } else if (newItem.equals(type[6])) {
                setSearchControls();
            }
            getScene().getWindow().sizeToScene();
        });
    }

    public String getAnalyticType() {return this.analyticTypeBox.getValue();}
    public void setAnalyticType(String item) {this.analyticTypeBox.getSelectionModel().select(item);}

    public String getInstanceSize() {return this.instanceSizeBox.getValue();}
    public void setInstanceSize(String item) {this.instanceSizeBox.getSelectionModel().select(item);}

    public Integer getData() {return this.dataSpinner.getValue();}
    public void setData(Integer value) {this.dataSpinner.getValueFactory().setValue(value);}

    public Integer getDataOut() {return this.dataOutSpinner.getValue();}
    public void setDataOut(Integer value) {this.dataOutSpinner.getValueFactory().setValue(value);}

    public Pair<Integer,Integer> getActivities() {return new Pair<>(
        this.activitiesOnSpinner.getValue(),
        this.activitiesOffSpinner.getValue());
    }
    public void setActivities(Pair<Integer,Integer> values) {
        this.activitiesOnSpinner.getValueFactory().setValue(values.getKey());
        this.activitiesOffSpinner.getValueFactory().setValue(values.getValue());
    }

    public Integer getUnits() {return this.unitsSpinner.getValue();}
    public void setUnits(Integer value) {this.unitsSpinner.getValueFactory().setValue(value);}

    private void recoverControls() {
        getChildren().clear();
        super.setControls();
        add(analyticTypeLbl, 0, 2);
        add(analyticTypeBox, 1, 2);
    }

    private void setDataQueryControls() {
        add(new Label("# of processed data in GB:"), 0, 3);
        add(dataSpinner, 1, 3);
    }

    private void setDataTransferControls() {
        add(new Label("# of on-premise activities:"), 0, 3);
        add(activitiesOnSpinner, 1, 3);
        add(new Label("# of off-premise activities:"), 0, 4);
        add(activitiesOffSpinner, 1, 4);
    }

    private void setDataStreamControls() {
        add(new Label("# of hours:"), 0, 3);
        add(dataSpinner, 1, 3);
        add(new Label("# of units:"), 0, 4);
        unitsSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(500000, 100000000, 1000000));
        add(unitsSpinner, 1, 4);
        add(new Label("# of transferred data in GB:"), 0, 5);
        add(dataOutSpinner, 1, 5);
    }

    private void setDataCatalogControls() {
        add(new Label("# of units:"), 0, 3);
        add(dataSpinner, 1, 3);
        add(new Label("# of requests:"), 0, 4);
        add(unitsSpinner, 1, 4);
    }

    private void setDataLakeControls() {
        add(new Label("# of units:"), 0, 3);
        add(unitsSpinner, 1, 3);
        add(new Label("per hour"), 2, 3);
        add(new Label("# of processed data in GB:"), 0, 4);
        add(dataSpinner, 1, 4);
    }

    private void setSearchControls() {
        add(new Label("Instance size:"), 0, 3);
        add(instanceSizeBox, 1, 3);
        add(new Label("# of instance-hours"), 0, 4);
        add(unitsSpinner, 1, 4);
        add(new Label("# of processed data in GB:"), 0, 5);
        add(dataSpinner, 1, 5);
        add(new Label("# of transferred data in GB:"), 0, 6);
        add(dataOutSpinner, 1, 6);
    }
}
