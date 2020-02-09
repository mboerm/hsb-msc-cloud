package cloud.view.dialogs;

import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class CostReport extends Dialog<ButtonType> {

    private TableView<String> costsTable = new TableView<>();
    private Label totalCostsValueLabel;
    private ButtonType buttonTypeCreate;

    public CostReport() {
        setTitle("Cost Calculation Report");
        setResizable(false);

        Stage stage = (Stage) getDialogPane().getScene().getWindow();
        stage.setMinHeight(100);
        stage.setMinWidth(500);

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
        TableColumn serviceNameCol = new TableColumn("Name");
        TableColumn serviceDisplayCol = new TableColumn("Service");
        TableColumn serviceProviderNameCol = new TableColumn("Provider-Service");
        TableColumn serviceCostsCol = new TableColumn("Costs");

        serviceNameCol.setMaxWidth( 1f * Integer.MAX_VALUE * 30 );
        serviceDisplayCol.setMaxWidth( 1f * Integer.MAX_VALUE * 30 );
        serviceProviderNameCol.setMaxWidth(1f * Integer.MAX_VALUE * 30);
        serviceCostsCol.setMaxWidth( 1f * Integer.MAX_VALUE * 30 );

        costsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        costsTable.getColumns().addAll(serviceNameCol, serviceDisplayCol, serviceProviderNameCol, serviceCostsCol);
    }

    public TableView<String> getCostsTable() {
        return this.costsTable;
    }
    public ButtonType getButtonTypeCreate() {return this.buttonTypeCreate;}
    public Label getTotalCostsValueLabel() {return this.totalCostsValueLabel;}
}
