package cloud.view.dialogs;

import cloud.configuration.Constants;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class CostDialog extends Dialog<ButtonType> {

    private TableView<Row> costsTable = new TableView<>();
    private Label totalCostsValueLabel;
    private ButtonType buttonTypeCreate;

    public CostDialog() {
        setTitle(Constants.DIALOG_COSTS_TITLE);
        setResizable(false);

        Stage stage = (Stage) getDialogPane().getScene().getWindow();
        stage.setMinHeight(Constants.DIALOG_MIN_HEIGHT);
        stage.setMinWidth(Constants.DIALOG_MIN_WIDTH);

        initCostsTable();
        VBox tableBox = new VBox();
        HBox totalCostsBox = new HBox();
        totalCostsBox.setAlignment(Pos.CENTER_RIGHT);
        totalCostsBox.setSpacing(50);

        Label totalCostsLabel = new Label("Total Costs: ");
        totalCostsValueLabel = new Label("");
        totalCostsLabel.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        totalCostsValueLabel.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        totalCostsBox.getChildren().addAll(totalCostsLabel, totalCostsValueLabel);

        tableBox.getChildren().add(costsTable);
        tableBox.getChildren().add(totalCostsBox);
        tableBox.setSpacing(25);
        getDialogPane().setContent(tableBox);

        buttonTypeCreate = new ButtonType("Create Report", ButtonBar.ButtonData.OK_DONE);
        getDialogPane().getButtonTypes().addAll(buttonTypeCreate, ButtonType.CANCEL);
    }

    private void initCostsTable() {
        TableColumn<Row, String> serviceNameCol = new TableColumn<>("Name");
        TableColumn<Row, String> serviceProviderNameCol = new TableColumn<>("Provider-Service");
        TableColumn<Row, String> serviceCostsCol = new TableColumn<>("Costs");

        serviceNameCol.setCellValueFactory(new PropertyValueFactory<>("fieldName"));
        serviceProviderNameCol.setCellValueFactory(new PropertyValueFactory<>("fieldProviderService"));
        serviceCostsCol.setCellValueFactory(new PropertyValueFactory<>("fieldCost"));

        serviceNameCol.setMaxWidth( 1f * Integer.MAX_VALUE * 30 );
        serviceProviderNameCol.setMaxWidth(1f * Integer.MAX_VALUE * 30);
        serviceCostsCol.setMaxWidth( 1f * Integer.MAX_VALUE * 30 );

        costsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        costsTable.getColumns().addAll(serviceNameCol, serviceProviderNameCol, serviceCostsCol);
    }

    public TableView<Row> getCostsTable() {
        return costsTable;
    }
    public ButtonType getButtonTypeCreate() {return this.buttonTypeCreate;}
    public Label getTotalCostsValueLabel() {return this.totalCostsValueLabel;}

    public static class Row {
        private SimpleStringProperty fieldName;
        private SimpleStringProperty fieldProviderService;
        private SimpleStringProperty fieldCost;

        Row(String fName, String fProviderService, String fCost){
            this.fieldName = new SimpleStringProperty(fName);
            this.fieldProviderService = new SimpleStringProperty(fProviderService);
            this.fieldCost = new SimpleStringProperty(fCost);
        }

        public String getFieldName() {
            return fieldName.get();
        }
        public String getFieldProviderService() {
            return fieldProviderService.get();
        }
        public String getFieldCost() {
            return fieldCost.get();
        }
    }
}
