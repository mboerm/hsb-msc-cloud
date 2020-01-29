package cloud.view.services;

import cloud.configuration.Config;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.util.Pair;

public class DatabaseServicePane extends ServicePropertiesPane {

    private Label databaseTypeLbl;

    private ComboBox<String> databaseTypeBox;
    private ComboBox<String> databaseSchemeBox;
    private ComboBox<String> instanceTypeBox;
    private ComboBox<String> instanceSizeBox;
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
        instanceTypeBox = new ComboBox<>(Config.getInstance().getConfigValues("database-instance-type"));
        instanceSizeBox = new ComboBox<>(Config.getInstance().getConfigValues("service-instance-size"));

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
    public String getInstanceSize() {return this.instanceSizeBox.getValue();}
    public void setInstanceSize(String item) {this.instanceSizeBox.getSelectionModel().select(item);}
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

    private void recoverControls() {
        getChildren().clear();
        super.setControls();
        add(databaseTypeLbl, 0, 2);
        add(databaseTypeBox, 1, 2);
    }

    private void setSQLControls() {
        add(new Label("Scheme:"), 0, 3);
        add(databaseSchemeBox, 1, 3);
        add(new Label("Instance type:"), 0, 4);
        add(instanceTypeBox, 1, 4);
        add(new Label("Instance size:"), 0, 5);
        add(instanceSizeBox, 1, 5);
        add(new Label("# of CPU:"), 0, 6);
        add(numOneSpinner, 1, 6);
        add(new Label("# of  in GB:"), 0, 7);
        add(numTwoSpinner, 1, 7);
        add(new Label("Instance hours:"), 0, 8);
        add(durationSpinner, 1, 8);
        add(new Label("per month"), 2, 8);
        add(new Label("Storage in GB:"), 0, 9);
        add(storageSpinner, 1, 9);
        add(new Label("per month"), 2, 9);
        add(new Label("Backup storage in GB:"), 0, 10);
        add(backupSpinner, 1, 10);
        add(new Label("per month"), 2, 10);
        add(new Label("Transferred data in GB:"), 0, 11);
        add(dataSpinner, 1, 11);
        add(new Label("per month"), 2, 11);
    }

    private void setNoSQLControls() {
        add(new Label("Read queries:"), 0, 3);
        add(numOneSpinner, 1, 3);
        add(new Label("Write queries:"), 0, 4);
        add(numTwoSpinner, 1, 4);
        add(new Label("Storage in GB:"), 0, 5);
        add(storageSpinner, 1, 5);
        add(new Label("per month"), 2, 5);
        add(new Label("Transferred data in GB:"), 0, 6);
        add(dataSpinner, 1, 6);
        add(new Label("per month"), 2, 6);
    }

    private void setDocumentControls() {
        add(new Label("Hours:"), 0, 3);
        add(durationSpinner, 1, 3);
        add(new Label("per month"), 2, 3);
        add(new Label("Requests:"), 0, 4);
        add(backupSpinner, 1, 4);
        add(new Label("per month"), 2, 4);
        add(new Label("Storage in GB:"), 0, 5);
        add(storageSpinner, 1, 5);
        add(new Label("per month"), 2, 5);
        add(new Label("Transferred data in GB:"), 0, 6);
        add(dataSpinner, 1, 6);
        add(new Label("per month"), 2, 6);
    }

    private void setCacheControls() {
        add(new Label("Instance type:"), 0, 3);
        add(instanceTypeBox, 1, 3);
        add(new Label("Instance size:"), 0, 4);
        add(instanceSizeBox, 1, 4);
        add(new Label("# of CPU:"), 0, 4);
        add(numOneSpinner, 1, 4);
        add(new Label("# of RAM in GB:"), 0, 5);
        add(numTwoSpinner, 1, 5);
        add(new Label("Hours:"), 0, 6);
        add(durationSpinner, 1, 6);
        add(new Label("per month"), 2, 6);
        add(new Label("Transferred data in GB:"), 0, 7);
        add(dataSpinner, 1, 7);
        add(new Label("per month"), 2, 7);
    }
}
