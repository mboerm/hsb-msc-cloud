package cloud.components;

public class VirtualMachine extends Component {

    // TODO: Parameter für VMs hinzufügen

    private enum instanceType {NORMAL, SPOT, RESERVED};
    private instanceType instance;

    public VirtualMachine() {
        setCategory(componentCategory.VM);
        setInstanceType(instanceType.NORMAL);
    }

    public instanceType getInstanceType() {
        return this.instance;
    }
    public void setInstanceType(instanceType instance) {
        this.instance = instance;
    }
}
