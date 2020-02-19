package cloud.view.services;

import cloud.configuration.Config;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;

public class ComputeServicePane extends ServicePropertiesPane {

    private Label computeTypeLbl;
    private String[] labels;

    private ComboBox<String> computeTypeBox;
    private ComboBox<String> instanceTypeBox;
    private ComboBox<String> instanceSizeBox;
    private TextField computeInstanceField;
    private TextField storageInstanceField;
    private ComboBox<String> systemBox;
    private Spinner<Integer> cpuSpinner;
    private Spinner<Integer> storageSpinner;
    private Spinner<Integer> dataSpinner;
    private Spinner<Integer> numOneSpinner;
    private Spinner<Integer> numTwoSpinner;

    public ComputeServicePane() {
        computeTypeLbl = new Label("Type:");
        computeTypeBox = new ComboBox<>(Config.getInstance().getConfigValues("compute-type"));
        instanceTypeBox = new ComboBox<>(Config.getInstance().getConfigValues("compute-instance-type"));
        instanceSizeBox = new ComboBox<>(Config.getInstance().getConfigValues("service-instance-size"));
        systemBox = new ComboBox<>();
        computeInstanceField = new TextField();
        storageInstanceField = new TextField();

        cpuSpinner = new Spinner<>(1, 1000000, 1);
        storageSpinner = new Spinner<>(1, 1000000, 1);
        dataSpinner = new Spinner<>(1, 1000000, 1);
        numOneSpinner = new Spinner<>(1, 1000000, 1);
        numTwoSpinner = new Spinner<>(1, 1000000, 1);

        cpuSpinner.setEditable(true);
        storageSpinner.setEditable(true);
        dataSpinner.setEditable(true);
        numOneSpinner.setEditable(true);
        numTwoSpinner.setEditable(true);

        add(computeTypeLbl, 0, 2);
        add(computeTypeBox, 1, 2);

        computeTypeBox.getSelectionModel().selectedItemProperty().addListener((ov, oldItem, newItem) -> {
            recoverControls();
            String[] type = Config.getInstance().getConfigValuesAsArray("compute-type");
            if (newItem.equals(type[0])) {
                setVMControls();
            } else if (newItem.equals(type[1])) {
                setContainerControls();
            } else if (newItem.equals(type[2])) {
                setAppControls();
            } else if (newItem.equals(type[3])) {
                setBatchControls();
            } else if (newItem.equals(type[4])) {
                setCodeControls();
            } else if (newItem.equals(type[5])) {
                setBalancerControls();
            }
            getScene().getWindow().sizeToScene();
        });
    }

    public String getComputeType() {return this.computeTypeBox.getValue();}
    public void setComputeType(String item) {this.computeTypeBox.getSelectionModel().select(item);}
    public String getInstanceType() {return this.instanceTypeBox.getValue();}
    public void setInstanceType(String item) {this.instanceTypeBox.getSelectionModel().select(item);}
    public String getInstanceSize() {return this.instanceSizeBox.getValue();}
    public void setInstanceSize(String item) {this.instanceSizeBox.getSelectionModel().select(item);}
    public String getComputeInstanceRef() {return this.computeInstanceField.getText();}
    public void setComputeInstanceRef(String item) {this.computeInstanceField.setText(item);}
    public String getStorageInstanceRef() {return this.storageInstanceField.getText();}
    public void setStorageInstanceRef(String item) {this.storageInstanceField.setText(item);}
    public String getSystem() {return this.systemBox.getValue();}
    public void setSystem(String item) {this.systemBox.getSelectionModel().select(item);}
    public Integer getCPU() {return this.cpuSpinner.getValue();}
    public void setCPU(Integer value) {this.cpuSpinner.getValueFactory().setValue(value);}
    public Integer getStorage() {return this.storageSpinner.getValue();}
    public void setStorage(Integer value) {this.storageSpinner.getValueFactory().setValue(value);}
    public Integer getData() {return this.dataSpinner.getValue();}
    public void setData(Integer value) {this.dataSpinner.getValueFactory().setValue(value);}
    public Integer getNumOne() {return this.numOneSpinner.getValue();}
    public void setNumOne(Integer value) {this.numOneSpinner.getValueFactory().setValue(value);}
    public Integer getNumTwo() {return this.numTwoSpinner.getValue();}
    public void setNumTwo(Integer value) {this.numTwoSpinner.getValueFactory().setValue(value);}

