package cloud.view;

import cloud.view.design.DesignArea;
import cloud.view.design.DesignControls;
import cloud.view.design.DesignProperties;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import static cloud.configuration.Constants.*;

public class CloudView {
    private Scene scene;

    private MenuItem menuFileExit;
    private MenuItem menuDesignAnalyse;
    private MenuItem menuDesignOptimize;
    private MenuItem menuDesignReset;
    private RadioMenuItem menuProviderAmazon;
    private RadioMenuItem menuProviderWindows;
    private RadioMenuItem menuProviderGoogle;
    private MenuItem menuHelpAbout;
    private Label taskLbl;

    private DesignProperties paneDesignProperties;
    private DesignArea paneDesignArea;
    private DesignControls paneDesignControls;

    public CloudView() {
        BorderPane rootPane = new BorderPane();
        rootPane.setTop(initMenuBar());
        rootPane.setLeft(initDesignProperties());
        rootPane.setCenter(initDesignArea());
        rootPane.setRight(initDesignControls());
        rootPane.setBottom(initTaskBar());
        scene = new Scene(rootPane);
    }

    public void show(Stage stage) {
        stage.setMinHeight(WINDOW_MIN_HEIGHT);
        stage.setMinWidth(WINDOW_MIN_WIDTH);
        stage.setTitle(APP_TITLE);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    private MenuBar initMenuBar() {
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().add(initMenuFile());
        menuBar.getMenus().add(initMenuEdit());
        menuBar.getMenus().add(initMenuDesign());
        menuBar.getMenus().add(initMenuView());
        menuBar.getMenus().add(initMenuProvider());
        menuBar.getMenus().add(initMenuHelp());
        return menuBar;
    }

    private Menu initMenuFile() {
        Menu menuFile = new Menu("File");
        menuFileExit = new MenuItem("Exit");
        menuFile.getItems().addAll(menuFileExit);
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

    private Menu initMenuProvider() {
        Menu menuProvider = new Menu("Provider");
        menuProviderAmazon = new RadioMenuItem("Amazon Web Services");
        menuProviderWindows = new RadioMenuItem("Windows Azure");
        menuProviderGoogle = new RadioMenuItem("Google Cloud Platform");
        ToggleGroup toggleGroup = new ToggleGroup();
        toggleGroup.getToggles().add(menuProviderAmazon);
        toggleGroup.getToggles().add(menuProviderWindows);
        toggleGroup.getToggles().add(menuProviderGoogle);
        menuProvider.getItems().addAll(menuProviderAmazon, menuProviderWindows, menuProviderGoogle);
        return menuProvider;
    }

    private Menu initMenuHelp() {
        Menu menuHelp = new Menu("Help");
        menuHelpAbout = new MenuItem("About");
        menuHelp.getItems().addAll(menuHelpAbout);
        return menuHelp;
    }

    private DesignProperties initDesignProperties() {
        paneDesignProperties = new DesignProperties();
        return paneDesignProperties;
    }

    private DesignArea initDesignArea() {
        paneDesignArea = new DesignArea();
        return paneDesignArea;
    }

    private DesignControls initDesignControls() {
        paneDesignControls = new DesignControls();
        return paneDesignControls;
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
    public MenuItem getMenuProviderAmazon() {
        return this.menuProviderAmazon;
    }
    public MenuItem getMenuProviderWindows() {
        return this.menuProviderWindows;
    }
    public MenuItem getMenuProviderGoogle() {
        return this.menuProviderGoogle;
    }
    public MenuItem getMenuHelpAbout() {
        return this.menuHelpAbout;
    }

    public void setTaskBarText(String msg) {
        this.taskLbl.setText(msg);
    }

    public DesignProperties getPaneDesignProperties() {
        return this.paneDesignProperties;
    }
    public DesignArea getPaneDesignArea() {
        return this.paneDesignArea;
    }
    public DesignControls getPaneDesignControls() {
        return this.paneDesignControls;
    }
}