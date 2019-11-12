package cloud.model;

import cloud.model.Component;

import static cloud.constants.Consts.*;

public class VMComponent extends Component {

    // TODO: Parameter für VMs hinzufügen

    private INSTANCE_TYPES instance;

    public VMComponent() {
        setCategory(COMPONENT_CATEGORIES.VM);
        setInstanceType(INSTANCE_TYPES.Normal);
    }

    public INSTANCE_TYPES getInstanceType() {
        return this.instance;
    }
    public void setInstanceType(INSTANCE_TYPES instance) {
        this.instance = instance;
    }
}
