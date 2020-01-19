package cloud.view.services;

import cloud.configuration.Config;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;

public class PaneNetworkService extends PaneServiceProperties {

    private Label networkTypeLbl;
    private Label connectionsLbl;
    private Label requestsLbl;
    private Label dataLbl;
    private Label dataOutLbl;
    private Label httpRequestsLbl;
    private Label dnsRequestsLbl;
    private Label zonesLbl;

    private ComboBox<String> networkTypeBox;
    private Spinner<String> requestsSpinner;
    private Spinner<String> dataSpinner;
    private Spinner<String> dataOutSpinner;
    private Spinner<String> zonesSpinner;

    public PaneNetworkService() {
        networkTypeLbl = new Label("Type:");
        networkTypeBox = new ComboBox<>(Config.getInstance().getConfigValues("network-type"));

        connectionsLbl = new Label("# of connections:");
        requestsLbl = new Label("# of requests:");
        dataLbl = new Label("# of transferred data in GB:");
        dataOutLbl = new Label("# of transferred data in GB into internet:");
        httpRequestsLbl = new Label("# of http-requests:");
        dnsRequestsLbl = new Label("# of dns-requests:");
        zonesLbl = new Label("# of zones:");

        requestsSpinner = new Spinner<>(1, 1000000, 1);
        dataSpinner = new Spinner<>(1, 1000000, 1);
        dataOutSpinner = new Spinner<>(1, 1000000, 1);
        zonesSpinner = new Spinner<>(1, 1000000, 1);

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
    public String getRequests() {return this.requestsSpinner.getEditor().getText();}
    public String getData() {return this.dataSpinner.getEditor().getText();}
    public String getDataOut() {return this.dataOutSpinner.getEditor().getText();}
    public String getZones() {return this.zonesSpinner.getEditor().getText();}

    private void recoverControls() {
        getChildren().clear();
        super.setControls();
        add(networkTypeLbl, 0, 2);
        add(networkTypeBox, 1, 2);
    }

    private void setPrivateControls() {
        add(connectionsLbl, 0, 3);
        add(requestsSpinner, 1, 3);
        add(new Label("per hour"), 2, 3);
        add(dataLbl, 0, 4);
        add(dataSpinner, 1, 4);
        add(new Label("per hour"), 2, 4);
    }

    private void setAPIControls() {
        add(requestsLbl, 0, 3);
        add(requestsSpinner, 1, 3);
        add(new Label("per month"), 2, 3);
        add(dataLbl, 0, 4);
        add(dataSpinner, 1, 4);
        add(new Label("per month"), 2, 4);
    }

    private void setCDNControls() {
        add(dataLbl, 0, 3);
        add(dataSpinner, 1, 3);
        add(new Label("per month"), 2, 3);
        add(dataOutLbl, 0, 4);
        add(dataOutSpinner, 1, 4);
        add(new Label("per month"), 2, 4);
        add(httpRequestsLbl, 0, 5);
        add(requestsSpinner, 1, 5);
        add(new Label("per month"), 2, 5);
    }

    private void setDNSControls() {
        add(zonesLbl, 0, 3);
        add(zonesSpinner, 1, 3);
        add(dnsRequestsLbl, 0, 4);
        add(dataSpinner, 1, 4);
        add(new Label("per month"), 2, 4);
    }
}
