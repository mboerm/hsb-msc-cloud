package cloud.view.services;

import cloud.configuration.Config;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;

public class ComputeServicePane extends ServicePropertiesPane {

    private Label computeTypeLbl;

    private ComboBox<String> computeTypeBox;
    private ComboBox<String> instanceTypeBox;
    private ComboBox<String> instanceSizeBox;
    private ComboBox<String> computeInstanceBox;
    private ComboBox<String> storageInstanceBox;
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
        computeInstanceBox = new ComboBox<>();
        storageInstanceBox = new ComboBox<>();

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
    public String getComputeInstanceRef() {return this.computeInstanceBox.getValue();}
    public void setComputeInstanceRef(String item) {this.computeInstanceBox.getSelectionModel().select(item);}
    public String getStorageInstanceRef() {return this.storageInstanceBox.getValue();}
    public void setStorageInstanceRef(String item) {this.storageInstanceBox.getSelectionModel().select(item);}
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

    private void recoverControls() {
        getChildren().clear();
        super.setControls();
        add(computeTypeLbl, 0, 2);
        add(computeTypeBox, 1, 2);
    }

    private void setVMControls() {
        add(new Label("Instance type:"), 0, 3);
        add(instanceTypeBox, 1, 3);
        add(new Label("Instance size:"), 0, 4);
        add(instanceSizeBox, 1, 4);
        add(new Label("Operating system:"), 0, 5);
        systemBox.setItems(Config.getInstance().getConfigValues("compute-os"));
        add(systemBox, 1, 5);
    }

    private void setContainerControls() {
		add(new Label("Container type:"), 0, 3);
		systemBox.setItems(Config.getInstance().getConfigValues("compute-container-type"));
		add(systemBox, 1, 3);
        add(new Label("# of vCPU:"), 0, 4);
        add(cpuSpinner, 1, 4);
        add(new Label("# of storage hours:"), 0, 5);
        add(storageSpinner, 1, 5);
        add(new Label("# of compute instances:"), 0, 6);
        add(numOneSpinner, 1, 6);
        add(new Label("# of container groups:"), 0, 7);
        add(numTwoSpinner, 1, 7);
        add(new Label("Transferred data in GB:"), 0, 8);
        add(dataSpinner, 1, 8);
    }

    private void setAppControls() {
        add(new Label("Language"), 0, 3);
        systemBox.setItems(Config.getInstance().getConfigValues("compute-app-language"));
        add(systemBox, 1, 3);
        add(new Label("Compute instance reference:"), 0, 4);
        add(computeInstanceBox, 1, 4);
        add(new Label("Storage instance reference:"), 0, 5);
        add(storageInstanceBox, 1, 5);
    }

    private void setBatchControls() {
        add(new Label("Compute instance reference:"), 0, 3);
        add(computeInstanceBox, 1, 3);
    }

    private void setCodeControls() {
        add(new Label("CPU:"), 0, 3);
        add(cpuSpinner, 1, 3);
        add(new Label("RAM:"), 0, 4);
        add(storageSpinner, 1, 4);
        add(new Label("# of invocations:"), 0, 5);
        add(numOneSpinner, 1, 5);
        add(new Label("per month"), 2, 5);
        add(new Label("Duration per invocation:"), 0, 6);
        add(numTwoSpinner, 1, 6);
    }

    private void setBalancerControls() {
        add(new Label("Compute instance reference:"), 0, 3);
        add(computeInstanceBox, 1, 3);
        add(new Label("Balancer type:"), 0, 4);
        systemBox.setItems(Config.getInstance().getConfigValues("compute-balancer-type"));
        add(systemBox, 1, 4);
        add(new Label("Transferred data in GB:"), 0, 5);
        add(dataSpinner, 1, 5);
        add(new Label("# of rules:"), 0, 6);
        add(numOneSpinner, 1, 6);
    }
}
