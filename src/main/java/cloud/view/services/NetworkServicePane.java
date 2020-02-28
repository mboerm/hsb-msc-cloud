package cloud.view.services;

import cloud.configuration.Config;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;

/**
 * Network service panel
 */
public class NetworkServicePane extends ServicePropertiesPane {

    private Label networkTypeLbl;
    private String[] labels;
    private ComboBox<String> networkTypeBox;
    private Spinner<Integer> requestsSpinner;
    private Spinner<Integer> dataSpinner;
    private Spinner<Integer> dataOutSpinner;
    private Spinner<Integer> numOneSpinner;
    private Spinner<Integer> numTwoSpinner;

    /**
     * Constructor
     */
    public NetworkServicePane() {
        networkTypeLbl = new Label("Type:");
        networkTypeBox = new ComboBox<>(Config.getInstance().getConfigValues("network-type"));

        requestsSpinner = new Spinner<>(1, 1000000, 1);
        dataSpinner = new Spinner<>(1, 1000000, 1);
        dataOutSpinner = new Spinner<>(1, 1000000, 1);
        numOneSpinner = new Spinner<>(1, 1000000, 1);
        numTwoSpinner = new Spinner<>(1, 1000000, 1);

        requestsSpinner.setEditable(true);
        dataSpinner.setEditable(true);
        dataOutSpinner.setEditable(true);
        numOneSpinner.setEditable(true);
        numTwoSpinner.setEditable(true);

        add(networkTypeLbl, 0, 2);
        add(networkTypeBox, 1, 2);

        /* set controls by network type */
        networkTypeBox.getSelectionModel().selectedItemProperty().addListener((ov, oldItem, newItem) -> {
            recoverControls();
            String[] type = Config.getInstance().getConfigValuesAsArray("network-type");
            if (newItem.equals(type[0])) {
                setVPCControls();
            } else if (newItem.equals(type[1])) {
                setVPNControls();
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
    public Integer getNumOne() {return this.numOneSpinner.getValue();}
    public void setNumOne(Integer value) {this.numOneSpinner.getValueFactory().setValue(value);}
    public Integer getNumTwo() {return this.numTwoSpinner.getValue();}
    public void setNumTwo(Integer value) {this.numTwoSpinner.getValueFactory().setValue(value);}

    @Override
    void recoverControls() {
        getChildren().clear();
        super.setControls();
        add(networkTypeLbl, 0, 2);
        add(networkTypeBox, 1, 2);
    }

    /* set controls for type vpc */
    private void setVPCControls() {
        labels = Config.getInstance().getConfigValuesAsArray("network-vpc-labels");
        add(new Label(labels[0]), 0, 3);
        add(requestsSpinner, 1, 3);
        add(new Label("per hour"), 2, 3);
        add(new Label(labels[1]), 0, 4);
        add(numOneSpinner, 1, 4);
        add(new Label("per hour"), 2, 4);
        add(new Label(labels[2]), 0, 5);
        add(numTwoSpinner, 1, 5);
        add(new Label(labels[3]), 0, 6);
        add(dataSpinner, 1, 6);
        add(new Label(labels[4]), 0, 7);
        add(dataOutSpinner, 1, 7);
    }

    /* set controls for type vpn */
    private void setVPNControls() {
        labels = Config.getInstance().getConfigValuesAsArray("network-vpn-labels");
        add(new Label(labels[0]), 0, 3);
        add(requestsSpinner, 1, 3);
        add(new Label("per hour"), 2, 3);
        add(new Label(labels[1]), 0, 4);
        add(dataSpinner, 1, 4);
        add(new Label(labels[2]), 0, 5);
        add(dataOutSpinner, 1, 5);
    }

    /* set controls for type api */
    private void setAPIControls() {
        labels = Config.getInstance().getConfigValuesAsArray("network-api-labels");
        add(new Label(labels[0]), 0, 3);
        add(requestsSpinner, 1, 3);
        add(new Label("per month"), 2, 3);
        add(new Label(labels[1]), 0, 4);
        add(dataSpinner, 1, 4);
        add(new Label("per month"), 2, 4);
    }

    /* set controls for type cdn */
    private void setCDNControls() {
        labels = Config.getInstance().getConfigValuesAsArray("network-cdn-labels");
        add(new Label(labels[0]), 0, 3);
        add(dataSpinner, 1, 3);
        add(new Label("per month"), 2, 3);
        add(new Label(labels[1]), 0, 4);
        add(dataOutSpinner, 1, 4);
        add(new Label("per month"), 2, 4);
        add(new Label(labels[2]), 0, 5);
        add(requestsSpinner, 1, 5);
        add(new Label("per month"), 2, 5);
    }

    /* set controls for type dns */
    private void setDNSControls() {
        labels = Config.getInstance().getConfigValuesAsArray("network-dns-labels");
        add(new Label(labels[0]), 0, 3);
        add(numOneSpinner, 1, 3);
        add(new Label(labels[1]), 0, 4);
        add(dataSpinner, 1, 4);
        add(new Label("per month"), 2, 4);
    }
}
