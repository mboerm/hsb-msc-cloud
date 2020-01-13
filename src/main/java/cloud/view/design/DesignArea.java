package cloud.view.design;

import cloud.model.components.*;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;

public class DesignArea extends VBox {
    private TableView<Component> componentsTable = new TableView<>();

    public DesignArea() {
        initArea();
        getChildren().add(componentsTable);
    }

    private void initArea() {
        TableColumn compNameCol = new TableColumn("Name");
        TableColumn compServiceCol = new TableColumn("Service");
        TableColumn compProviderServiceCol = new TableColumn("Provider-Service");
        TableColumn compCatCol = new TableColumn("Category");

        compNameCol.setCellValueFactory(new PropertyValueFactory<Component, String>("name"));
        compServiceCol.setCellValueFactory(new PropertyValueFactory<Component, String>("service"));
        compProviderServiceCol.setCellValueFactory(new PropertyValueFactory<Component, String>("providerService"));
        compCatCol.setCellValueFactory(new PropertyValueFactory<Component, String>("category"));

        componentsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        componentsTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        compNameCol.setMaxWidth( 1f * Integer.MAX_VALUE * 30 );
        compServiceCol.setMaxWidth( 1f * Integer.MAX_VALUE * 30 );
        compProviderServiceCol.setMaxWidth(1f * Integer.MAX_VALUE * 30);
        compCatCol.setMaxWidth( 1f * Integer.MAX_VALUE * 30 );
        componentsTable.getColumns().addAll(compNameCol, compServiceCol, compProviderServiceCol, compCatCol);
    }

    public TableView<Component> getComponentsTable() {
        return this.componentsTable;
    }
}
