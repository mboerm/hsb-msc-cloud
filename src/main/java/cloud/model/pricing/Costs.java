package cloud.model.pricing;

import cloud.configuration.Constants;

public class Costs {
    private double price;
    private double scaledPrice;

    public Costs() {
        setPrice(0.0);
        setScaledPrice(0.0);
    }

    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = Double.parseDouble(Constants.DOUBLE_FORMAT_2.format(price));
    }

    public double getScaledPrice() {
        return scaledPrice;
    }
    public void setScaledPrice(double scaledPrice) {
        this.scaledPrice = Double.parseDouble(Constants.DOUBLE_FORMAT_2.format(scaledPrice));
    }
}
