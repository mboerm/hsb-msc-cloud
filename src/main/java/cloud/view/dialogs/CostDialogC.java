package cloud.view.dialogs;

import cloud.configuration.Constants;
import cloud.model.design.DesignManager;
import cloud.model.pricing.Costs;
import cloud.model.services.Service;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ButtonType;
import javafx.util.Pair;

import java.util.Optional;

public class CostDialogC {
    private CostDialog costReport;

    public boolean showCostReport() {
        costReport = new CostDialog();
        setCostsData();

        Optional<ButtonType> result = costReport.showAndWait();

        return (result.isPresent()) && (result.get() == costReport.getButtonTypeCreate());
    }

    private void setCostsData() {
        ObservableList<Pair<Service, Costs>> costs = DesignManager.getInstance().getDesign().getServicesCosts();
        ObservableList<CostDialog.Row> rowList = FXCollections.observableArrayList();

        for (Pair<Service, Costs> cost : costs) {
            rowList.add(new CostDialog.Row(
                    cost.getKey().getName(),
                    cost.getKey().getProviderService(),
                    Constants.DOUBLE_FORMAT_2.format(cost.getValue().getPrice()) + " USD")
            );
        }
        costReport.getCostsTable().setItems(rowList);
        costReport.getTotalCostsValueLabel().setText(Constants.DOUBLE_FORMAT_2.format(DesignManager.getInstance().getDesign().getTotalCosts()) + " USD");
    }
}
