package cloud.view.services;

import cloud.configuration.Config;
import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.util.Pair;

import java.util.Arrays;

public class DatabaseServicePane extends ServicePropertiesPane {

    private Label databaseTypeLbl;
    private String[] labels;

    private ComboBox<String> databaseTypeBox;
    private ComboBox<String> databaseSchemeBox;
    private ComboBox<String> instanceTypeBox;
    private Spinner<Integer> durationSpinner;
    private Spinner<Integer> storageSpinner;
    private Spinner<Integer> backupSpinner;
    private Spinner<Integer> dataSpinner;
    private Spinner<Integer> numOneSpinner;
    private Spinner<Integer> numTwoSpinner;

    public DatabaseServicePane() {
        databaseTypeLbl = new Label("Type:");
        databaseTypeBox = new ComboBox<>(Config.getInstance().getConfigValues("database-system-type"));
        databaseSchemeBox = new ComboBox<>(Config.getInstance().getConfigValues("database-sql-scheme"));
        String[] instanceTypes = Config.getInstance().getConfigValuesAsArray("service-instance-type");
        instanceTypeBox = new ComboBox<>(FXCollections.observableArrayList(Arrays.copyOfRange(instanceTypes, 0, instanceTypes.length-2)));

        durationSpinner = new Spinner<>(1, 1000000, 1);
        storageSpinner = new Spinner<>(1, 1000000, 1);
        backupSpinner = new Spinner<>(1, 1000000, 1);
        dataSpinner = new Spinner<>(1, 1000000, 1);
        numOneSpinner = new Spinner<>(1, 1000000, 1);
        numTwoSpinner = new Spinner<>(1, 1000000, 1);

        durationSpinner.setEditable(true);
        storageSpinner.setEditable(true);
        backupSpinner.setEditable(true);
        dataSpinner.setEditable(true);
        numOneSpinner.setEditable(true);
        numTwoSpinner.setEditable(true);

        add(databaseTypeLbl, 0, 2);
        add(databaseTypeBox, 1, 2);

        databaseTypeBox.getSelectionModel().selectedItemProperty().addListener((ov, oldItem, newItem) -> {
            recoverControls();
            String[] type = Config.getInstance().getConfigValuesAsArray("database-system-type");
            if (newItem.equals(type[0])) {
                setSQLControls();
            } else if (newItem.equals(type[1])) {
                setNoSQLControls();
            } else if (newItem.equals(type[2])) {
                setDocumentControls();
            } else if (newItem.equals(type[3])) {
                setCacheControls();
            }
            getScene().getWindow().sizeToScene();
        });
    }

    public String getDatabaseType() {return this.databaseTypeBox.getValue();}
    public void setDatabaseType(String item) {this.databaseTypeBox.getSelectionModel().select(item);}
    public String getDatabaseScheme() {return this.databaseSchemeBox.getValue();}
    public void setDatabaseScheme(String item) {this.databaseSchemeBox.getSelectionModel().select(item);}
    public String getInstanceType() {return this.instanceTypeBox.getValue();}
    public void setInstanceType(String item) {this.instanceTypeBox.getSelectionModel().select(item);}
    public Integer getDuration() {return this.durationSpinner.getValue();}
    public void setDuration(Integer value) {this.durationSpinner.getValueFactory().setValue(value);}
    public Integer getStorage() {return this.storageSpinner.getValue();}
    public void setStorage(Integer value) {this.storageSpinner.getValueFactory().setValue(value);}
    public Integer getBackup() {return this.backupSpinner.getValue();}
    public void setBackup(Integer value) {this.backupSpinner.getValueFactory().setValue(value);}
    public Integer getData() {return this.dataSpinner.getValue();}
    public void setData(Integer value) {this.dataSpinner.getValueFactory().setValue(value);}
    public Pair<Integer,Integer> getNum() {return new Pair<>(
        this.numOneSpinner.getValue(),
        this.numTwoSpinner.getValue());
    }
    public void setNum(Pair<Integer,Integer> values) {
        this.numOneSpinner.getValueFactory().setValue(values.getKey());
        this.numTwoSpinner.getValueFactory().setValue(values.getValue());
    }

    @Override
    void recoverControls() {
        getChildren().clear();
        super.setControls();
        add(databaseTypeLbl, 0, 2);
        add(databaseTypeBox, 1, 2);
    }

    private void setSQLControls() {
        labels = Config.getInstance().getConfigValuesAsArray("database-sql-labels");
        add(new Label(labels[0]), 0, 3);
        add(databaseSchemeBox, 1, 3);
        add(new Label(labels[1]), 0, 4);
        add(instanceTypeBox, 1, 4);
        add(new Label(labels[2]), 0, 5);
        add(numOneSpinner, 1, 5);
        add(new Label(labels[3]), 0, 6);
        add(numTwoSpinner, 1, 6);
        add(new Label(labels[4]), 0, 7);
        add(durationSpinner, 1, 7);
        add(new Label("per month"), 2, 7);
        add(new Label(labels[5]), 0, 8);
        add(storageSpinner, 1, 8);
        add(new Label("per month"), 2, 8);
        add(new Label(labels[6]), 0, 9);
        add(backupSpinner, 1, 9);
        add(new Label("per month"), 2, 9);
        add(new Label(labels[7]), 0, 10);
        add(dataSpinner, 1, 10);
        add(new Label("per month"), 2, 10);
    }

    private void setNoSQLControls() {
        labels = Config.getInstance().getConfigValuesAsArray("database-nosql-labels");
        add(new Label(labels[0]), 0, 3);
        add(numOneSpinner, 1, 3);
        add(new Label(labels[1]), 0, 4);
        add(numTwoSpinner, 1, 4);
        add(new Label(labels[2]), 0, 5);
        add(storageSpinner, 1, 5);
        add(new Label("per month"), 2, 5);
        add(new Label(labels[3]), 0, 6);
        add(dataSpinner, 1, 6);
        add(new Label("per month"), 2, 6);
    }

    private void setDocumentControls() {
        labels = Config.getInstance().getConfigValuesAsArray("database-document-labels");
        add(new Label(labels[0]), 0, 3);
        add(numOneSpinner, 1, 3);
        add(new Label("per month"), 2, 3);
        add(new Label(labels[1]), 0, 4);
        add(numTwoSpinner, 1, 4);
        add(new Label("per month"), 2, 4);
        add(new Label(labels[2]), 0, 5);
        add(storageSpinner, 1, 5);
        add(new Label("per month"), 2, 5);
        add(new Label(labels[3]), 0, 6);
        add(dataSpinner, 1, 6);
        add(new Label("per month"), 2, 6);
    }

    private void setCacheControls() {
        labels = Config.getInstance().getConfigValuesAsArray("database-cache-labels");
        add(new Label(labels[0]), 0, 3);
        add(numOneSpinner, 1, 3);
        add(new Label(labels[1]), 0, 4);
        add(numTwoSpinner, 1, 4);
        add(new Label(labels[2]), 0, 5);
        add(durationSpinner, 1, 5);
        add(new Label("per month"), 2, 5);
        add(new Label(labels[3]), 0, 6);
        add(dataSpinner, 1, 6);
        add(new Label("per month"), 2, 6);
    }
}
