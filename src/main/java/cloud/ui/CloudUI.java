package cloud.ui;

import cloud.constants.Consts;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class CloudUI {

    private Scene scene;

    public CloudUI() {
        BorderPane rootPane = new BorderPane();

        rootPane.setTop(initMenuBar());
        rootPane.setRight(initDesignPane());
        rootPane.setBottom(initTaskBar());

        scene = new Scene(rootPane, 800, 600);
    }

    public void show(Stage stage) {
        stage.setMinHeight(600);
        stage.setMinWidth(800);
        stage.setTitle(Consts.TITLE);
        stage.setScene(scene);
        stage.show();
    }

    private MenuBar initMenuBar() {
        MenuBar menuBar = new MenuBar();

        Menu menuFile = new Menu("File");
        MenuItem menuItemNew = new MenuItem("New Session");
        MenuItem menuItemOpen = new MenuItem("Open Session");
        MenuItem menuItemSave = new MenuItem("Save Session");
        MenuItem menuItemExit = new MenuItem("Exit");

        menuItemExit.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                System.exit(0);
            }
        });

        menuFile.getItems().addAll(menuItemNew, menuItemOpen, menuItemSave, new SeparatorMenuItem(), menuItemExit);

        Menu menuEdit = new Menu("Edit");
        Menu menuView = new Menu("View");
        Menu menuDesign = new Menu("Design");
        Menu menuServices = new Menu("Services");
        Menu menuHelp = new Menu("Help");

        MenuItem menuItemAbout = new MenuItem("About");
        menuItemAbout.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                callAboutDialog();
            }
        });

        menuHelp.getItems().add(menuItemAbout);

        menuBar.getMenus().add(menuFile);
        menuBar.getMenus().add(menuEdit);
        menuBar.getMenus().add(menuView);
        menuBar.getMenus().add(menuDesign);
        menuBar.getMenus().add(menuServices);
        menuBar.getMenus().add(menuHelp);

        return menuBar;
    }

    private DesignPane initDesignPane() {
        return new DesignPane();
    }

    private HBox initTaskBar() {
        HBox taskBox = new HBox();
        taskBox.setPadding(new Insets(5, 5, 5, 5));

        Label taskLbl = new Label("Init");
        taskLbl.setTextAlignment(TextAlignment.RIGHT);
        taskLbl.setWrapText(true);

        taskBox.setAlignment(Pos.CENTER_RIGHT);
        taskBox.getChildren().add(taskLbl);

        return taskBox;
    }

    private void callAboutDialog() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About " + Consts.TITLE);
        alert.setHeaderText(null);
        alert.setContentText(Consts.ABOUT);

        alert.showAndWait();
    }
}