package cloud.view.dialogs;

import cloud.config.Config;;
import cloud.view.panes.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.Window;
import java.util.Optional;

public class DialogComponent extends Dialog {

    private PaneComponent paneComponent;
    private ComboBox componentsBox;

    public DialogComponent() {
        setTitle("Add Component to Design");
        setHeaderText("Select component category and define properties");
        setResizable(false);

        final Window window = getDialogPane().getScene().getWindow();
        Stage stage = (Stage) window;

        stage.setMinHeight(400);
        stage.setMinWidth(300);

        Label componentsLabel = new Label("Category: ");

        componentsBox = new ComboBox(FXCollections.observableArrayList(
                Config.getInstance().getConfigValues("component-categories")
        ));

        HBox boxPane = new HBox();
        boxPane.setSpacing(10);
        boxPane.setPadding(new Insets(10, 10, 10, 10));
        boxPane.getChildren().addAll(componentsLabel, componentsBox);
        boxPane.setAlignment(Pos.CENTER);

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
                    case "Compute":
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
