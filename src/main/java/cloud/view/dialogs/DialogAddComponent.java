package cloud.view.dialogs;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.util.Optional;

public class DialogAddComponent extends Dialog {

    private PaneComponent paneComponent;

    public DialogAddComponent() {
        setTitle("Add Component to Design");
        setHeaderText("Select component type and define properties");
        setResizable(true);

        final Window window = getDialogPane().getScene().getWindow();
        Stage stage = (Stage) window;

        stage.setMinHeight(400);
        stage.setMinWidth(300);

        Label componentsLabel = new Label("Component Type: ");
        String[] componentsArray = {"Virtual Machine", "Storage", "Database"};

        ChoiceBox<String> componentsBox = new ChoiceBox<>(FXCollections.observableArrayList(componentsArray));

        HBox boxPane = new HBox();
        boxPane.setSpacing(10);
        boxPane.setPadding(new Insets(10, 10, 10, 10));
        boxPane.getChildren().addAll(componentsLabel, componentsBox);

        BorderPane pane = new BorderPane();
        pane.setPadding(new Insets(10, 10, 10, 10));
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
                        paneComponent = new PaneComputeComponent();
                        break;
                    case "Database":
                        paneComponent = new PaneDatabaseComponent();
                        break;
                    case "Storage":
                        paneComponent = new PaneStorageComponent();
                        break;
                    default:
                        break;
                }
                pane.setCenter(paneComponent);
            }
        });

        Optional result = showAndWait();
        if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
            // TODO: Inhalte aus paneComponent zurückgeben
            System.out.println("pressed add button");
        }
    }
}
