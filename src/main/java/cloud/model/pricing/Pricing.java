package cloud.model.pricing;

/**
 * Pricing interface
 */
public interface Pricing {
    /* api cost calculation method */
    void calculateCosts();

    /* static cost calculation method */
    void calculateStaticCosts();

    /* optimize costs method */
    void optimizeCosts();
}
