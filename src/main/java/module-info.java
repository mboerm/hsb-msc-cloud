module cloud {
    requires javafx.controls;
    requires javafx.fxml;

    opens cloud.main to javafx.fxml;
    exports cloud.main;
}