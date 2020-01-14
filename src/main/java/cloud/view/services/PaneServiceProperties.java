package cloud.view.services;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class PaneServiceProperties extends GridPane {

    private TextField nameTFld;

    public PaneServiceProperties() {
        setPadding(new Insets(10, 10, 10, 10));
        setHgap(10);
        setVgap(10);
        setAlignment(Pos.TOP_CENTER);

        Label nameLbl = new Label("Name:");
        nameTFld = new TextField();

        add(nameLbl, 0, 0);
        add(nameTFld, 1, 0);
    }

    public String getName() {
        return this.nameTFld.getText();
    }
    public void setName(String name) {
        this.nameTFld.setText(name);
    }
}
