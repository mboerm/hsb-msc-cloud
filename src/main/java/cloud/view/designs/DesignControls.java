package cloud.view.designs;

import cloud.view.dialogs.DialogAddComponent;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class DesignControls extends VBox {

    public DesignControls() {
        Button controlAdd = new Button("Add");
        Button controlRemove = new Button("Remove");
        Button controlEdit = new Button("Edit");

        setSpacing(10);
        setMargin(controlAdd, new Insets(5, 5, 5, 5));
        setMargin(controlRemove, new Insets(5, 5, 5, 5));
        setMargin(controlEdit, new Insets(5, 5, 5, 5));
        getChildren().addAll(controlAdd, controlRemove, controlEdit);

        controlAdd.setOnAction(actionEvent -> {
            DialogAddComponent dialogAdd = new DialogAddComponent();
        });

        controlRemove.setOnAction(actionEvent -> {

        });

        controlEdit.setOnAction(actionEvent -> {

        });
    }
}
