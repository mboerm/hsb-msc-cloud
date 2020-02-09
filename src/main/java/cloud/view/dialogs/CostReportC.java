package cloud.view.dialogs;

import cloud.model.design.DesignManager;
import cloud.model.pricing.Costs;
import cloud.model.services.Service;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ButtonType;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
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
        List list = new ArrayList();
        double totalCosts = 0;

        for (Pair<Service, Costs> cost : costs) {
            list.add(new String[]{
                    cost.getKey().getName(),
                    cost.getKey().getDisplayName(),
                    cost.getKey().getProviderService(),
                    cost.getValue().getPrice().toString() + " USD"
            });
            totalCosts += cost.getValue().getPrice();
        }
        costReport.getCostsTable().setItems(FXCollections.observableList(list));
        costReport.getCostsTable().refresh();
        costReport.getTotalCostsValueLabel().setText(totalCosts + " USD");
    }
}
