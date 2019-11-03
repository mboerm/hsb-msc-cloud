package cloud.ui;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

class DesignProperties extends GridPane {

    DesignProperties() {
        setPadding(new Insets(5, 5, 5, 5));

        Label testLabel = new Label("Test: ");
        TextField testField = new TextField();
        add(testLabel, 0, 0);
        add(testField, 1, 0);
    }
}
