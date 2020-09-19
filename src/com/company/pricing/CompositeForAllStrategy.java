package com.company.pricing;

import com.company.customTypes.Money;
import com.company.domain.Sale;

import java.util.Iterator;

public class CompositeForAllStrategy extends CompositePricingStrategy{

    @Override
    public Money getTotal(Sale sale) {

        Money m1 = new Money(0);
        Money total = sale.preDiscountTotal();
        for(Iterator<ISalePricingStrategy> i = strategies.iterator();i.hasNext();){
            m1 =m1.add(total.minus(i.next().getTotal(sale)));
        }
        return total.minus(m1);
    }
    public void add(ISalePricingStrategy salePricingStrategy){
        super.add(salePricingStrategy);
    }
}
