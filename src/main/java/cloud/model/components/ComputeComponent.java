package cloud.model.components;

import static cloud.constants.Consts.*;

public class ComputeComponent extends Component {

    // TODO: Parameter für Compute hinzufügen

    private String computeInstanceType;

    public ComputeComponent() {
        setCategory("Compute");
        setComputeInstanceType("General");
    }

    public String getComputeInstanceType() {
        return computeInstanceType;
    }
    public void setComputeInstanceType(String computeInstanceType) {
        this.computeInstanceType = computeInstanceType;
    }
}
