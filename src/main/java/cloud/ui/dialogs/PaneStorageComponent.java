package cloud.ui.dialogs;

import javafx.scene.control.Label;

class PaneStorageComponent extends PaneComponent {

    PaneStorageComponent() {
        Label labelType = new Label("Storage Type: ");

        add(labelType, 0, 1);
    }
}
