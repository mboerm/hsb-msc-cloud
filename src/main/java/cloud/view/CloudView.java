package cloud.view;

import cloud.constants.Consts;
import cloud.model.Session;
import cloud.model.StageManager;
import cloud.view.designs.DesignArea;
import cloud.view.designs.DesignControls;
import cloud.view.designs.DesignProperties;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class CloudView {
    private Scene scene;

    private Menu menuFile;
    private Menu menuEdit;
    private Menu menuView;
    private Menu menuDesign;
    private Menu menuServices;
    private Menu menuHelp;
    private Label taskLbl;

    public CloudView() {
        BorderPane rootPane = new BorderPane();
        rootPane.setTop(initMenuBar());
        rootPane.setLeft(initDesignProperties());
        rootPane.setCenter(initDesignArea());
        rootPane.setRight(initDesignControls());
        rootPane.setBottom(initTaskBar());
        scene = new Scene(rootPane, Consts.WINDOW_DEFAULT_WIDTH, Consts.WINDOW_DEFAULT_HEIGHT);
    }

    public void show(Stage stage) {
        stage.setMinHeight(Consts.WINDOW_MIN_HEIGHT);
        stage.setMinWidth(Consts.WINDOW_MIN_WIDTH);
        stage.setTitle(Consts.APP_TITLE);
        stage.setScene(scene);
        stage.show();
    }

    private MenuBar initMenuBar() {
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().add(initMenuFile());
        menuBar.getMenus().add(initMenuEdit());
        menuBar.getMenus().add(initMenuDesign());
        menuBar.getMenus().add(initMenuView());
        menuBar.getMenus().add(initMenuServices());
        menuBar.getMenus().add(initMenuHelp());

        return menuBar;
    }

    private Menu initMenuFile() {
        menuFile = new Menu("File");
        MenuItem menuFileNew = new MenuItem("New Session");
        MenuItem menuFileOpen = new MenuItem("Open Session");
        MenuItem menuFileSave = new MenuItem("Save Session");
        MenuItem menuFileExit = new MenuItem("Exit");
        menuFile.getItems().addAll(menuFileNew, menuFileOpen, menuFileSave, new SeparatorMenuItem(), menuFileExit);

        return menuFile;
    }

    private Menu initMenuEdit() {
        menuEdit = new Menu("Edit");

        return menuEdit;
    }

    private Menu initMenuView() {
        menuView = new Menu("View");

        return menuView;
    }

    private Menu initMenuDesign() {
        menuDesign = new Menu("Design");
        MenuItem menuDesignAnalyse = new MenuItem("Analyse Design");
        MenuItem menuDesignOptimize = new MenuItem("Optimize Design");
        MenuItem menuDesignReset = new MenuItem("Reset Design");
        menuDesign.getItems().addAll(menuDesignAnalyse, menuDesignOptimize, menuDesignReset);

        menuDesignReset.setOnAction(actionEvent -> {

        });

        return menuDesign;
    }

    private Menu initMenuServices() {
        menuServices = new Menu("Services");
        MenuItem menuServicesAmazon = new MenuItem("Amazon Web Services");
        MenuItem menuServicesWindows = new MenuItem("Windows Azure");
        MenuItem menuServicesGoogle = new MenuItem("Google Cloud Platform");
        menuServicesWindows.setDisable(true);
        menuServicesGoogle.setDisable(true);
        menuServices.getItems().addAll(menuServicesAmazon, menuServicesWindows, menuServicesGoogle);

        return menuServices;
    }

    private Menu initMenuHelp() {
        menuHelp = new Menu("Help");
        MenuItem menuHelpAbout = new MenuItem("About");
        menuHelp.getItems().addAll(menuHelpAbout);

        menuHelpAbout.setOnAction(actionEvent -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("About " + Consts.APP_TITLE);
            alert.setHeaderText(null);
            alert.setContentText(Consts.ABOUT_TEXT);
            alert.showAndWait();
        });

        return menuHelp;
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
        taskLbl = new Label("Init");
        taskLbl.setTextAlignment(TextAlignment.RIGHT);
        taskLbl.setWrapText(true);
        taskBox.setAlignment(Pos.CENTER_RIGHT);
        taskBox.getChildren().add(taskLbl);
        return taskBox;
    }

    public Menu getMenuFile() {
        return this.menuFile;
    }

    public Menu getMenuEdit() {
        return this.menuEdit;
    }

    public Menu getMenuView() {
        return this.menuView;
    }

    public Menu getMenuDesign() {
        return this.menuDesign;
    }

    public Menu getMenuServices() {
        return this.menuServices;
    }

    public Menu getMenuHelp() {
        return this.menuHelp;
    }

    public Label getTaskBar() {
        return this.taskLbl;
    }
}