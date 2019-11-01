package cloud.ui;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

class DesignComponents extends VBox {

    DesignComponents() {
        Label computeLabel = new Label("Compute Components");
        TitledPane computePane = new TitledPane("Compute", computeLabel);

        Label storageLabel = new Label("Storage Components");
        TitledPane storagePane = new TitledPane("Storage", storageLabel);

        Label databaseLabel = new Label("Database Components");
        TitledPane databasePane = new TitledPane("Database", databaseLabel);

        Label networkLabel = new Label("Network Components");
        TitledPane networkPane = new TitledPane("Network", networkLabel);

        Label managementLabel = new Label("Management Components");
        TitledPane managementPane = new TitledPane("Management", managementLabel);

        Label integrationLabel = new Label("Integration Components");
        TitledPane integrationPane = new TitledPane("Integration", integrationLabel);

        Label analyticsLabel = new Label("Analytics Components");
        TitledPane analyticsPane = new TitledPane("Analytics", analyticsLabel);

        getChildren().addAll(computePane, storagePane, databasePane, networkPane, managementPane, integrationPane, analyticsPane);
    }
}
