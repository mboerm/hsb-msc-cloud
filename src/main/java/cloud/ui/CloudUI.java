package cloud.ui;

import cloud.constants.Consts;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class CloudUI {

    private Scene scene;
    private BorderPane root;

    private MenuBar menuBar;
    private GridPane grid;
    private Label taskLbl;

    public CloudUI() {
        root = new BorderPane();
        grid = new GridPane();

        taskLbl = new Label();

        initMenuBar();

        root.setTop(getMenuBar());
        root.setCenter(getPane());
        root.setBottom(getTaskLabel());
        scene = new Scene(root, 800, 600);
    }

    public void show(Stage stage) {
        stage.setMinHeight(600);
        stage.setMinWidth(800);
        stage.setTitle(Consts.TITLE);
        stage.setScene(scene);
        stage.show();
    }

    private void initMenuBar() {
        menuBar = new MenuBar();

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
        menuBar.getMenus().add(menuHelp);
    }

    private MenuBar getMenuBar() {
        return menuBar;
    }

    private GridPane getPane() {
        return grid;
    }

    private Label getTaskLabel() {
        return taskLbl;
    }

    private void callAboutDialog() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About " + Consts.TITLE);
        alert.setHeaderText(null);
        alert.setContentText(Consts.ABOUT);

        alert.showAndWait();
    }
}