package cloud.ui.dialogs;

import javafx.scene.control.Label;

class PaneDatabaseComponent extends PaneComponent {

    PaneDatabaseComponent() {
        Label labelType = new Label("Database Type: ");

        add(labelType, 0, 1);
    }
}
