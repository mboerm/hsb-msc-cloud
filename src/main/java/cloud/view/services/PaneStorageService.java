package cloud.view.services;

import javafx.scene.control.Label;

public class PaneStorageService extends PaneServiceProperties {

    public PaneStorageService() {
        Label labelType = new Label("Storage type:");

        add(labelType, 0, 1);
    }
}
