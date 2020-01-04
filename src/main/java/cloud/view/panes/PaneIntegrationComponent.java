package cloud.view.panes;

import cloud.view.panes.PaneComponent;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

public class PaneIntegrationComponent extends PaneComponent {

    PaneIntegrationComponent() {
        RadioButton radioButton1 = new RadioButton("Left");
        RadioButton radioButton2 = new RadioButton("Right");
        RadioButton radioButton3 = new RadioButton("Up");
        RadioButton radioButton4 = new RadioButton("Down");

        ToggleGroup radioGroup = new ToggleGroup();

        radioButton1.setToggleGroup(radioGroup);
        radioButton2.setToggleGroup(radioGroup);
        radioButton3.setToggleGroup(radioGroup);
        radioButton4.setToggleGroup(radioGroup);
    }
}
