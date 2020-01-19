package cloud.view.services;

import cloud.configuration.Config;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.util.Pair;

public class PaneAnalyticService extends PaneServiceProperties {

    private Label analyticTypeLbl;
    private Label dataLbl;
    private Label dataOutLbl;
    private Label activitiesOnLbl;
    private Label activitiesOffLbl;
    private Label requestsLbl;
    private Label unitsLbl;
    private Label instanceSizeLbl;
    private Label instanceHoursLbl;

    private ComboBox<String> analyticTypeBox;
    private ComboBox<String> instanceSizeBox;
    private Spinner<String> dataSpinner;
    private Spinner<String> dataOutSpinner;
    private Spinner<String> activitiesOnSpinner;
    private Spinner<String> activitiesOffSpinner;
    private Spinner<String> unitsSpinner;

    public PaneAnalyticService() {
        analyticTypeLbl = new Label("Type:");
        analyticTypeBox = new ComboBox<>(Config.getInstance().getConfigValues("analytic-type"));

        dataLbl = new Label("# of processed data in GB:");
        dataOutLbl = new Label("# of transferred data in GB:");
        activitiesOnLbl = new Label("# of on-premise activities:");
        activitiesOffLbl = new Label("# of off-premise activities:");
        requestsLbl = new Label("# of requests:");
        unitsLbl = new Label("# of units:");
        instanceSizeLbl = new Label("Instance size:");
        instanceHoursLbl = new Label("# of instance-hours");

        instanceSizeBox = new ComboBox<>(Config.getInstance().getConfigValues("service-instance-size"));
        dataSpinner = new Spinner<>(1, 1000000, 1);
        dataOutSpinner = new Spinner<>(1, 1000000, 1);
        activitiesOnSpinner = new Spinner<>(1, 1000000, 1);
        activitiesOffSpinner = new Spinner<>(1, 1000000, 1);
        unitsSpinner = new Spinner<>(1, 1000000, 1);

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
                setDataCatalogControls();
            } else if (newItem.equals(type[4])) {
                setDataLakeControls();
            } else if (newItem.equals(type[5])) {
                setSearchControls();
            }
            getScene().getWindow().sizeToScene();
        });
    }

    public String getAnalyticType() {return this.analyticTypeBox.getValue();}
    public String getInstanceSize() {return this.instanceSizeBox.getValue();}
    public String getData() {return this.dataSpinner.getEditor().getText();}
    public String getDataOut() {return this.dataOutSpinner.getEditor().getText();}
    public Pair<String,String> getActivities() {return new Pair<>(
        this.activitiesOnSpinner.getEditor().getText(),
        this.activitiesOffSpinner.getEditor().getText());
    }
    public String getUnits() {return this.unitsSpinner.getEditor().getText();}

    private void recoverControls() {
        getChildren().clear();
        super.setControls();
        add(analyticTypeLbl, 0, 2);
        add(analyticTypeBox, 1, 2);
    }

    private void setDataQueryControls() {
        add(dataLbl, 0, 3);
        add(dataSpinner, 1, 3);
    }

    private void setDataTransferControls() {
        add(activitiesOnLbl, 0, 3);
        add(activitiesOnSpinner, 1, 3);
        add(activitiesOffLbl, 0, 4);
        add(activitiesOffSpinner, 1, 4);
    }

    private void setDataCatalogControls() {
        add(unitsLbl, 0, 3);
        add(dataSpinner, 1, 3);
        add(requestsLbl, 0, 4);
        add(unitsSpinner, 1, 4);
    }

    private void setDataLakeControls() {
        add(unitsLbl, 0, 3);
        add(unitsSpinner, 1, 3);
        add(new Label("per hour"), 2, 3);
        add(dataLbl, 0, 4);
        add(dataSpinner, 1, 4);
    }

    private void setSearchControls() {
        add(instanceSizeLbl, 0, 3);
        add(instanceSizeBox, 1, 3);
        add(instanceHoursLbl, 0, 4);
        add(unitsSpinner, 1, 4);
        add(dataLbl, 0, 5);
        add(dataSpinner, 1, 5);
        add(dataOutLbl, 0, 6);
        add(dataOutSpinner, 1, 6);
    }
}
