package cloud.view.panes;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class PaneComponent extends GridPane {

    private TextField nameField;

    public PaneComponent() {
        setHgap(10);
        setVgap(10);
        setPadding(new Insets(10, 10, 10, 10));

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
