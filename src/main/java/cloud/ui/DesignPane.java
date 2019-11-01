package cloud.ui;

import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

class DesignPane extends BorderPane {

    DesignPane() {
        this.setTop(initDesignButtons());
        this.setRight(initDesignComponents());
        this.setCenter(initDesignArea());
    }

    private HBox initDesignButtons() {
        HBox designButtons = new HBox();
        Button designAdd = new Button("+");
        Button designRemove = new Button("-");
        Button designEdit = new Button("E");
        Button designReset = new Button("R");

        designButtons.setSpacing(10);
        designButtons.getChildren().addAll(designAdd, designRemove, designEdit, designReset);

        return designButtons;
    }

    private VBox initDesignComponents() {
        VBox designComponents = new VBox();
        return designComponents;
    }

    private StackPane initDesignArea() {
        StackPane designArea = new StackPane();
        return designArea;
    }
}
