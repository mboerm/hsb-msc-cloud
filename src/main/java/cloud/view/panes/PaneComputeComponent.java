package cloud.view.panes;

import cloud.view.panes.PaneComponent;
import javafx.collections.FXCollections;
import javafx.geometry.HPos;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import static cloud.constants.Consts.*;

public class PaneComputeComponent extends PaneComponent {

    public PaneComputeComponent() {
        Label typeLabel = new Label("Instance Type: ");
        ChoiceBox typeBox = new ChoiceBox<>(FXCollections.observableArrayList(COMPONENT_USAGE_TYPE.values()));

        add(typeLabel, 0, 1);
        add(typeBox, 1, 1);
        setHalignment(typeLabel, HPos.RIGHT);
        setHalignment(typeBox, HPos.LEFT);
    }
}
