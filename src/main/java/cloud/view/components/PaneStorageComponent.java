package cloud.view.components;

import javafx.scene.control.Label;

public class PaneStorageComponent extends PaneComponent {

    public PaneStorageComponent() {
        Label labelType = new Label("Storage type:");

        add(labelType, 0, 1);
    }
}
