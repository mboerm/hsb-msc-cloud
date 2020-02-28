package cloud.view.design;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

/**
 * Panel of design controls
 */
public class DesignControls extends VBox {

    private Button controlAdd;
    private Button controlRemove;

    /**
     * Constructor
     */
    public DesignControls() {
        initControls();
        setSpacing(10);
        setPadding(new Insets(10,10, 10, 10));
        getChildren().addAll(controlAdd, controlRemove);
    }

    /**
     * Init design controls
     */
    private void initControls() {
        controlAdd = new Button("Add");
        controlRemove = new Button("Remove");

        controlAdd.setMaxWidth(Double.MAX_VALUE);
        controlRemove.setMaxWidth(Double.MAX_VALUE);
    }

    public Button getControlAdd() {
        return this.controlAdd;
    }

    public Button getControlRemove() {
        return this.controlRemove;
    }
}
