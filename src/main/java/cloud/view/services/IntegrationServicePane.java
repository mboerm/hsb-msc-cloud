package cloud.view.services;

import cloud.configuration.Config;
import cloud.configuration.Constants;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;

/**
 * Integration service panel
 */
public class IntegrationServicePane extends ServicePropertiesPane {
    private Label integrationTypeLbl;
    private ComboBox<String> integrationTypeBox;
    private ComboBox<String> integrationModeBox;
    private Spinner<Integer> dataSpinner;
    private Spinner<Integer> requestsSpinner;
    private Spinner<Integer> pushSpinner;
    private Spinner<Integer> httpSpinner;
    private Spinner<Integer> mailSpinner;
    private Spinner<Integer> smsSpinner;

    /**
     * Constructor
     */
    public IntegrationServicePane() {
        integrationTypeLbl = new Label("Type:");
        integrationTypeBox = new ComboBox<>(Config.getInstance().getConfigValues("integration-type"));
        integrationModeBox = new ComboBox<>(Config.getInstance().getConfigValues("integration-communication-mode"));
        dataSpinner = new Spinner<>(Constants.SPINNER_MIN, Constants.SPINNER_MAX, Constants.SPINNER_INIT);
        requestsSpinner = new Spinner<>(Constants.SPINNER_MIN, Constants.SPINNER_MAX, Constants.SPINNER_INIT);
        pushSpinner = new Spinner<>(Constants.SPINNER_MIN, Constants.SPINNER_MAX, Constants.SPINNER_INIT);
        httpSpinner = new Spinner<>(Constants.SPINNER_MIN, Constants.SPINNER_MAX, Constants.SPINNER_INIT);
        mailSpinner = new Spinner<>(Constants.SPINNER_MIN, Constants.SPINNER_MAX, Constants.SPINNER_INIT);
        smsSpinner = new Spinner<>(Constants.SPINNER_MIN, Constants.SPINNER_MAX, Constants.SPINNER_INIT);

        dataSpinner.setEditable(true);
        requestsSpinner.setEditable(true);
        pushSpinner.setEditable(true);
        httpSpinner.setEditable(true);
        mailSpinner.setEditable(true);
        smsSpinner.setEditable(true);

        add(integrationTypeLbl, 0, 2);
        add(integrationTypeBox, 1, 2);

        /* set controls by integration type */
        integrationTypeBox.getSelectionModel().selectedItemProperty().addListener((ov, oldItem, newItem) -> {
            recoverControls();
            String[] type = Config.getInstance().getConfigValuesAsArray("integration-type");
            if (newItem.equals(type[0])) {
                setMessageControls();
                disableMessageControls(true);
            }
            getScene().getWindow().sizeToScene();
        });

        /* toggle message controls by communication mode */
        integrationModeBox.getSelectionModel().selectedItemProperty().addListener((ov, oldItem, newItem) -> {
            if (newItem.equals(Config.getInstance().getConfigValuesAsArray("integration-communication-mode")[0]))
                disableMessageControls(false);
            else {
                disableMessageControls(true);
            }
        });
    }

    public String getIntegrationType() {return this.integrationTypeBox.getValue();}
    public void setIntegrationType(String item) {this.integrationTypeBox.getSelectionModel().select(item);}
    public String getIntegrationMode() {return this.integrationModeBox.getValue();}
    public void setIntegrationMode(String item) {this.integrationModeBox.getSelectionModel().select(item);}
    public int getData() {return this.dataSpinner.getValue();}
    public void setData(int value) {this.dataSpinner.getValueFactory().setValue(value);}
    public int getRequests() {return this.requestsSpinner.getValue();}
    public void setRequests(int value) {this.requestsSpinner.getValueFactory().setValue(value);}
    public int[] getMessages() {return new int[] {
        this.pushSpinner.getValue(),
        this.httpSpinner.getValue(),
        this.mailSpinner.getValue(),
        this.smsSpinner.getValue(),
    };}
    public void setMessages(int[] values) {
        if (values == null) {
            values = new int[] {1,1,1,1};
        }
        this.pushSpinner.getValueFactory().setValue(values[0]);
        this.httpSpinner.getValueFactory().setValue(values[1]);
        this.mailSpinner.getValueFactory().setValue(values[2]);
        this.smsSpinner.getValueFactory().setValue(values[3]);
    }

    @Override
    void recoverControls() {
        getChildren().clear();
        super.setControls();
        add(integrationTypeLbl, 0, 2);
        add(integrationTypeBox, 1, 2);
    }

    /* set controls for type messaging */
    private void setMessageControls() {
        String[] labels = Config.getInstance().getConfigValuesAsArray("integration-messaging-labels");
        add(new Label(labels[0]), 0, 3);
        add(integrationModeBox, 1, 3);
        add(new Label(labels[1]), 0, 4);
        add(dataSpinner, 1, 4);
        add(new Label("per month"), 2, 4);
        add(new Label(labels[2]), 0, 5);
        add(requestsSpinner, 1, 5);
        add(new Label("per month"), 2, 5);
        add(new Label(labels[3]), 0, 6);
        add(pushSpinner, 1, 6);
        add(new Label("per month"), 2, 6);
        add(new Label(labels[4]), 0, 7);
        add(httpSpinner, 1, 7);
        add(new Label("per month"), 2, 7);
        add(new Label(labels[5]), 0, 8);
        add(mailSpinner, 1, 8);
        add(new Label("per month"), 2, 8);
        add(new Label(labels[6]), 0, 9);
        add(smsSpinner, 1, 9);
        add(new Label("per month"), 2, 9);
    }

    /* disable message controls */
    private void disableMessageControls(boolean show) {
        this.pushSpinner.setDisable(show);
        this.httpSpinner.setDisable(show);
        this.mailSpinner.setDisable(show);
        this.smsSpinner.setDisable(show);
    }
}
