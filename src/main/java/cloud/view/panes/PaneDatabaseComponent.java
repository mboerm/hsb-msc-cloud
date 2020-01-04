package cloud.view.panes;

import static cloud.constants.Consts.*;
import javafx.collections.FXCollections;
import javafx.geometry.HPos;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

class PaneDatabaseComponent extends PaneComponent {

    PaneDatabaseComponent() {
        Label typeLabel = new Label("Database Type: ");
        ChoiceBox typeBox = new ChoiceBox<>(FXCollections.observableArrayList(DATABASE_TYPE.values()));

        add(typeLabel, 0, 1);
        add(typeBox, 1, 1);
        setHalignment(typeLabel, HPos.RIGHT);
        setHalignment(typeBox, HPos.LEFT);
    }
}
