package cloud.view.services;

import cloud.configuration.Config;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.util.Pair;

/**
 * Storage service panel
 */
public class StorageServicePane extends ServicePropertiesPane {

    private Label storageTypeLbl;
    private Label storageModeLbl;
    private String[] labels;

    private ComboBox<String> storageTypeBox;
    private ComboBox<String> storageModeBox;
    private Spinner<Integer> capacitySpinner;
    private Spinner<Integer> dataSpinner;
    private Spinner<Integer> requestsReadSpinner;
    private Spinner<Integer> requestsWriteSpinner;
    private Spinner<Integer> rateSpinner;

    /**
     * Constructor
     */
    public StorageServicePane() {
        storageTypeLbl = new Label("Type:");
        storageTypeBox = new ComboBox<>(Config.getInstance().getConfigValues("storage-type"));
        storageModeLbl = new Label("Mode:");
        storageModeBox = new ComboBox<>();

        capacitySpinner = new Spinner<>(1, 1000000, 1);
        requestsReadSpinner = new Spinner<>(1, 1000000, 1);
        requestsWriteSpinner = new Spinner<>(1, 1000000, 1);
        dataSpinner = new Spinner<>(1, 1000000, 1);
        rateSpinner = new Spinner<>(1, 1000000, 1);

        capacitySpinner.setEditable(true);
        requestsReadSpinner.setEditable(true);
        requestsWriteSpinner.setEditable(true);
        dataSpinner.setEditable(true);
        rateSpinner.setEditable(true);

        add(storageTypeLbl, 0, 2);
        add(storageTypeBox, 1, 2);
        add(storageModeLbl, 0, 3);
        add(storageModeBox, 1, 3);

        /* set controls by storage type */
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
    public Integer getRate() {
        return rateSpinner.getValue();
    }
    public void setRate(Integer value) {this.rateSpinner.getValueFactory().setValue(value);}

    @Override
    void recoverControls() {
        getChildren().clear();
        super.setControls();
        add(storageTypeLbl, 0, 2);
        add(storageTypeBox, 1, 2);
        add(storageModeLbl, 0, 3);
        add(storageModeBox, 1, 3);
    }

    /* set controls for type object storage */
    private void setObjectStorageControls() {
        this.labels = Config.getInstance().getConfigValuesAsArray("storage-object-labels");
        this.storageModeBox.setItems(Config.getInstance().getConfigValues("storage-object-mode"));
        add(new Label(labels[0]), 0, 4);
        add(capacitySpinner, 1, 4);
        add(new Label("per month"), 2, 4);
        add(new Label(labels[1]), 0, 5);
        add(requestsReadSpinner, 1, 5);
        add(new Label(labels[2]), 0, 6);
        add(requestsWriteSpinner, 1, 6);
        add(new Label(labels[3]), 0, 7);
        add(dataSpinner, 1, 7);
        add(new Label("per month"), 2, 7);
    }

    /* set controls for type block storage */
    private void setBlockStorageControls() {
        this.labels = Config.getInstance().getConfigValuesAsArray("storage-block-labels");
        this.storageModeBox.setItems(Config.getInstance().getConfigValues("storage-block-mode"));
        add(new Label(labels[0]), 0, 4);
        add(capacitySpinner, 1, 4);
        add(new Label("per month"), 2, 4);
        add(new Label(labels[1]), 0, 5);
        add(dataSpinner, 1, 5);
        add(new Label("per month"), 2, 5);
    }

    /* set controls for type file storage */
    private void setFileStorageControls() {
        this.labels = Config.getInstance().getConfigValuesAsArray("storage-file-labels");
        this.storageModeBox.setItems(Config.getInstance().getConfigValues("storage-file-mode"));
        add(new Label(labels[0]), 0, 4);
        add(capacitySpinner, 1, 4);
        add(new Label("per month"), 2, 4);
        add(new Label(labels[1]), 0, 5);
        add(rateSpinner, 1, 5);
        add(new Label(labels[2]), 0, 6);
        add(dataSpinner, 1, 6);
        add(new Label("per month"), 2, 6);
    }
}