    @Override
    void recoverControls() {
        getChildren().clear();
        super.setControls();
        add(computeTypeLbl, 0, 2);
        add(computeTypeBox, 1, 2);
    }

    private void setVMControls() {
        labels = Config.getInstance().getConfigValuesAsArray("compute-vm-labels");
        add(new Label(labels[0]), 0, 3);
        add(instanceTypeBox, 1, 3);
        add(new Label(labels[1]), 0, 4);
        add(instanceSizeBox, 1, 4);
        add(new Label(labels[2]), 0, 5);
        add(numOneSpinner, 1, 5);
        add(new Label(labels[3]), 0, 6);
        add(cpuSpinner, 1, 6);
        add(new Label(labels[4]), 0, 7);
        add(storageSpinner, 1, 7);
        add(new Label(labels[5]), 0, 8);
        systemBox.setItems(Config.getInstance().getConfigValues("compute-os"));
        add(systemBox, 1, 8);
        add(new Label(labels[6]), 0, 9);
        add(dataSpinner, 1, 9);
    }

    private void setContainerControls() {
        labels = Config.getInstance().getConfigValuesAsArray("compute-container-labels");
		add(new Label(labels[0]), 0, 3);
		systemBox.setItems(Config.getInstance().getConfigValues("compute-container-type"));
		add(systemBox, 1, 3);
        add(new Label(labels[1]), 0, 4);
        add(cpuSpinner, 1, 4);
		add(new Label("per hour"), 2, 4);
        add(new Label(labels[2]), 0, 5);
        add(storageSpinner, 1, 5);
		add(new Label("per hour"), 2, 5);
        add(new Label(labels[3]), 0, 6);
        add(numOneSpinner, 1, 6);
        add(new Label(labels[4]), 0, 7);
        add(numTwoSpinner, 1, 7);
        add(new Label(labels[5]), 0, 8);
        add(dataSpinner, 1, 8);
    }

    private void setAppControls() {
        labels = Config.getInstance().getConfigValuesAsArray("compute-app-labels");
        add(new Label(labels[0]), 0, 3);
        systemBox.setItems(Config.getInstance().getConfigValues("compute-app-language"));
        add(systemBox, 1, 3);
        add(new Label(labels[1]), 0, 4);
        add(computeInstanceField, 1, 4);
        add(new Label(labels[2]), 0, 5);
        add(storageInstanceField, 1, 5);
    }

    private void setBatchControls() {
        labels = Config.getInstance().getConfigValuesAsArray("compute-batch-labels");
        add(new Label(labels[0]), 0, 3);
        add(computeInstanceField, 1, 3);
    }

    private void setCodeControls() {
        labels = Config.getInstance().getConfigValuesAsArray("compute-code-labels");
        add(new Label(labels[0]), 0, 3);
        add(numOneSpinner, 1, 3);
        add(new Label("per month"), 2, 3);
        add(new Label(labels[1]), 0, 4);
        add(numTwoSpinner, 1, 4);
        add(new Label(labels[2]), 0, 5);
        add(storageSpinner, 1, 5);
    }

    private void setBalancerControls() {
        labels = Config.getInstance().getConfigValuesAsArray("compute-balancer-labels");
        add(new Label(labels[0]), 0, 3);
        add(numOneSpinner, 1, 3);
        add(new Label(labels[1]), 0, 4);
        add(dataSpinner, 1, 4);
        add(new Label("per month"), 2, 4);
    }
}
