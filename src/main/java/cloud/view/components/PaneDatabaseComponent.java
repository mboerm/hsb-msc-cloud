package cloud.view.components;

import cloud.configuration.Config;
import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class PaneDatabaseComponent extends PaneComponent {

    private ComboBox<String> databaseSystemTypeBox;
    private ComboBox<String> databaseInstanceTypeBox;

    public PaneDatabaseComponent() {
        Label systemTypeLabel = new Label("System type:");
        databaseSystemTypeBox = new ComboBox<>(FXCollections.observableArrayList(
                Config.getInstance().getConfigValues("database-system-type")
        ));

        Label instanceTypeLabel = new Label("Instance type:");
        databaseInstanceTypeBox = new ComboBox<>(FXCollections.observableArrayList(
                Config.getInstance().getConfigValues("database-instance-type")
        ));

        add(systemTypeLabel, 0, 1);
        add(databaseSystemTypeBox, 1, 1);
        add(instanceTypeLabel, 0, 2);
        add(databaseInstanceTypeBox, 1, 2);
    }

    public ComboBox<String> getDatabaseSystemTypeBox() {
        return databaseSystemTypeBox;
    }
    public void setDatabaseSystemTypeBox(ComboBox<String> databaseSystemTypeBox) {
        this.databaseSystemTypeBox = databaseSystemTypeBox;
    }

    public ComboBox<String> getDatabaseInstanceTypeBox() {
        return databaseInstanceTypeBox;
    }
    public void setDatabaseInstanceTypeBox(ComboBox<String> databaseInstanceTypeBox) {
        this.databaseInstanceTypeBox = databaseInstanceTypeBox;
    }
}
