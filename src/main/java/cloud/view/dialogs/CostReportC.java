package cloud.view.dialogs;

import cloud.model.design.DesignManager;
import cloud.model.pricing.Costs;
import cloud.model.services.Service;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ButtonType;
import javafx.util.Pair;

import java.util.Optional;

public class CostReportC {
    private CostReport costReport;

    public boolean showCostReport() {
        costReport = new CostReport();
        setCostsData();

        Optional<ButtonType> result = costReport.showAndWait();

        return (result.isPresent()) && (result.get() == costReport.getButtonTypeCreate());
    }

    private void setCostsData() {
        ObservableList<Pair<Service, Costs>> costs = DesignManager.getInstance().getDesign().getServicesCosts();
        ObservableList<CostReport.Row> rowList = FXCollections.observableArrayList();
        double totalCosts = 0;

        for (Pair<Service, Costs> cost : costs) {
            rowList.add(new CostReport.Row(
                    cost.getKey().getName(),
                    cost.getKey().getProviderService(),
                    cost.getValue().getPrice().toString() + " USD")
            );
            totalCosts += cost.getValue().getPrice();
        }
        costReport.getCostsTable().setItems(rowList);
        costReport.getTotalCostsValueLabel().setText(totalCosts + " USD");
    }
}
