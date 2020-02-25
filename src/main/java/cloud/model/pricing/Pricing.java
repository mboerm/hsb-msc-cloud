package cloud.model.pricing;

public interface Pricing {
    void calculateCosts();
    void calculateStaticCosts();
    void optimizeCosts();
}
