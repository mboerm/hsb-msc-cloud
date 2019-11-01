package cloud.ui;

import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class DesignPane extends BorderPane {

    private HBox designButtons;
    private Button designAdd;
    private Button designRemove;
    private Button designEdit;
    private Button designReset;

    private VBox designComponents;


    private StackPane designArea;

    public DesignPane() {
        designButtons = new HBox();
        designComponents = new VBox();
        designArea = new StackPane();

        this.setTop(designButtons);
        this.setRight(designComponents);
        this.setCenter(designArea);
    }

    private void initDesignButtons() {
        designAdd = new Button("+");
        designRemove = new Button("-");
        designEdit = new Button("E");
        designReset = new Button("R");

        designButtons.setSpacing(10);
        designButtons.getChildren().addAll(designAdd, designRemove, designEdit, designReset);
    }

    private void initDesignComponents() {

    }

    private void initDesignArea() {

    }
}
