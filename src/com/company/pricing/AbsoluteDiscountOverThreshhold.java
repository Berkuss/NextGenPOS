package com.company.pricing;

import com.company.customTypes.Money;
import com.company.domain.Sale;

public class AbsoluteDiscountOverThreshhold implements ISalePricingStrategy{
    private Money discount;
    private Money threshold;

    public AbsoluteDiscountOverThreshhold(Money discount, Money threshold) {
        this.discount = discount;
        this.threshold = threshold;
    }

    @Override
    public Money getTotal(Sale sale) {
        Money pst = sale.preDiscountTotal();
        if(pst.getAmount().compareTo(threshold.getAmount())==-1){
            return pst;
        }else {
            return pst.minus(discount);
        }
    }
}
