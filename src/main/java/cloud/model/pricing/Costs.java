package cloud.model.pricing;

import cloud.configuration.Constants;

/**
 * Costs class
 */
public class Costs {
    /* calculated price */
    private double price;
    /* scaled price */
    private double scaledPrice;
    /* string formula for price calculation */
    private String formula;

    public Costs() {
        setPrice(0);
        setScaledPrice(0);
        setFormula("");
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

    public String getFormula() {
        return formula;
    }
    public void setFormula(String formula) {
        this.formula = formula;
    }
}
