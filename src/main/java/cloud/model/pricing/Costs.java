package cloud.model.pricing;

import java.text.DecimalFormat;

public class Costs {
    private Double price;
    private Double scaledPrice;

    private static DecimalFormat df2 = new DecimalFormat("#.00");

    public Costs() {
        setPrice(0.0);
        setScaledPrice(0.0);
    }

    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = Double.valueOf(df2.format(price));
    }

    public Double getScaledPrice() {
        return scaledPrice;
    }
    public void setScaledPrice(Double scaledPrice) {
        this.scaledPrice = Double.valueOf(df2.format(scaledPrice));
    }
}
