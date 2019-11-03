package cloud.components;

import static cloud.constants.Consts.*;

public class VirtualMachine extends Component {

    // TODO: Parameter für VMs hinzufügen

    private INSTANCE_TYPES instance;

    public VirtualMachine() {
        setCategory(COMPONENT_CATEGORIES.VM);
        setInstanceType(INSTANCE_TYPES.NORMAL);
    }

    public INSTANCE_TYPES getInstanceType() {
        return this.instance;
    }
    public void setInstanceType(INSTANCE_TYPES instance) {
        this.instance = instance;
    }
}
