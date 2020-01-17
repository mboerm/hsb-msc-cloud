package cloud.view.services;

import cloud.configuration.Config;
import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class PaneComputeService extends PaneServiceProperties {

    private ComboBox<String> computeTypeBox;

    public PaneComputeService() {
        Label typeLabel = new Label("Type:");
        computeTypeBox = new ComboBox<>(Config.getInstance().getConfigValues("compute-type"));

        add(typeLabel, 0, 2);
        add(computeTypeBox, 1, 2);
    }

    public String getComputeType() {return this.computeTypeBox.getEditor().getText();}
}
