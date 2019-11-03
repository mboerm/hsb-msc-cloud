package cloud.ui.dialogs;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class PaneComponent extends GridPane {

    private TextField nameField;

    PaneComponent() {
        setAlignment(Pos.CENTER);

        Label nameLabel = new Label("Name: ");
        nameField = new TextField();

        add(nameLabel, 0, 0);
        add(nameField, 1, 0);
    }

    public String getName() {
        return this.nameField.getText();
    }
    public void setName(String name) {
        this.nameField.setText(name);
    }
}
