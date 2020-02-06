package cloud.model;

import javafx.stage.Stage;

public class StageManager {

    private static StageManager instance;
    private static Stage primaryStage;

    /** Private constructor to prevent class to be instantiated from public */
    private StageManager() {
    }

    /** Singleton instance */
    public static StageManager getInstance() {
        if (instance == null) {
            instance = new StageManager();
        }
        return instance;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setPrimaryStage(Stage primaryStage) {
        StageManager.primaryStage = primaryStage;
    }
}