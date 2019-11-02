package cloud.ui;

import cloud.components.Database;
import cloud.components.Storage;
import cloud.components.VirtualMachine;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.layout.VBox;

import java.util.Arrays;
import java.util.List;

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

        controlAdd.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                DialogAddComponent dialogAdd = new DialogAddComponent();
                dialogAdd.showAndWait();

                String result = dialogAdd.getResult().toString();
                switch (result) {
                    case "Virtual Machine":
                        new VirtualMachine();
                        break;

                    case "Storage":
                        new Storage();
                        break;

                    case "Database":
                        new Database();
                        break;
                }
            }
        });

        controlRemove.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

            }
        });

        controlEdit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

            }
        });
    }
}
