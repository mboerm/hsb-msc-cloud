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

public class ServiceUsagePropertiesPane extends GridPane {

    private Label serviceUsageLbl;
    private ComboBox<String> regionBox;
    private ComboBox<String> usageTypeBox;
    private ComboBox<String> usagePeriodBox;
    private ComboBox<String> usagePrepayBox;
    private ComboBox<String> opModeBox;

    public ServiceUsagePropertiesPane() {
        setPadding(new Insets(10, 10, 10, 10));
        setHgap(10);
        setVgap(10);
        setAlignment(Pos.TOP_CENTER);

        serviceUsageLbl = new Label("Usage properties");
        serviceUsageLbl.setTextAlignment(TextAlignment.CENTER);
        serviceUsageLbl.setFont(Font.font("Arial", FontWeight.BOLD, 12));

        regionBox = new ComboBox<>(Config.getInstance().getConfigValues("service-regions"));
        usageTypeBox = new ComboBox<>(Config.getInstance().getConfigValues("service-usage-type"));
        usagePeriodBox = new ComboBox<>();
        usagePrepayBox = new ComboBox<>(Config.getInstance().getConfigValues("service-usage-prepay"));
        opModeBox = new ComboBox<>(Config.getInstance().getConfigValues("service-operating-mode"));

        usagePeriodBox.setDisable(true);
        usagePrepayBox.setDisable(true);

        setServiceUsageControls();

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

    public String getRegionItem() {
        return regionBox.getValue();
    }
    public void setRegionItem(String item) {this.regionBox.getSelectionModel().select(item);}

    public String getUsageTypeItem() {
        return usageTypeBox.getValue();
    }
    public void setUsageTypeItem(String item) {this.usageTypeBox.getSelectionModel().select(item);}

    public String getUsagePeriodItem() {
        return usagePeriodBox.getValue();
    }
    public void setUsagePeriodItem(String item) {this.usagePeriodBox.getSelectionModel().select(item);}

    public String getUsagePrepayItem() {
        return usagePrepayBox.getValue();
    }
    public void setUsagePrepayItem(String item) {this.usagePrepayBox.getSelectionModel().select(item);}

    public String getOpModeItem() {
        return opModeBox.getValue();
    }
    public void setOpModeItem(String item) {this.opModeBox.getSelectionModel().select(item);}

    private void setServiceUsageControls() {
        add(serviceUsageLbl, 0,0);
        setColumnSpan(serviceUsageLbl, 2);
        add(new Label("Region: "), 0, 1);
        add(regionBox, 1, 1);
        add(new Label("Usage type: "), 0, 2);
        add(usageTypeBox, 1, 2);
        add(new Label("Usage period: "), 0, 3);
        add(usagePeriodBox, 1, 3);
        add(new Label("Usage prepay: "), 0, 4);
        add(usagePrepayBox, 1, 4);
        add(new Label("Operating mode: "), 0, 5);
        add(opModeBox, 1, 5);
    }
}