package cloud.view.components;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class PaneComponent extends GridPane {

    private String identifier;
    private TextField nameTFld;

    public PaneComponent() {
        setPadding(new Insets(10, 10, 10, 10));
        setHgap(10);
        setVgap(10);
        setAlignment(Pos.TOP_CENTER);

        Label nameLbl = new Label("Name:");
        nameTFld = new TextField();

        add(nameLbl, 0, 0);
        add(nameTFld, 1, 0);
    }

    public void setIdentifier(String identifier) {this.identifier = identifier;}
    public String getIdentifier() {return this.identifier;}

    public String getName() {
        return this.nameTFld.getText();
    }
    public void setName(String name) {
        this.nameTFld.setText(name);
    }
}
