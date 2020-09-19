package com.company.pricing;

import com.company.customTypes.Money;
import com.company.domain.Sale;

import java.util.Iterator;

public class CompositeBestForStoreStretegy extends CompositePricingStrategy {

    @Override
    public Money getTotal(Sale sale) {
        Money highest = new Money(0.1);

        for(Iterator<ISalePricingStrategy> i = strategies.iterator(); i.hasNext();){
            ISalePricingStrategy strategy =i.next();
            Money total = strategy.getTotal(sale);
            highest = total.max(highest);
        }
        return highest;
    }
}
