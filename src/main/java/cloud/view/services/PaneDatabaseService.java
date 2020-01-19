package cloud.view.services;

import cloud.configuration.Config;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.util.Pair;

public class PaneDatabaseService extends PaneServiceProperties {

    private Label databaseTypeLbl;
    private Label databaseSchemeLbl;
    private Label instanceTypeLbl;
    private Label instanceSizeLbl;

    private Label storageLbl;
    private Label backupLbl;
    private Label dataLbl;

    private ComboBox<String> databaseTypeBox;
    private ComboBox<String> databaseSchemeBox;
    private ComboBox<String> instanceTypeBox;
    private ComboBox<String> instanceSizeBox;
    private Spinner<String> durationSpinner;
    private Spinner<String> storageSpinner;
    private Spinner<String> backupSpinner;
    private Spinner<String> dataSpinner;
    private Spinner<String> queriesReadSpinner;
    private Spinner<String> queriesWriteSpinner;

    public PaneDatabaseService() {
        databaseTypeLbl = new Label("Database type:");
        databaseTypeBox = new ComboBox<>(Config.getInstance().getConfigValues("database-system-type"));
        databaseSchemeLbl = new Label("Database scheme:");
        databaseSchemeBox = new ComboBox<>(Config.getInstance().getConfigValues("database-sql-scheme"));
        instanceTypeLbl = new Label("Instance type:");
        instanceTypeBox = new ComboBox<>(Config.getInstance().getConfigValues("database-instance-type"));
        instanceSizeLbl = new Label("Instance size:");
        instanceSizeBox = new ComboBox<>(Config.getInstance().getConfigValues("service-instance-size"));

        storageLbl = new Label("Storage in GB:");
        backupLbl = new Label("Backup storage in GB:");
        dataLbl = new Label("Transferred data in GB:");

        durationSpinner = new Spinner<>(1, 1000000, 1);
        storageSpinner = new Spinner<>(1, 1000000, 1);
        backupSpinner = new Spinner<>(1, 1000000, 1);
        dataSpinner = new Spinner<>(1, 1000000, 1);
        queriesReadSpinner = new Spinner<>(1, 1000000, 1);
        queriesWriteSpinner = new Spinner<>(1, 1000000, 1);

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
    public String getDatabaseScheme() {return this.databaseSchemeBox.getValue();}
    public String getInstanceType() {return this.instanceTypeBox.getValue();}
    public String getInstanceSize() {return this.instanceSizeBox.getValue();}
    public String getDuration() {return this.durationSpinner.getEditor().getText();}
    public String getStorage() {return this.storageSpinner.getEditor().getText();}
    public String getBackup() {return this.backupSpinner.getEditor().getText();}
    public String getData() {return this.dataSpinner.getEditor().getText();}
    public Pair<String,String> getQueries() {return new Pair<>(
            this.queriesReadSpinner.getEditor().getText(),
            this.queriesWriteSpinner.getEditor().getText());
    }

    private void recoverControls() {
        getChildren().clear();
        super.setControls();
        add(databaseTypeLbl, 0, 2);
        add(databaseTypeBox, 1, 2);
    }

    private void setSQLControls() {
        add(databaseSchemeLbl, 0, 3);
        add(databaseSchemeBox, 1, 3);
        add(instanceTypeLbl, 0, 4);
        add(instanceTypeBox, 1, 4);
        add(instanceSizeLbl, 0, 5);
        add(instanceSizeBox, 1, 5);
        add(new Label("Instance hours:"), 0, 6);
        add(durationSpinner, 1, 6);
        add(new Label("per month"), 2, 6);
        add(storageLbl, 0, 7);
        add(storageSpinner, 1, 7);
        add(new Label("per month"), 2, 7);
        add(backupLbl, 0, 8);
        add(backupSpinner, 1, 8);
        add(new Label("per month"), 2, 8);
        add(dataLbl, 0, 9);
        add(dataSpinner, 1, 9);
        add(new Label("per month"), 2, 9);
    }

    private void setNoSQLControls() {
        add(new Label("Read queries:"), 0, 3);
        add(queriesReadSpinner, 1, 3);
        add(new Label("Write queries:"), 0, 4);
        add(queriesWriteSpinner, 1, 4);
        add(storageLbl, 0, 5);
        add(storageSpinner, 1, 5);
        add(new Label("per month"), 2, 5);
        add(dataLbl, 0, 6);
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
        add(storageLbl, 0, 5);
        add(storageSpinner, 1, 5);
        add(new Label("per month"), 2, 5);
        add(dataLbl, 0, 6);
        add(dataSpinner, 1, 6);
        add(new Label("per month"), 2, 6);
    }

    private void setCacheControls() {
        add(instanceTypeLbl, 0, 3);
        add(instanceTypeBox, 1, 3);
        add(instanceSizeLbl, 0, 4);
        add(instanceSizeBox, 1, 4);
        add(new Label("Hours:"), 0, 5);
        add(durationSpinner, 1, 5);
        add(new Label("per month"), 2, 5);
        add(dataLbl, 0, 6);
        add(dataSpinner, 1, 6);
        add(new Label("per month"), 2, 6);
    }
}
