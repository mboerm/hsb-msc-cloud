package cloud.view.panes;

import cloud.config.Config;
import javafx.collections.FXCollections;
import javafx.geometry.HPos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class PaneDatabaseComponent extends PaneComponent {

    private ComboBox databaseSystemTypeBox;
    private ComboBox databaseInstanceTypeBox;

    public PaneDatabaseComponent() {
        Label systemTypeLabel = new Label("System type: ");
        databaseSystemTypeBox = new ComboBox(FXCollections.observableArrayList(
                Config.getInstance().getConfigValues("database-system-type")
        ));

        Label instanceTypeLabel = new Label("Instance type: ");
        databaseInstanceTypeBox = new ComboBox(FXCollections.observableArrayList(
                Config.getInstance().getConfigValues("database-instance-type")
        ));

        add(systemTypeLabel, 0, 1);
        add(databaseSystemTypeBox, 1, 1);
        add(instanceTypeLabel, 0, 2);
        add(databaseInstanceTypeBox, 1, 2);
    }
}
