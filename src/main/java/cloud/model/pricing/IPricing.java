package cloud.model.pricing;

public interface IPricing {

    int MONTH_SECONDS = 2628000;
    int MONTH_HOURS = 730;
    int DATA_FACTOR = 1024;
    double K_FACTOR = Math.pow(10,3);

    void calculateCosts();
    void optimizeCosts();
}
