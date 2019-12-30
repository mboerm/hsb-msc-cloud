package cloud.view.designs;

import cloud.model.Component;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class DesignArea extends HBox {
    private StackPane compPane;
    private TableView<Component> componentsTable;

    private VBox controlBox;
    private Button controlAdd;
    private Button controlRemove;
    private Button controlEdit;

    public DesignArea() {
        initArea();
        initControls();
        getChildren().addAll(compPane, controlBox);
        setSpacing(10);
    }

    private void initArea() {
        TableColumn<Component, String> compNameCol = new TableColumn<Component, String>("Name");
        TableColumn<Component, String> compServiceCol = new TableColumn<Component, String>("Service");
        TableColumn<Component, String> compCatCol = new TableColumn<Component, String>("Category");
        TableColumn<Component, String> compUsageCol = new TableColumn<Component, String>("Usage");

        componentsTable = new TableView<Component>();
        componentsTable.getColumns().addAll(compNameCol, compServiceCol, compCatCol, compUsageCol);

        compPane = new StackPane();
        compPane.getChildren().add(componentsTable);
        compPane.setAlignment(Pos.CENTER);
    }

    private void initControls() {
        controlAdd = new Button("Add");
        controlRemove = new Button("Remove");
        controlEdit = new Button("Edit");

        controlBox = new VBox();
        controlBox.setAlignment(Pos.TOP_CENTER);
        controlBox.setSpacing(10);
        controlBox.setPadding(new Insets(5,5, 5,5));
        controlBox.getChildren().addAll(controlAdd, controlRemove, controlEdit);
    }

    public Button getControlAdd() {
        return this.controlAdd;
    }

    public Button getControlRemove() {
        return this.controlRemove;
    }

    public Button getControlEdit() {
        return this.controlEdit;
    }

    public TableView<Component> getComponentsTable() {
        return this.componentsTable;
    }
}
