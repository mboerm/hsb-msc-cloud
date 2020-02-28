package cloud.view.dialogs;

import cloud.configuration.Config;
import cloud.configuration.Constants;
import cloud.view.services.ServiceUsagePropertiesPane;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ServiceDialog extends Dialog<String> {

    private ServiceUsagePropertiesPane serviceUsagePropertiesPane;
    private ComboBox<String> serviceTypeBox;
    private BorderPane serviceDialogPane;

    private Separator sepVert;
    private Button buttonOk;

    public ServiceDialog() {
        setTitle(Config.getInstance().getConfigValue("dialog-service-title"));
        setHeaderText(Config.getInstance().getConfigValue("dialog-service-header"));
        setResizable(false);

        Stage stage = (Stage) getDialogPane().getScene().getWindow();
        stage.setMinHeight(Constants.DIALOG_MIN_HEIGHT);
        stage.setMinWidth(Constants.DIALOG_MIN_WIDTH);

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
        serviceDialogPane.setLeft(serviceUsagePropertiesPane = new ServiceUsagePropertiesPane());

        sepVert = new Separator();
        sepVert.setOrientation(Orientation.VERTICAL);
        sepVert.setValignment(VPos.CENTER);
        sepVert.setPrefHeight(100);

        getDialogPane().setContent(serviceDialogPane);

        getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        buttonOk = (Button)getDialogPane().lookupButton(ButtonType.OK);
        disableOKButton();

        setResultConverter(dialogButton -> {
            if (dialogButton == ButtonType.OK) {
                return getServiceTypeBoxItem();
            } else {
                return null;
            }
        });
    }

    public BorderPane getServiceDialogPane() {return this.serviceDialogPane;}
    public ServiceUsagePropertiesPane getServiceUsagePropertiesPane() {return this.serviceUsagePropertiesPane;}
    public ComboBox<String> getServiceTypeBox() {return this.serviceTypeBox;}

    public void setSeparator() {
        serviceDialogPane.setCenter(sepVert);
    }

    public void disableOKButton() {
        this.buttonOk.setDisable(true);
    }
    public void enableOKButton() {this.buttonOk.setDisable(false);}

    public String getServiceTypeBoxItem() {
        return this.serviceTypeBox.getSelectionModel().selectedItemProperty().getValue();
    }
    public void selectServiceType(String item) {
        this.serviceTypeBox.getSelectionModel().select(item);
    }
}
