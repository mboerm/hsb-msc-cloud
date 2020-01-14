package cloud.view.dialogs;

import cloud.configuration.Config;
import cloud.view.services.PaneServiceUsageProperties;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class DialogAddService<String> extends Dialog<String> {

    private BorderPane serviceDialogPane;
    private ComboBox<String> serviceTypeBox;

    private GridPane designPropertiesPane;

    private Button buttonOk;

    public DialogAddService() {
        setTitle("Add service to Design");
        setHeaderText("Select service category and define properties");
        setResizable(false);

        Stage stage = (Stage) getDialogPane().getScene().getWindow();
        stage.setMinHeight(400);
        stage.setMinWidth(300);

        Label serviceTypeLbl = new Label("Category: ");
        String[] serviceCategories = (String[]) Config.getInstance().getConfigValues("component-categories");
        serviceTypeBox = new ComboBox<>(FXCollections.observableArrayList(serviceCategories));

        HBox serviceTypBoxPane = new HBox();
        serviceTypBoxPane.setSpacing(10);
        serviceTypBoxPane.setPadding(new Insets(10, 10, 10, 10));
        serviceTypBoxPane.getChildren().addAll(serviceTypeLbl, serviceTypeBox);
        serviceTypBoxPane.setAlignment(Pos.CENTER);

        serviceDialogPane = new BorderPane();
        serviceDialogPane.setPadding(new Insets(10, 10, 10, 10));
        serviceDialogPane.setTop(serviceTypBoxPane);
        serviceDialogPane.setLeft(designPropertiesPane = new PaneServiceUsageProperties());
        getDialogPane().setContent(serviceDialogPane);

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

    public GridPane getDesignPropertiesPane() {return this.designPropertiesPane;}
    public BorderPane getServiceDialogPane() {return this.serviceDialogPane;}
    public ComboBox<String> getServiceTypeBox() {return this.serviceTypeBox;}

    public void toggleOKButton(boolean toggle) {
        this.buttonOk.setDisable(toggle);
    }

    private String getComboBoxItem() {
        return (String) this.serviceTypeBox.getSelectionModel().selectedItemProperty().toString();
    }
}
