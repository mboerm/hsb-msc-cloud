package cloud.view.services;

import cloud.configuration.Config;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.util.Pair;

public class StorageServicePane extends ServicePropertiesPane {

    private Label storageTypeLbl;
    private Label storageModeLbl;

    private ComboBox<String> storageTypeBox;
    private ComboBox<String> storageModeBox;
    private Spinner<Integer> capacitySpinner;
    private Spinner<Integer> dataSpinner;
    private Spinner<Integer> requestsReadSpinner;
    private Spinner<Integer> requestsWriteSpinner;
    private Spinner<Integer> queriesSpinner;
    private Spinner<Integer> rateSpinner;

    public StorageServicePane() {
        storageTypeLbl = new Label("Type:");
        storageTypeBox = new ComboBox<>(Config.getInstance().getConfigValues("storage-type"));
        storageModeLbl = new Label("Mode:");
        storageModeBox = new ComboBox<>();

        capacitySpinner = new Spinner<>(1, 1000000, 1);
        requestsReadSpinner = new Spinner<>(1, 1000000, 1);
        requestsWriteSpinner = new Spinner<>(1, 1000000, 1);
        dataSpinner = new Spinner<>(1, 1000000, 1);
        queriesSpinner = new Spinner<>(1, 1000000, 1);
        rateSpinner = new Spinner<>(1, 1000000, 1);

        capacitySpinner.setEditable(true);
        requestsReadSpinner.setEditable(true);
        requestsWriteSpinner.setEditable(true);
        dataSpinner.setEditable(true);
        queriesSpinner.setEditable(true);
        rateSpinner.setEditable(true);

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
        return storageTypeBox.getValue();
    }
    public void setStorageType(String item) {this.storageTypeBox.getSelectionModel().select(item);}
    public String getStorageMode() {
        return storageModeBox.getValue();
    }
    public void setStorageMode(String item) {this.storageModeBox.getSelectionModel().select(item);}
    public Integer getCapacity() {
        return capacitySpinner.getValue();
    }
    public void setCapacity(Integer value) {this.capacitySpinner.getValueFactory().setValue(value);}
    public Pair<Integer,Integer> getRequests() {return new Pair<>(
        this.requestsReadSpinner.getValue(),
        this.requestsWriteSpinner.getValue());
    }
    public void setRequests(Pair<Integer,Integer> values) {
        this.requestsReadSpinner.getValueFactory().setValue(values.getKey());
        this.requestsWriteSpinner.getValueFactory().setValue(values.getValue());
    }
    public Integer getData() {
        return dataSpinner.getValue();
    }
    public void setData(Integer value) {this.dataSpinner.getValueFactory().setValue(value);}
    public Integer getQueries() {
        return queriesSpinner.getValue();
    }
    public void setQueries(Integer value) {this.queriesSpinner.getValueFactory().setValue(value);}
    public Integer getRate() {
        return rateSpinner.getValue();
    }
    public void setRate(Integer value) {this.rateSpinner.getValueFactory().setValue(value);}

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
        add(new Label("Storage in TB:"), 0, 4);
        add(capacitySpinner, 1, 4);
        add(new Label("per month"), 2, 4);
        add(new Label("# of read requests:"), 0, 5);
        add(requestsReadSpinner, 1, 5);
        add(new Label("# of write requests:"), 0, 6);
        add(requestsWriteSpinner, 1, 6);
        add(new Label("# of data queries:"), 0, 7);
        add(queriesSpinner, 1, 7);
        add(new Label("Transferred data in GB:"), 0, 8);
        add(dataSpinner, 1, 8);
        add(new Label("per month"), 2, 8);
    }

    private void setBlockStorageControls() {
        this.storageModeBox.setItems(Config.getInstance().getConfigValues("storage-block-mode"));
        add(new Label("Snapshot-storage in GB:"), 0, 4);
        add(capacitySpinner, 1, 4);
        add(new Label("per month"), 2, 4);
        add(new Label("Volume-storage in GB:"), 0, 5);
        add(dataSpinner, 1, 5);
        add(new Label("per month"), 2, 5);
    }

    private void setFileStorageControls() {
        this.storageModeBox.setItems(Config.getInstance().getConfigValues("storage-file-mode"));
        add(new Label("Storage in GB:"), 0, 4);
        add(capacitySpinner, 1, 4);
        add(new Label("per month"), 2, 4);
        add(new Label("Rate in MB/s:"), 0, 5);
        add(rateSpinner, 1, 5);
        add(new Label("Transferred data in GB:"), 0, 6);
        add(dataSpinner, 1, 6);
        add(new Label("per month"), 2, 6);
    }
}
