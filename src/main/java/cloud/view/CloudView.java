package cloud.view;

import cloud.constants.Consts;
import cloud.view.designs.DesignArea;
import cloud.view.designs.DesignControls;
import cloud.view.designs.DesignProperties;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class CloudView {
    private Scene scene;

    private MenuItem menuFileNew;
    private MenuItem menuFileOpen;
    private MenuItem menuFileSave;
    private MenuItem menuFileExit;
    private MenuItem menuDesignAnalyse;
    private MenuItem menuDesignOptimize;
    private MenuItem menuDesignReset;
    private MenuItem menuServicesAmazon;
    private MenuItem menuServicesWindows;
    private MenuItem menuServicesGoogle;
    private MenuItem menuHelpAbout;
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
        Menu menuFile = new Menu("File");
        menuFileNew = new MenuItem("New Session");
        menuFileOpen = new MenuItem("Open Session");
        menuFileSave = new MenuItem("Save Session");
        menuFileExit = new MenuItem("Exit");
        menuFile.getItems().addAll(menuFileNew, menuFileOpen, menuFileSave, new SeparatorMenuItem(), menuFileExit);
        return menuFile;
    }

    private Menu initMenuEdit() {
        Menu menuEdit = new Menu("Edit");
        return menuEdit;
    }

    private Menu initMenuView() {
        Menu menuView = new Menu("View");
        return menuView;
    }

    private Menu initMenuDesign() {
        Menu menuDesign = new Menu("Design");
        menuDesignAnalyse = new MenuItem("Analyse Design");
        menuDesignOptimize = new MenuItem("Optimize Design");
        menuDesignReset = new MenuItem("Reset Design");
        menuDesign.getItems().addAll(menuDesignAnalyse, menuDesignOptimize, menuDesignReset);
        return menuDesign;
    }

    private Menu initMenuServices() {
        Menu menuServices = new Menu("Services");
        menuServicesAmazon = new MenuItem("Amazon Web Services");
        menuServicesWindows = new MenuItem("Windows Azure");
        menuServicesGoogle = new MenuItem("Google Cloud Platform");
        menuServicesWindows.setDisable(true);
        menuServicesGoogle.setDisable(true);
        menuServices.getItems().addAll(menuServicesAmazon, menuServicesWindows, menuServicesGoogle);
        return menuServices;
    }

    private Menu initMenuHelp() {
        Menu menuHelp = new Menu("Help");
        menuHelpAbout = new MenuItem("About");
        menuHelp.getItems().addAll(menuHelpAbout);
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

    public MenuItem getMenuFileNew() {
        return this.menuFileNew;
    }
    public MenuItem getMenuFileOpen() {
        return this.menuFileOpen;
    }
    public MenuItem getMenuFileSave() {
        return this.menuFileSave;
    }
    public MenuItem getMenuFileExit() {
        return this.menuFileExit;
    }

    public MenuItem getMenuDesignAnalyse() {
        return this.menuDesignAnalyse;
    }
    public MenuItem getMenuDesignOptimize() {
        return this.menuDesignOptimize;
    }
    public MenuItem getMenuDesignReset() {
        return this.menuDesignReset;
    }

    public MenuItem getMenuServicesAmazon() {
        return this.menuServicesAmazon;
    }
    public MenuItem getMenuServicesWindows() {
        return this.menuServicesWindows;
    }
    public MenuItem getMenuServicesGoogle() {
        return this.menuServicesGoogle;
    }

    public MenuItem getMenuHelpAbout() {
        return this.menuHelpAbout;
    }

    public Label getTaskBar() {
        return this.taskLbl;
    }
}