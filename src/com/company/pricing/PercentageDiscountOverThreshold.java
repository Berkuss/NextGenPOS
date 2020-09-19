package com.company.pricing;

import com.company.customTypes.Money;
import com.company.domain.Sale;

public class PercentageDiscountOverThreshold implements ISalePricingStrategy {
    private double percentage;
    private Money threshold;

    public PercentageDiscountOverThreshold(double percentage, Money threshold) {
        this.percentage = percentage;
        this.threshold = threshold;
    }

    @Override
    public Money getTotal(Sale sale) {
        Money pst = sale.preDiscountTotal();
        if(pst.getAmount().compareTo(threshold.getAmount())==-1){
            return pst;
        }else {
            return pst.minus(pst.times(percentage));
        }
    }
}
