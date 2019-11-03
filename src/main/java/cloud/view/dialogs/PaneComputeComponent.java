package cloud.view.dialogs;

import javafx.collections.FXCollections;
import javafx.geometry.HPos;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import static cloud.constants.Consts.*;

class PaneComputeComponent extends PaneComponent {

    PaneComputeComponent() {
        Label typeLabel = new Label("Instance Type: ");
        ChoiceBox typeBox = new ChoiceBox<>(FXCollections.observableArrayList(INSTANCE_TYPES.values()));

        add(typeLabel, 0, 1);
        add(typeBox, 1, 1);
        setHalignment(typeLabel, HPos.RIGHT);
        setHalignment(typeBox, HPos.LEFT);
    }
}
