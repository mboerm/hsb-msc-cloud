package cloud.ui;

import cloud.constants.Consts;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class CloudUI {
    private Stage stage;
    private Scene scene;

    public CloudUI() {
        BorderPane rootPane = new BorderPane();

        rootPane.setTop(initMenuBar());
        rootPane.setLeft(initDesignProperties());
        rootPane.setCenter(initDesignArea());
        rootPane.setRight(initDesignControls());
        rootPane.setBottom(initTaskBar());

        scene = new Scene(rootPane, Consts.WINDOW_DEFAULT_WIDTH, Consts.WINDOW_DEFAULT_HEIGHT);
    }

    public void show(Stage stage) {
        this.stage = stage;
        stage.setMinHeight(Consts.WINDOW_MIN_HEIGHT);
        stage.setMinWidth(Consts.WINDOW_MIN_WIDTH);
        stage.setTitle(Consts.APP_TITLE);
        stage.setScene(scene);
        stage.show();
    }

    private MenuBar initMenuBar() {
        MenuBar menuBar = new MenuBar();

        Menu menuFile = new Menu("File");
        MenuItem menuFileNew = new MenuItem("New Session");
        MenuItem menuFileOpen = new MenuItem("Open Session");
        MenuItem menuFileSave = new MenuItem("Save Session");
        MenuItem menuFileExit = new MenuItem("Exit");

        Menu menuEdit = new Menu("Edit");
        Menu menuView = new Menu("View");

        Menu menuDesign = new Menu("Design");
        MenuItem menuDesignReset = new MenuItem("Reset Design");

        Menu menuServices = new Menu("Services");
        MenuItem menuServicesAmazon = new MenuItem("Amazon Web Services");
        MenuItem menuServicesWindows = new MenuItem("Windows Azure");
        MenuItem menuServicesGoogle = new MenuItem("Google Cloud Platform");
        menuServicesWindows.setDisable(true);
        menuServicesGoogle.setDisable(true);

        Menu menuHelp = new Menu("Help");
        MenuItem menuHelpAbout = new MenuItem("About");

        menuBar.getMenus().add(menuFile);
        menuBar.getMenus().add(menuEdit);
        menuBar.getMenus().add(menuView);
        menuBar.getMenus().add(menuDesign);
        menuBar.getMenus().add(menuServices);
        menuBar.getMenus().add(menuHelp);

        menuFile.getItems().addAll(menuFileNew, menuFileOpen, menuFileSave, new SeparatorMenuItem(), menuFileExit);
        menuDesign.getItems().addAll(menuDesignReset);
        menuServices.getItems().addAll(menuServicesAmazon, menuServicesWindows, menuServicesGoogle);
        menuHelp.getItems().addAll(menuHelpAbout);

        menuFileNew.setOnAction(actionEvent -> {

        });

        menuFileOpen.setOnAction(actionEvent -> {
            FileChooser fileChooser = new FileChooser();
            File selectedFile = fileChooser.showOpenDialog(stage);
        });

        menuFileSave.setOnAction(actionEvent -> {

        });

        menuFileExit.setOnAction(actionEvent -> System.exit(0));

        menuHelpAbout.setOnAction(actionEvent -> callAboutDialog());

        return menuBar;
    }

    private DesignProperties initDesignProperties() {
        return new DesignProperties();
    }

    private DesignArea initDesignArea() {
        return new DesignArea();
    }

    private DesignControls initDesignControls() {
        return new DesignControls();
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
        alert.setTitle("About " + Consts.APP_TITLE);
        alert.setHeaderText(null);
        alert.setContentText(Consts.ABOUT_TEXT);

        alert.showAndWait();
    }
}