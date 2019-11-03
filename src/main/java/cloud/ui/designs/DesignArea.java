package cloud.ui.designs;

import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class DesignArea extends StackPane {

    public DesignArea() {
        Text properties = new Text("Design Area");
        getChildren().add(properties);
        setAlignment(Pos.CENTER);
    }
}
