package cloud.view.components;

import cloud.configuration.Config;
import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class PaneComputeComponent extends PaneComponent {

    private ComboBox computeTypeBox;

    public PaneComputeComponent() {
        Label typeLabel = new Label("Type:");
        computeTypeBox = new ComboBox(FXCollections.observableArrayList(
                Config.getInstance().getConfigValues("compute-type")
        ));

        add(typeLabel, 0, 1);
        add(computeTypeBox, 1, 1);
    }

    public String getComputeType() {return this.computeTypeBox.getEditor().getText();}
}
