package cloud.ui.dialogs;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import java.util.Optional;

public class DialogAddComponent extends Dialog {

    private String component;

    public DialogAddComponent() {
        setTitle("Add Component to Design");
        setHeaderText("Select component type and define properties");

        Label componentsLabel = new Label("Component Type: ");
        String[] componentsArray = {"Virtual Machine", "Storage", "Database"};

        ChoiceBox<String> componentsBox = new ChoiceBox<>(FXCollections.observableArrayList(componentsArray));

        HBox boxPane = new HBox();
        boxPane.setPadding(new Insets(5, 5, 5, 5));
//        boxPane.setAlignment(Pos.CENTER);
        boxPane.getChildren().addAll(componentsLabel, componentsBox);

        BorderPane pane = new BorderPane();
        pane.setPadding(new Insets(5, 5, 5, 5));
        pane.setTop(boxPane);
        pane.setCenter(new PaneComponent());
        getDialogPane().setContent(pane);

        getDialogPane().getButtonTypes().addAll(ButtonType.CANCEL, ButtonType.OK);
        Button okButton = (Button)getDialogPane().lookupButton(ButtonType.OK);
        okButton.setText("Add");
        okButton.setDisable(true);

        componentsBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldItem, String newItem) {
                okButton.setDisable(false);
                switch (newItem) {
                    case "Virtual Machine":
                        pane.setCenter(new PaneComputeComponent());
                        break;
                    case "Database":
                        pane.setCenter(new PaneDatabaseComponent());
                        break;
                    case "Storage":
                        pane.setCenter(new PaneStorageComponent());
                        break;
                    default:
                        break;
                }
            }
        });

        Optional result = showAndWait();

        if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
            setComponent("done");
        } else {
            setComponent("cancelled!");
        }
    }

    private void setComponent(String component) {
        this.component = component;
    }
    public String getComponent() {
        return this.component;
    }
}
