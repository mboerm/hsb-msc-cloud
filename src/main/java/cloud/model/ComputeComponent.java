package cloud.model;

import cloud.model.Component;

import static cloud.constants.Consts.*;

public class ComputeComponent extends Component {

    // TODO: Parameter für VMs hinzufügen

    private INSTANCE_TYPES instance;

    public ComputeComponent() {
        setCategory(COMPONENT_CATEGORIES.Compute);
        setInstanceType(INSTANCE_TYPES.Normal);
    }

    public INSTANCE_TYPES getInstanceType() {
        return this.instance;
    }
    public void setInstanceType(INSTANCE_TYPES instance) {
        this.instance = instance;
    }
}
