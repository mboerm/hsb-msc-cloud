package cloud.view.design;

import cloud.model.services.*;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;

public class DesignArea extends VBox {
    private TableView<Service> componentsTable = new TableView<>();

    public DesignArea() {
        initArea();
        getChildren().add(componentsTable);
    }

    private void initArea() {
        TableColumn<Service, String> serviceNameCol = new TableColumn<>("Name");
        TableColumn<Service, String> serviceTypeCol = new TableColumn<>("Service");
        TableColumn<Service, String> serviceProviderNameCol = new TableColumn<>("Provider-Service");
        TableColumn<Service, String> serviceCategoryCol = new TableColumn<>("Category");

        serviceNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        serviceTypeCol.setCellValueFactory(new PropertyValueFactory<>("service"));
        serviceProviderNameCol.setCellValueFactory(new PropertyValueFactory<>("providerService"));
        serviceCategoryCol.setCellValueFactory(new PropertyValueFactory<>("category"));

        componentsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        componentsTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        serviceNameCol.setMaxWidth( 1f * Integer.MAX_VALUE * 30 );
        serviceTypeCol.setMaxWidth( 1f * Integer.MAX_VALUE * 30 );
        serviceProviderNameCol.setMaxWidth(1f * Integer.MAX_VALUE * 30);
        serviceCategoryCol.setMaxWidth( 1f * Integer.MAX_VALUE * 30 );
        componentsTable.getColumns().addAll(serviceNameCol, serviceTypeCol, serviceProviderNameCol, serviceCategoryCol);
    }

    public TableView<Service> getComponentsTable() {
        return this.componentsTable;
    }
}