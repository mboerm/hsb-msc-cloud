package cloud.view.panes;

import cloud.view.panes.PaneComponent;
import javafx.scene.control.Label;

class PaneStorageComponent extends PaneComponent {

    PaneStorageComponent() {
        Label labelType = new Label("Storage Type: ");

        add(labelType, 0, 1);
    }
}
