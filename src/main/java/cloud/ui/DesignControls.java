package cloud.ui;

import cloud.components.Database;
import cloud.components.Storage;
import cloud.components.VirtualMachine;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

class DesignControls extends VBox {

    DesignControls() {
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
            switch (dialogAdd.getItem()) {
                case "Virtual Machine":
                    new VirtualMachine();
                    break;
                case "Storage":
                    new Storage();
                    break;
                case "Database":
                    new Database();
                    break;
                default:
                    break;
            }
        });

        controlRemove.setOnAction(actionEvent -> {

        });

        controlEdit.setOnAction(actionEvent -> {

        });
    }
}
