package cloud.ui;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.util.Optional;

class DialogAddComponent extends Dialog {

    private String item;

    DialogAddComponent() {
        setTitle("Add Component to Design");
        setHeaderText("Select component to add to design");

        String[] arrayData = {
                "Virtual Machine",
                "Storage",
                "Database"
        };

        ComboBox<String> choiceBox = new ComboBox<>(FXCollections.observableArrayList(arrayData));

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(5, 5, 5, 5));
        grid.setAlignment(Pos.CENTER);
        grid.add(choiceBox, 0, 0);
        getDialogPane().setContent(grid);

        SingleSelectionModel<String> model = choiceBox.getSelectionModel();

        getDialogPane().getButtonTypes().addAll(ButtonType.CANCEL, ButtonType.OK);
        Button okButton = (Button)getDialogPane().lookupButton(ButtonType.OK);
        okButton.setText("Add");

        Optional result = showAndWait();

        if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
            setItem(model.getSelectedItem());
        } else {
            setItem("cancelled!");
        }
    }

    private void setItem(String item) {
        this.item = item;
    }
    String getItem() {
        return this.item;
    }
}
