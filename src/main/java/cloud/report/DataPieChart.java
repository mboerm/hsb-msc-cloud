package cloud.report;

import cloud.configuration.Constants;
import cloud.model.design.DesignManager;
import cloud.model.pricing.Costs;
import cloud.model.services.Service;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Side;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.chart.PieChart;
import javafx.scene.image.WritableImage;
import javafx.stage.Stage;
import javafx.util.Pair;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

/**
 * Pie chart class
 */
public class DataPieChart implements DataChart {
    private PieChart pieChart;

    /**
     * Interface method to create chart
     */
    @Override
    public void createChart() {
        Stage stage = new Stage();
        stage.setWidth(500);
        stage.setHeight(500);
        Scene scene = new Scene(new Group());
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

        //Creating a Pie chart
        pieChart = new PieChart(pieChartData);
        //setting the direction to arrange the data
        pieChart.setClockwise(true);

        //Setting the length of the label line
        pieChart.setLabelLineLength(50);
        //Setting the labels of the pie chart visible
        pieChart.setLabelsVisible(true);

        //Set legends to the left
        pieChart.setLegendVisible(true);
        pieChart.setLegendSide(Side.BOTTOM);

        //Setting the start angle of the pie chart
        pieChart.setStartAngle(180);
        ((Group) scene.getRoot()).getChildren().add(pieChart);

        setPieChartData();
        stage.setScene(scene);
    }

    /**
     * Interface method to save chart as png
     */
    @Override
    public void saveAsPng(){
        try {
            WritableImage image = pieChart.snapshot(new SnapshotParameters(), null);
            File file = new File("chart.png");
            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Method to set pie chart data
     */
    public void setPieChartData() {
        double percentageCosts;
        for (Pair<Service, Costs> servicePair : DesignManager.getInstance().getDesign().getServicesCosts()) {
            percentageCosts = (servicePair.getValue().getPrice() / DesignManager.getInstance().getDesign().getTotalCosts()) * 100;
            this.pieChart.getData().add(new PieChart.Data(
                    servicePair.getKey().getName() + " - " + Constants.DOUBLE_FORMAT_2.format(percentageCosts) + "%",
                    percentageCosts));
        }
    }
}
