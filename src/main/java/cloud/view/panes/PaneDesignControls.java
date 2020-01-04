package cloud.view.panes;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class PaneDesignControls extends VBox {
    private Button controlAdd;
    private Button controlRemove;
    private Button controlEdit;

    public PaneDesignControls() {
        controlAdd = new Button("+");
        controlRemove = new Button("-");
        controlEdit = new Button("Edit");

        setSpacing(10);
        setMargin(controlAdd, new Insets(5, 5, 5, 5));
        setMargin(controlRemove, new Insets(5, 5, 5, 5));
        setMargin(controlEdit, new Insets(5, 5, 5, 5));
        getChildren().addAll(controlAdd, controlRemove, controlEdit);
    }


}