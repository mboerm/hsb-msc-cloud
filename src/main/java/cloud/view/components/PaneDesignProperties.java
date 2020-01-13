package cloud.view.components;

import cloud.configuration.Config;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class PaneDesignProperties extends GridPane {

    private ComboBox<String> regionBox;
    private ComboBox<String> usageTypeBox;
    private ComboBox<String> usagePeriodBox;
    private ComboBox<String> usagePrepayBox;
    private ComboBox<String> opModeBox;

    public PaneDesignProperties() {
        setPadding(new Insets(10, 10, 10, 10));
        setHgap(10);
        setVgap(10);
        setAlignment(Pos.TOP_CENTER);

        Label regionLbl = new Label("Region: ");
        regionBox = new ComboBox<>();
        regionBox.getItems().addAll(Config.getInstance().getConfigValues("component-regions"));

        Label usageTypeLbl = new Label("Usage type: ");
        usageTypeBox = new ComboBox<>();
        usageTypeBox.getItems().addAll(Config.getInstance().getConfigValues("component-usage-type"));

        Label usagePeriodLbl = new Label("Usage period: ");
        usagePeriodBox = new ComboBox<>();
        usagePeriodBox.getItems().addAll(Config.getInstance().getConfigValues("component-usage-period"));

        Label usagePrepayLbl = new Label("Usage prepay: ");
        usagePrepayBox = new ComboBox<>();
        usagePrepayBox.getItems().addAll(Config.getInstance().getConfigValues("component-usage-prepay"));
        usagePrepayBox.setDisable(true);

        Label opModeLbl = new Label("Operating mode: ");
        opModeBox = new ComboBox<>();
        opModeBox.getItems().addAll(Config.getInstance().getConfigValues("component-operating-mode"));

        add(regionLbl, 0, 0);
        add(regionBox, 1, 0);
        add(usageTypeLbl, 0, 1);
        add(usageTypeBox, 1, 1);
        add(usagePeriodLbl, 0, 2);
        add(usagePeriodBox, 1, 2);
        add(usagePrepayLbl, 0, 3);
        add(usagePrepayBox, 1, 3);
        add(opModeLbl, 0, 4);
        add(opModeBox, 1, 4);

        usageTypeBox.getSelectionModel().selectedItemProperty().addListener((ov, oldItem, newItem) -> {
            if (newItem.equals(Config.getInstance().getConfigValues("component-usage-type")[1]))
                this.usagePrepayBox.setDisable(false);
            else {
                this.usagePrepayBox.setDisable(true);
            }
        });
    }

    public ComboBox<String> getRegionBox() {
        return regionBox;
    }
    public ComboBox<String> getUsageTypeBox() {
        return usageTypeBox;
    }
    public ComboBox<String> getUsagePeriodBox() {
        return usagePeriodBox;
    }
    public ComboBox<String> getUsagePrepayBox() {
        return usagePrepayBox;
    }
    public ComboBox<String> getOpModeBox() {
        return opModeBox;
    }
}
