package cloud.model.pricing;

import java.text.DecimalFormat;

public class Costs {
    private double price;
    private double scaledPrice;

    private static DecimalFormat df2 = new DecimalFormat("#.00");

    public Costs() {
        setPrice(0.0);
        setScaledPrice(0.0);
    }

    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = Double.parseDouble(df2.format(price));
    }

    public Double getScaledPrice() {
        return scaledPrice;
    }
    public void setScaledPrice(Double scaledPrice) {
        this.scaledPrice = Double.parseDouble(df2.format(scaledPrice));
    }
}
