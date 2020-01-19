package cloud.view.services;

import cloud.configuration.Config;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;

public class PaneStorageService extends PaneServiceProperties {

    private Label storageTypeLbl;
    private Label storageModeLbl;
    private Label capacityLbl;
    private Label snapshotStorageLbl;
    private Label volumeStorageLbl;
    private Label dataLbl;
    private Label requestsLbl;
    private Label queriesLbl;
    private Label rateLbl;

    private ComboBox<String> storageTypeBox;
    private ComboBox<String> storageModeBox;
    private Spinner<String> capacitySpinner;
    private Spinner<String> dataSpinner;
    private Spinner<String> requestsSpinner;
    private Spinner<String> queriesSpinner;
    private Spinner<String> rateSpinner;

    public PaneStorageService() {
        storageTypeLbl = new Label("Type:");
        storageTypeBox = new ComboBox<>(Config.getInstance().getConfigValues("storage-type"));
        storageModeLbl = new Label("Mode:");
        storageModeBox = new ComboBox<>();

        capacityLbl = new Label("Storage in GB:");
        snapshotStorageLbl = new Label("Snapshot-storage in GB:");
        volumeStorageLbl = new Label("Volume-storage in GB:");
        dataLbl = new Label("Transferred data in GB:");
        requestsLbl = new Label("# of requests:");
        queriesLbl = new Label("# of data queries:");
        rateLbl = new Label("Rate in MB/s:");

        capacitySpinner = new Spinner<>(1, 1000000, 1);
        requestsSpinner = new Spinner<>(1, 1000000, 1);
        dataSpinner = new Spinner<>(1, 1000000, 1);
        queriesSpinner = new Spinner<>(1, 1000000, 1);
        rateSpinner = new Spinner<>(1, 1000000, 1);

        add(storageTypeLbl, 0, 2);
        add(storageTypeBox, 1, 2);
        add(storageModeLbl, 0, 3);
        add(storageModeBox, 1, 3);

        storageTypeBox.getSelectionModel().selectedItemProperty().addListener((ov, oldItem, newItem) -> {
            recoverControls();
            String[] type = Config.getInstance().getConfigValuesAsArray("storage-type");
            if (newItem.equals(type[0])) {
                setObjectStorageControls();
            } else if (newItem.equals(type[1])) {
                setBlockStorageControls();
            } else if (newItem.equals(type[2])) {
                setFileStorageControls();
            }
            getScene().getWindow().sizeToScene();
        });
    }

    public String getStorageType() {
        return storageTypeBox.getEditor().getText();
    }
    public String getStorageMode() {
        return storageModeBox.getEditor().getText();
    }
    public String getCapacity() {
        return capacitySpinner.getEditor().getText();
    }
    public String getRequests() {
        return requestsSpinner.getEditor().getText();
    }
    public String getData() {
        return dataSpinner.getEditor().getText();
    }
    public String getQueries() {
        return queriesSpinner.getEditor().getText();
    }
    public String getRate() {
        return rateSpinner.getEditor().getText();
    }

    private void recoverControls() {
        getChildren().clear();
        super.setControls();
        add(storageTypeLbl, 0, 2);
        add(storageTypeBox, 1, 2);
        add(storageModeLbl, 0, 3);
        add(storageModeBox, 1, 3);
    }

    private void setObjectStorageControls() {
        this.storageModeBox.setItems(Config.getInstance().getConfigValues("storage-object-mode"));
        add(capacityLbl, 0, 4);
        add(capacitySpinner, 1, 4);
        add(requestsLbl, 0, 5);
        add(requestsSpinner, 1, 5);
        add(queriesLbl, 0, 6);
        add(queriesSpinner, 1, 6);
        add(dataLbl, 0, 7);
        add(dataSpinner, 1, 7);
        add(new Label("per month"), 2, 7);
    }

    private void setBlockStorageControls() {
        this.storageModeBox.setItems(Config.getInstance().getConfigValues("storage-block-mode"));
        add(snapshotStorageLbl, 0, 4);
        add(capacitySpinner, 1, 4);
        add(new Label("per month"), 2, 4);
        add(volumeStorageLbl, 0, 5);
        add(dataSpinner, 1, 5);
        add(new Label("per month"), 2, 5);
    }

    private void setFileStorageControls() {
        this.storageModeBox.setItems(Config.getInstance().getConfigValues("storage-file-mode"));
        add(capacityLbl, 0, 4);
        add(capacitySpinner, 1, 4);
        add(new Label("per month"), 2, 4);
        add(rateLbl, 0, 5);
        add(rateSpinner, 1, 5);
        add(dataLbl, 0, 6);
        add(dataSpinner, 1, 6);
        add(new Label("per month"), 2, 6);
    }
}
