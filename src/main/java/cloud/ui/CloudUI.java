package cloud.ui;

import cloud.constants.Consts;
import cloud.ui.designs.DesignArea;
import cloud.ui.designs.DesignControls;
import cloud.ui.designs.DesignProperties;
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
        MenuItem menuFileNew = new MenuItem("New Session");
        MenuItem menuFileOpen = new MenuItem("Open Session");
        MenuItem menuFileSave = new MenuItem("Save Session");
        MenuItem menuFileExit = new MenuItem("Exit");

        menuFile.getItems().addAll(menuFileNew, menuFileOpen, menuFileSave, new SeparatorMenuItem(), menuFileExit);

        menuFileNew.setOnAction(actionEvent -> {

        });

        menuFileOpen.setOnAction(actionEvent -> {
            FileChooser fileChooser = new FileChooser();
            File selectedFile = fileChooser.showOpenDialog(stage);
        });

        menuFileSave.setOnAction(actionEvent -> {

        });

        menuFileExit.setOnAction(actionEvent -> System.exit(0));

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
        MenuItem menuDesignAnalyse = new MenuItem("Analyse Design");
        MenuItem menuDesignOptimize = new MenuItem("Optimize Design");
        MenuItem menuDesignReset = new MenuItem("Reset Design");

        menuDesign.getItems().addAll(menuDesignAnalyse, menuDesignOptimize, menuDesignReset);

        return menuDesign;
    }

    private Menu initMenuServices() {
        Menu menuServices = new Menu("Services");
        MenuItem menuServicesAmazon = new MenuItem("Amazon Web Services");
        MenuItem menuServicesWindows = new MenuItem("Windows Azure");
        MenuItem menuServicesGoogle = new MenuItem("Google Cloud Platform");
        menuServicesWindows.setDisable(true);
        menuServicesGoogle.setDisable(true);

        menuServices.getItems().addAll(menuServicesAmazon, menuServicesWindows, menuServicesGoogle);

        return menuServices;
    }

    private Menu initMenuHelp() {
        Menu menuHelp = new Menu("Help");
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

        Label taskLbl = new Label("Init");
        taskLbl.setTextAlignment(TextAlignment.RIGHT);
        taskLbl.setWrapText(true);

        taskBox.setAlignment(Pos.CENTER_RIGHT);
        taskBox.getChildren().add(taskLbl);

        return taskBox;
    }
}