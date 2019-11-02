package cloud.ui;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

class DialogAddComponent extends Dialog {

    private final String[] arrayData = {
            "Virtual Machine",
            "Storage",
            "Database",
            "Network",
            "Management",
            "Integration",
            "Analytics"
            };

    DialogAddComponent() {
        setTitle("Add Component to Design");
        setHeaderText("Select component to add to design");

        ComboBox choiceBox = new ComboBox(FXCollections.observableArrayList(arrayData));

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(5, 5, 5, 5));
        grid.setAlignment(Pos.CENTER);
        grid.add(choiceBox, 0, 0);
        getDialogPane().setContent(grid);

        SingleSelectionModel model = choiceBox.getSelectionModel();

        getDialogPane().getButtonTypes().addAll(ButtonType.CANCEL, ButtonType.OK);
        Button okButton = (Button)getDialogPane().lookupButton(ButtonType.OK);
        okButton.setText("Add");

        setResultConverter(dialogButton -> {
            if (dialogButton == ButtonType.OK) {
                return model.getSelectedItem();
            }
            return "cancelled.";
        });
    }
}
