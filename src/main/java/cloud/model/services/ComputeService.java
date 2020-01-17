package cloud.model.services;

class ComputeService extends Service {

    private String computeType;

    public ComputeService() {
        setCategory("Compute");
    }

    public String getComputeType() {
        return computeType;
    }
    public void setComputeType(String computeType) {
        this.computeType = computeType;
    }
}
