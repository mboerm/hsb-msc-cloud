package cloud.model;

import static cloud.constants.Consts.*;

public class ComputeComponent extends Component {

    // TODO: Parameter für Compute hinzufügen

    private COMPUTE_INSTANCE_TYPE computeInstanceType;

    public ComputeComponent() {
        setCategory(COMPONENT_CATEGORY.Compute);
        setComputeInstanceType(COMPUTE_INSTANCE_TYPE.General);
    }

    public COMPUTE_INSTANCE_TYPE getComputeInstanceType() {
        return computeInstanceType;
    }
    public void setComputeInstanceType(COMPUTE_INSTANCE_TYPE computeInstanceType) {
        this.computeInstanceType = computeInstanceType;
    }
}
