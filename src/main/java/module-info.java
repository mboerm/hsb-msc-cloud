module cloud {
    requires javafx.controls;
    requires javafx.fxml;

    opens hsb.msc to javafx.fxml;
    exports hsb.msc;
}