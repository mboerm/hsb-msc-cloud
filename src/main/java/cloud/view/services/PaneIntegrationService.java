package cloud.view.services;

import cloud.configuration.Config;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;

public class PaneIntegrationService extends PaneServiceProperties {
    private Label integrationTypeLbl;
    private ComboBox<String> integrationTypeBox;
    private Spinner<Integer> dataSpinner;
    private Spinner<Integer> requestsSpinner;
    private Spinner<Integer> pushSpinner;
    private Spinner<Integer> httpSpinner;
    private Spinner<Integer> mailSpinner;
    private Spinner<Integer> smsSpinner;

    public PaneIntegrationService() {
        integrationTypeLbl = new Label("Type:");
        integrationTypeBox = new ComboBox<>(Config.getInstance().getConfigValues("integration-type"));
        dataSpinner = new Spinner<>(1, 10000, 1);
        requestsSpinner = new Spinner<>(1, 10000000, 1);
        pushSpinner = new Spinner<>(1, 10000000, 1);
        httpSpinner = new Spinner<>(1, 10000000, 1);
        mailSpinner = new Spinner<>(1, 10000000, 1);
        smsSpinner = new Spinner<>(1, 10000000, 1);

        dataSpinner.setEditable(true);
        requestsSpinner.setEditable(true);
        pushSpinner.setEditable(true);
        httpSpinner.setEditable(true);
        mailSpinner.setEditable(true);
        smsSpinner.setEditable(true);

        setMessageControls();
        disableMessageControls(true);

        integrationTypeBox.getSelectionModel().selectedItemProperty().addListener((ov, oldItem, newItem) -> {
            if (newItem.equals(Config.getInstance().getConfigValuesAsArray("integration-type")[0]))
                disableMessageControls(false);
            else {
                disableMessageControls(true);
            }
        });
    }

    public String getIntegrationType() {return this.integrationTypeBox.getValue();}
    public void setIntegrationType(String item) {this.integrationTypeBox.getSelectionModel().select(item);}
    public Integer getData() {return this.dataSpinner.getValue();}
    public void setData(Integer value) {this.dataSpinner.getValueFactory().setValue(value);}
    public Integer getRequests() {return this.requestsSpinner.getValue();}
    public void setRequests(Integer value) {this.requestsSpinner.getValueFactory().setValue(value);}
    public Integer[] getMessages() {return new Integer[] {
        this.pushSpinner.getValue(),
        this.httpSpinner.getValue(),
        this.mailSpinner.getValue(),
        this.smsSpinner.getValue(),
    };}
    public void setMessages(Integer[] values) {
        this.pushSpinner.getValueFactory().setValue(values[0]);
        this.httpSpinner.getValueFactory().setValue(values[1]);
        this.mailSpinner.getValueFactory().setValue(values[2]);
        this.smsSpinner.getValueFactory().setValue(values[3]);
    }

    private void setMessageControls() {
        add(integrationTypeLbl, 0, 3);
        add(integrationTypeBox, 1, 3);
        add(new Label("# of data in GB:"), 0, 4);
        add(dataSpinner, 1, 4);
        add(new Label("per month"), 2, 4);
        add(new Label("# of requests:"), 0, 5);
        add(requestsSpinner, 1, 5);
        add(new Label("per month"), 2, 5);
        add(new Label("# of push messages:"), 0, 6);
        add(pushSpinner, 1, 6);
        add(new Label("per month"), 2, 6);
        add(new Label("# of http messages:"), 0, 7);
        add(httpSpinner, 1, 7);
        add(new Label("per month"), 2, 7);
        add(new Label("# of mail messages:"), 0, 8);
        add(mailSpinner, 1, 8);
        add(new Label("per month"), 2, 8);
        add(new Label("# of sms messages:"), 0, 9);
        add(smsSpinner, 1, 9);
        add(new Label("per month"), 2, 9);
    }

    private void disableMessageControls(boolean show) {
        this.pushSpinner.setDisable(show);
        this.httpSpinner.setDisable(show);
        this.mailSpinner.setDisable(show);
        this.smsSpinner.setDisable(show);
    }
}
