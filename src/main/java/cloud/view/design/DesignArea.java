package cloud.view.design;

import cloud.model.services.*;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;

public class DesignArea extends VBox {
    private TableView<Service> servicesTable = new TableView<>();

    public DesignArea() {
        initArea();
        getChildren().add(servicesTable);
    }

    private void initArea() {
        TableColumn<Service, String> serviceNameCol = new TableColumn<>("Name");
        TableColumn<Service, String> serviceDisplayCol = new TableColumn<>("Service");
        TableColumn<Service, String> serviceProviderNameCol = new TableColumn<>("Provider-Service");
        TableColumn<Service, String> serviceCategoryCol = new TableColumn<>("Category");

        serviceNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        serviceDisplayCol.setCellValueFactory(new PropertyValueFactory<>("displayName"));
        serviceProviderNameCol.setCellValueFactory(new PropertyValueFactory<>("providerService"));
        serviceCategoryCol.setCellValueFactory(new PropertyValueFactory<>("category"));

        servicesTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        servicesTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        serviceNameCol.setMaxWidth( 1f * Integer.MAX_VALUE * 30 );
        serviceDisplayCol.setMaxWidth( 1f * Integer.MAX_VALUE * 30 );
        serviceProviderNameCol.setMaxWidth(1f * Integer.MAX_VALUE * 30);
        serviceCategoryCol.setMaxWidth( 1f * Integer.MAX_VALUE * 30 );
        servicesTable.getColumns().addAll(serviceNameCol, serviceDisplayCol, serviceProviderNameCol, serviceCategoryCol);
    }

    public TableView<Service> getServicesTable() {
        return this.servicesTable;
    }
}
