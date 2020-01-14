package cloud.view.services;

import cloud.configuration.Config;
import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;

public class PaneIntegrationService extends PaneServiceProperties {

    private ComboBox<String> integrationTypeBox;
    private Spinner<Integer> dataSpinner;
    private Spinner<Integer> requestsSpinner;

    private Spinner<Integer> pushSpinner;
    private Spinner<Integer> httpSpinner;
    private Spinner<Integer> mailSpinner;
    private Spinner<Integer> smsSpinner;

    public PaneIntegrationService() {
        Label typeLbl = new Label("Type:");
        Label dataLbl = new Label("# of data in GB:");
        Label requestsLbl = new Label("# of requests:");
        Label pushLbl = new Label("# of push messages:");
        Label httpLbl = new Label("# of http messages:");
        Label mailLbl = new Label("# of mail messages:");
        Label smsLbl = new Label("# of sms messages:");

        integrationTypeBox = new ComboBox<>(FXCollections.observableArrayList(
                Config.getInstance().getConfigValues("integration-type")));
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

        add(typeLbl, 0, 2);
        add(integrationTypeBox, 1, 2);
        add(dataLbl, 0, 3);
        add(dataSpinner, 1, 3);
        add(new Label("per month"), 2, 3);
        add(requestsLbl, 0, 4);
        add(requestsSpinner, 1, 4);
        add(new Label("per month"), 2, 4);

        add(pushLbl, 0, 5);
        add(pushSpinner, 1, 5);
        add(new Label("per month"), 2, 5);
        add(httpLbl, 0, 6);
        add(httpSpinner, 1, 6);
        add(new Label("per month"), 2, 6);
        add(mailLbl, 0, 7);
        add(mailSpinner, 1, 7);
        add(new Label("per month"), 2, 7);
        add(smsLbl, 0, 8);
        add(smsSpinner, 1, 8);
        add(new Label("per month"), 2, 8);

        disableMessageControls(true);

        integrationTypeBox.getSelectionModel().selectedItemProperty().addListener((ov, oldItem, newItem) -> {
            if (newItem.equals(Config.getInstance().getConfigValues("integration-type")[0]))
                disableMessageControls(false);
            else {
                disableMessageControls(true);
            }
        });
    }

    public String getType() {return this.integrationTypeBox.getEditor().getText();}
    public String getData() {return this.dataSpinner.getEditor().getText();}
    public String getRequests() {return this.requestsSpinner.getEditor().getText();}
    public String[] getMessages() {return new String[] {
            this.pushSpinner.getEditor().getText(),
            this.httpSpinner.getEditor().getText(),
            this.mailSpinner.getEditor().getText(),
            this.smsSpinner.getEditor().getText(),
    };}

    private void disableMessageControls(boolean show) {
        this.pushSpinner.setDisable(show);
        this.httpSpinner.setDisable(show);
        this.mailSpinner.setDisable(show);
        this.smsSpinner.setDisable(show);
    }
}
