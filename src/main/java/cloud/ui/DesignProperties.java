package cloud.ui;

import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

class DesignProperties extends VBox {

    DesignProperties() {
        Text properties = new Text("Properties");
        getChildren().add(properties);
        setAlignment(Pos.CENTER);
    }
}
