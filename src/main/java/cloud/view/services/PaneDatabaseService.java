package cloud.view.services;

import cloud.configuration.Config;
import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class PaneDatabaseService extends PaneServiceProperties {

    private ComboBox<String> databaseSystemTypeBox;
    private ComboBox<String> databaseInstanceTypeBox;

    public PaneDatabaseService() {
        Label systemTypeLabel = new Label("System type:");
        databaseSystemTypeBox = new ComboBox<>(Config.getInstance().getConfigValues("database-system-type"));

        Label instanceTypeLabel = new Label("Instance type:");
        databaseInstanceTypeBox = new ComboBox<>(Config.getInstance().getConfigValues("database-instance-type"));

        add(systemTypeLabel, 0, 2);
        add(databaseSystemTypeBox, 1, 2);
        add(instanceTypeLabel, 0, 3);
        add(databaseInstanceTypeBox, 1, 3);
    }

    public String getDatabaseSystemTypeBox() {
        return databaseSystemTypeBox.getEditor().getText();
    }
    public void setDatabaseSystemTypeBox(String databaseSystemType) {
        this.databaseSystemTypeBox.getEditor().setText(databaseSystemType);
    }

    public String getDatabaseInstanceTypeBox() {
        return databaseInstanceTypeBox.getEditor().getText();
    }
    public void setDatabaseInstanceTypeBox(String databaseInstanceType) {
        this.databaseInstanceTypeBox.getEditor().setText(databaseInstanceType);
    }
}
