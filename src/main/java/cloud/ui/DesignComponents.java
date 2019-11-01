package cloud.ui;

import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

class DesignComponents extends VBox {

    DesignComponents() {
        Text components = new Text("Components");
        getChildren().add(components);
        setAlignment(Pos.CENTER);
    }
}
