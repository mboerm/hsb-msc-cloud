package cloud.view.panes;

import cloud.config.Config;
import javafx.collections.FXCollections;
import javafx.geometry.HPos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import static cloud.constants.Constants.*;

public class PaneComputeComponent extends PaneComponent {

    private ComboBox typeBox;

    public PaneComputeComponent() {
        Label typeLabel = new Label("Instance Type: ");
        typeBox = new ComboBox(FXCollections.observableArrayList(
                Config.getInstance().getConfigValues("compute-instance-type")
        ));

        add(typeLabel, 0, 1);
        add(typeBox, 1, 1);
    }
}
