package cloud.ui;

import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

class DesignArea extends StackPane {

    DesignArea() {
        Text properties = new Text("Design Area");
        getChildren().add(properties);
        setAlignment(Pos.CENTER);
    }
}
