package cloud.view.dialogs;

import cloud.configuration.Config;;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.Optional;

public class DialogAddComponent<String> extends Dialog {

    private HBox componentsBoxPane;
    private BorderPane componentsDialogPane;
    private ComboBox<String> componentsBox;

    private Button buttonOk;

    public DialogAddComponent() {
        setTitle("Add Component to Design");
        setHeaderText("Select component category and define properties");
        setResizable(false);

        Stage stage = (Stage) getDialogPane().getScene().getWindow();
        stage.setMinHeight(400);
        stage.setMinWidth(300);

        Label componentsLabel = new Label("Category: ");
        String[] componentTypes = (String[]) Config.getInstance().getConfigValues("component-categories");
        componentsBox = new ComboBox<>(FXCollections.observableArrayList(componentTypes));

        componentsBoxPane = new HBox();
        componentsBoxPane.setSpacing(10);
        componentsBoxPane.setPadding(new Insets(10, 10, 10, 10));
        componentsBoxPane.getChildren().addAll(componentsLabel, componentsBox);
        componentsBoxPane.setAlignment(Pos.CENTER);

        componentsDialogPane = new BorderPane();
        componentsDialogPane.setPadding(new Insets(10, 10, 10, 10));
        componentsDialogPane.setTop(componentsBoxPane);
        getDialogPane().setContent(componentsDialogPane);

        getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        buttonOk = (Button)getDialogPane().lookupButton(ButtonType.OK);
        buttonOk.setDisable(true);

        setResultConverter(dialogButton -> {
            if (dialogButton == ButtonType.OK) {
                return getComboBoxItem();
            }
            return null;
        });
    }

    public BorderPane getComponentsDialogPane() {return this.componentsDialogPane;}
    public ComboBox<String> getComponentsBox() {return this.componentsBox;}

    public void toggleOKButton(boolean toggle) {
        this.buttonOk.setDisable(toggle);
    }

    private String getComboBoxItem() {
        return (String) this.componentsBox.getSelectionModel().selectedItemProperty().toString();
    }
}
