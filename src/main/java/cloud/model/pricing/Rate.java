package cloud.model.pricing;

public class Rate {
    private Double originalPrice;
    private Double scaledPrice;

    public Rate(Double originalPrice) {
        setOriginalPrice(originalPrice);
        setScaledPrice(0.0);
    }

    public Double getOriginalPrice() {
        return originalPrice;
    }
    public void setOriginalPrice(Double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public Double getScaledPrice() {
        return scaledPrice;
    }
    public void setScaledPrice(Double scaledPrice) {
        this.scaledPrice = scaledPrice;
    }
}
