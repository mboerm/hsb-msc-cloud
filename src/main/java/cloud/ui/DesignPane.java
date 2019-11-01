package cloud.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

class DesignPane extends BorderPane {

    private HBox designButtons;
    private StackPane designArea;

    DesignPane() {
        initDesignButtons();
        initDesignArea();

        setTop(designButtons);
        setCenter(designArea);

        setAlignment(designButtons, Pos.TOP_CENTER);
        setAlignment(designArea, Pos.CENTER);
    }

    private void initDesignButtons() {
        designButtons = new HBox();
        Button designAdd = new Button("+");
        Button designRemove = new Button("-");
        Button designEdit = new Button("E");
        Button designReset = new Button("R");

        designButtons.setSpacing(10);
        designButtons.setMargin(designAdd, new Insets(10, 10, 10, 10));
        designButtons.setMargin(designRemove, new Insets(10, 10, 10, 10));
        designButtons.setMargin(designEdit, new Insets(10, 10, 10, 10));
        designButtons.setMargin(designReset, new Insets(10, 10, 10, 10));
        designButtons.setAlignment(Pos.CENTER);

        designButtons.getChildren().addAll(designAdd, designRemove, designEdit, designReset);
    }

    private void initDesignArea() {
        designArea = new StackPane();
    }
}
