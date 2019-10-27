package hsb.msc.cloud;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class CloudApp extends Application {
    private BorderPane root;

    private void initUI(Stage stage) {

        root = new BorderPane();

        root.setTop(getMenuBar());
        root.setCenter(getPane());
        root.setBottom(getTaskLabel());

        Scene scene = new Scene(root, 800, 600);

        stage.setMinHeight(600);
        stage.setMinWidth(800);
        stage.setTitle("Cloud Architecture Optimizer");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void start(Stage primaryStage) {
        initUI(primaryStage);
    }

    private MenuBar getMenuBar() {
        MenuBar menuBar = new MenuBar();

        Menu menuFile = new Menu("File");
        MenuItem menuItemSaveAs = new MenuItem("Save As");
        MenuItem menuItemExit = new MenuItem("Exit");
        menuFile.getItems().add(menuItemSaveAs);
        menuFile.getItems().add(menuItemExit);

        Menu menuEdit = new Menu("Edit");
        Menu menuView = new Menu("View");

        Menu menuHelp = new Menu("Help");
        MenuItem menuItemAbout = new MenuItem("About");
        menuHelp.getItems().add(menuItemAbout);

        menuBar.getMenus().add(menuFile);
        menuBar.getMenus().add(menuEdit);
        menuBar.getMenus().add(menuView);
        menuBar.getMenus().add(menuHelp);

        return menuBar;
    }

    private GridPane getPane() {
        GridPane grid = new GridPane();

        return grid;
    }

    private Label getTaskLabel() {
        Label taskLbl = new Label();

        return taskLbl;
    }

    public static void main(String[] args) {
        launch();
    }
}