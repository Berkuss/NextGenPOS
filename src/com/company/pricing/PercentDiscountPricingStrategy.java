package com.company.pricing;

import com.company.customTypes.Money;
import com.company.domain.Sale;

public class PercentDiscountPricingStrategy implements ISalePricingStrategy{
    private double percentage;

    public PercentDiscountPricingStrategy(double percentage) {
        this.percentage = percentage;
    }

    @Override
    public Money getTotal(Sale sale) {
        return sale.preDiscountTotal().minus(sale.preDiscountTotal().times(percentage));
    }
}
