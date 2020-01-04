package cloud.view.panes;

import cloud.view.panes.PaneComponent;
import javafx.scene.control.Label;

public class PaneStorageComponent extends PaneComponent {

    public PaneStorageComponent() {
        Label labelType = new Label("Storage Type: ");

        add(labelType, 0, 1);
    }
}
