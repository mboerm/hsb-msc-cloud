package cloud.model.pricing;

public interface IPricing {
    void calculateCosts();
    void calculateStaticCosts();
    void optimizeCosts();
}
