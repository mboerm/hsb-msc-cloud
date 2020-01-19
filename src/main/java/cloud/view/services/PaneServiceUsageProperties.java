package cloud.view.services;

import cloud.configuration.Config;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

public class PaneServiceUsageProperties extends GridPane {

    private ComboBox<String> regionBox;
    private ComboBox<String> usageTypeBox;
    private ComboBox<String> usagePeriodBox;
    private ComboBox<String> usagePrepayBox;
    private ComboBox<String> opModeBox;

    public PaneServiceUsageProperties() {
        setPadding(new Insets(10, 10, 10, 10));
        setHgap(10);
        setVgap(10);
        setAlignment(Pos.TOP_CENTER);

        Label serviceUsageLbl = new Label("Usage properties");
        serviceUsageLbl.setTextAlignment(TextAlignment.CENTER);
        serviceUsageLbl.setFont(Font.font("Arial", FontWeight.BOLD, 12));

        Label regionLbl = new Label("Region: ");
        regionBox = new ComboBox<>(Config.getInstance().getConfigValues("service-regions"));

        Label usageTypeLbl = new Label("Usage type: ");
        usageTypeBox = new ComboBox<>(Config.getInstance().getConfigValues("service-usage-type"));

        Label usagePeriodLbl = new Label("Usage period: ");
        usagePeriodBox = new ComboBox<>();
        usagePeriodBox.setDisable(true);

        Label usagePrepayLbl = new Label("Usage prepay: ");
        usagePrepayBox = new ComboBox<>(Config.getInstance().getConfigValues("service-usage-prepay"));
        usagePrepayBox.setDisable(true);

        Label opModeLbl = new Label("Operating mode: ");
        opModeBox = new ComboBox<>(Config.getInstance().getConfigValues("service-operating-mode"));

        add(serviceUsageLbl, 0,0);
        setColumnSpan(serviceUsageLbl, 2);
        add(regionLbl, 0, 1);
        add(regionBox, 1, 1);
        add(usageTypeLbl, 0, 2);
        add(usageTypeBox, 1, 2);
        add(usagePeriodLbl, 0, 3);
        add(usagePeriodBox, 1, 3);
        add(usagePrepayLbl, 0, 4);
        add(usagePrepayBox, 1, 4);
        add(opModeLbl, 0, 5);
        add(opModeBox, 1, 5);

        usageTypeBox.getSelectionModel().selectedItemProperty().addListener((ov, oldItem, newItem) -> {
            usagePeriodBox.setDisable(false);
            if (newItem.equals(Config.getInstance().getConfigValuesAsArray("service-usage-type")[1])) {
                this.usagePeriodBox.setItems(FXCollections.observableArrayList(
                        Config.getInstance().getConfigValues("service-usage-period").subList(2,4)));
                this.usagePrepayBox.setDisable(false);
            } else {
                this.usagePeriodBox.setItems(FXCollections.observableArrayList(
                        Config.getInstance().getConfigValues("service-usage-period").subList(0,2)));
                this.usagePrepayBox.setDisable(true);
            }
        });
    }

    public String getRegionText() {
        return regionBox.getEditor().getText();
    }
    public String getUsageTypeText() {
        return usageTypeBox.getEditor().getText();
    }
    public String getUsagePeriodText() {
        return usagePeriodBox.getEditor().getText();
    }
    public String getUsagePrepayText() {
        return usagePrepayBox.getEditor().getText();
    }
    public String getOpModeText() {
        return opModeBox.getEditor().getText();
    }
}
