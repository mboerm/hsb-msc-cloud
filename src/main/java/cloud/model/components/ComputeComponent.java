package cloud.model.components;

public class ComputeComponent extends Component {

    // TODO: Parameter für Compute hinzufügen

    private String computeType;

    public ComputeComponent(
            String name,
            String type
    ) {
        setName(name);
        setCategory("Compute");
        setComputeType(type);
        setRegion("");
        setUsageType("");
        setUsagePeriod("");
        setUsagePrepay("");
        setOpMode("");
    }

    public String getComputeType() {
        return computeType;
    }
    public void setComputeType(String computeType) {
        this.computeType = computeType;
    }
}
