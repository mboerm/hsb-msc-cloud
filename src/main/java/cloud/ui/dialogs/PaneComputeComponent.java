package cloud.ui.dialogs;

import javafx.scene.control.Label;

class PaneComputeComponent extends PaneComponent {

    PaneComputeComponent() {
        Label labelType = new Label("Instance Type: ");

        add(labelType, 0, 1);
    }
}
