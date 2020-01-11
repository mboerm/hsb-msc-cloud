package cloud.view.design;

import cloud.model.components.*;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.*;

public class DesignArea extends VBox {
    private TableView<Component> componentsTable;

    public DesignArea() {
        initArea();
        getChildren().add(componentsTable);
    }

    private void initArea() {
        TableColumn<Component, String> compNameCol = new TableColumn<Component, String>("Name");
        TableColumn<Component, String> compServiceCol = new TableColumn<Component, String>("Service");
        TableColumn<Component, String> compCatCol = new TableColumn<Component, String>("Category");

        componentsTable = new TableView<Component>();
        componentsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        compNameCol.setMaxWidth( 1f * Integer.MAX_VALUE * 30 );
        compServiceCol.setMaxWidth( 1f * Integer.MAX_VALUE * 30 );
        compCatCol.setMaxWidth( 1f * Integer.MAX_VALUE * 30 );
        componentsTable.getColumns().addAll(compNameCol, compServiceCol, compCatCol);
    }

    public TableView<Component> getComponentsTable() {
        return this.componentsTable;
    }
}
