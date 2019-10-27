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
        root.setCenter(getTabPane());

        Scene scene = new Scene(root, 800, 600);

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

    private TabPane getTabPane() {
        TabPane tabPane = new TabPane();
        Tab tabConfig = new Tab("Configurations");
        tabConfig.setClosable(false);
        Tab tabArch = new Tab("Architecture");
        tabArch.setClosable(false);

        tabPane.getTabs().add(tabConfig);
        tabPane.getTabs().add(tabArch);

        return tabPane;
    }

    public static void main(String[] args) {
        launch();
    }
}