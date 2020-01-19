package cloud.view.dialogs;

import cloud.configuration.Config;
import cloud.view.services.PaneServiceUsageProperties;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class DialogAddService extends Dialog {

    private PaneServiceUsageProperties serviceUsagePropertiesPane;
    private ComboBox<String> serviceTypeBox;
    private BorderPane serviceDialogPane;

    private Button buttonOk;

    public DialogAddService() {
        setTitle("Add service to Design");
        setHeaderText("Select service category and define properties");
        setResizable(false);

        Stage stage = (Stage) getDialogPane().getScene().getWindow();
        stage.setMinHeight(400);
        stage.setMinWidth(300);

        Label serviceTypeLbl = new Label("Category: ");
        serviceTypeBox = new ComboBox<>(Config.getInstance().getConfigValues("service-categories"));

        HBox serviceTypeBoxPane = new HBox();
        serviceTypeBoxPane.setSpacing(10);
        serviceTypeBoxPane.setPadding(new Insets(10, 10, 10, 10));
        serviceTypeBoxPane.getChildren().addAll(serviceTypeLbl, serviceTypeBox);
        serviceTypeBoxPane.setAlignment(Pos.CENTER);

        serviceDialogPane = new BorderPane();
        serviceDialogPane.setPadding(new Insets(10, 10, 10, 10));
        serviceDialogPane.setTop(serviceTypeBoxPane);
        serviceDialogPane.setLeft(serviceUsagePropertiesPane = new PaneServiceUsageProperties());

        final Separator sepVert = new Separator();
        sepVert.setOrientation(Orientation.VERTICAL);
        sepVert.setValignment(VPos.CENTER);
        sepVert.setPrefHeight(100);
        serviceDialogPane.setCenter(sepVert);

        getDialogPane().setContent(serviceDialogPane);

        getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        buttonOk = (Button)getDialogPane().lookupButton(ButtonType.OK);
        disableOKButton();

        setResultConverter(dialogButton -> {
            if (dialogButton == ButtonType.OK) {
                return getServiceTypeBoxItem();
            }
            return null;
        });
    }

    public BorderPane getServiceDialogPane() {return this.serviceDialogPane;}
    public PaneServiceUsageProperties getServiceUsagePropertiesPane() {return this.serviceUsagePropertiesPane;}
    public ComboBox<String> getServiceTypeBox() {return this.serviceTypeBox;}

    public void disableOKButton() {
        this.buttonOk.setDisable(true);
    }
    public void enableOKButton() {this.buttonOk.setDisable(false);}

    private String getServiceTypeBoxItem() {
        return this.serviceTypeBox.getSelectionModel().selectedItemProperty().toString();
    }
}
