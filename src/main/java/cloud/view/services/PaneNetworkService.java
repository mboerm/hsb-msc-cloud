package cloud.view.services;

import cloud.configuration.Config;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;

public class PaneNetworkService extends PaneServiceProperties {

    private Label networkTypeLbl;
    private ComboBox<String> networkTypeBox;
    private Spinner<Integer> requestsSpinner;
    private Spinner<Integer> dataSpinner;
    private Spinner<Integer> dataOutSpinner;
    private Spinner<Integer> zonesSpinner;

    public PaneNetworkService() {
        networkTypeLbl = new Label("Type:");
        networkTypeBox = new ComboBox<>(Config.getInstance().getConfigValues("network-type"));

        requestsSpinner = new Spinner<>(1, 1000000, 1);
        dataSpinner = new Spinner<>(1, 1000000, 1);
        dataOutSpinner = new Spinner<>(1, 1000000, 1);
        zonesSpinner = new Spinner<>(1, 1000000, 1);

        requestsSpinner.setEditable(true);
        dataSpinner.setEditable(true);
        dataOutSpinner.setEditable(true);
        zonesSpinner.setEditable(true);

        add(networkTypeLbl, 0, 2);
        add(networkTypeBox, 1, 2);

        networkTypeBox.getSelectionModel().selectedItemProperty().addListener((ov, oldItem, newItem) -> {
            recoverControls();
            String[] type = Config.getInstance().getConfigValuesAsArray("network-type");
            if (newItem.equals(type[0]) || newItem.equals(type[1])) {
                setPrivateControls();
            } else if (newItem.equals(type[2])) {
                setAPIControls();
            } else if (newItem.equals(type[3])) {
                setCDNControls();
            } else if (newItem.equals(type[4])) {
                setDNSControls();
            }
            getScene().getWindow().sizeToScene();
        });
    }

    public String getNetworkType() {return this.networkTypeBox.getValue();}
    public void setNetworkType(String item) {this.networkTypeBox.getSelectionModel().select(item);}
    public Integer getRequests() {return this.requestsSpinner.getValue();}
    public void setRequests(Integer value) {this.requestsSpinner.getValueFactory().setValue(value);}
    public Integer getData() {return this.dataSpinner.getValue();}
    public void setData(Integer value) {this.dataSpinner.getValueFactory().setValue(value);}
    public Integer getDataOut() {return this.dataOutSpinner.getValue();}
    public void setDataOut(Integer value) {this.dataOutSpinner.getValueFactory().setValue(value);}
    public Integer getZones() {return this.zonesSpinner.getValue();}
    public void setZones(Integer value) {this.zonesSpinner.getValueFactory().setValue(value);}

    private void recoverControls() {
        getChildren().clear();
        super.setControls();
        add(networkTypeLbl, 0, 2);
        add(networkTypeBox, 1, 2);
    }

    private void setPrivateControls() {
        add(new Label("# of connections:"), 0, 3);
        add(requestsSpinner, 1, 3);
        add(new Label("per hour"), 2, 3);
        add(new Label("# of transferred data in GB:"), 0, 4);
        add(dataSpinner, 1, 4);
        add(new Label("per hour"), 2, 4);
    }

    private void setAPIControls() {
        add(new Label("# of requests:"), 0, 3);
        add(requestsSpinner, 1, 3);
        add(new Label("per month"), 2, 3);
        add(new Label("# of transferred data in GB:"), 0, 4);
        add(dataSpinner, 1, 4);
        add(new Label("per month"), 2, 4);
    }

    private void setCDNControls() {
        add(new Label("# of transferred data in GB:"), 0, 3);
        add(dataSpinner, 1, 3);
        add(new Label("per month"), 2, 3);
        add(new Label("# of transferred data in GB into internet:"), 0, 4);
        add(dataOutSpinner, 1, 4);
        add(new Label("per month"), 2, 4);
        add(new Label("# of http-requests:"), 0, 5);
        add(requestsSpinner, 1, 5);
        add(new Label("per month"), 2, 5);
    }

    private void setDNSControls() {
        add(new Label("# of zones:"), 0, 3);
        add(zonesSpinner, 1, 3);
        add(new Label("# of dns-requests:"), 0, 4);
        add(dataSpinner, 1, 4);
        add(new Label("per month"), 2, 4);
    }
}
