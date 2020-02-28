package cloud.main;

import javafx.stage.Stage;

/**
 * Stage manager class
 */
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

    /**
     * Get primary stage
     * @return primaryStage
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    /**
     * Set primary stage
     * @param primaryStage Stage
     */
    public void setPrimaryStage(Stage primaryStage) {
        StageManager.primaryStage = primaryStage;
    }
}