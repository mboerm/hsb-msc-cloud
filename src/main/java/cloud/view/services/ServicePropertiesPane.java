package cloud.view.services;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

public abstract class ServicePropertiesPane extends GridPane {

    private Label servicePropertiesLbl;
    private Label nameLbl;
    private TextField nameTFld;

    public ServicePropertiesPane() {
        setPadding(new Insets(10, 10, 10, 10));
        setHgap(10);
        setVgap(10);
        setAlignment(Pos.TOP_CENTER);

        servicePropertiesLbl = new Label("Service properties");
        servicePropertiesLbl.setTextAlignment(TextAlignment.CENTER);
        servicePropertiesLbl.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        nameLbl = new Label("Name:");
        nameTFld = new TextField();

        setControls();
    }

    public String getName() {
        return this.nameTFld.getText();
    }
    public void setName(String name) {this.nameTFld.setText(name);}

    public void setControls() {
        add(servicePropertiesLbl, 0,0);
        setColumnSpan(servicePropertiesLbl, 2);
        add(nameLbl, 0, 1);
        add(nameTFld, 1, 1);
    }

    abstract void recoverControls();
}
