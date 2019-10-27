module cloud {
    requires javafx.controls;
    requires javafx.fxml;

    opens hsb.msc.cloud to javafx.fxml;
    exports hsb.msc.cloud;
}