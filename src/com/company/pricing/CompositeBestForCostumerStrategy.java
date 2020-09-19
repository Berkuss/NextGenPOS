package com.company.pricing;

import com.company.customTypes.Money;
import com.company.domain.Sale;

import java.util.Iterator;

public class CompositeBestForCostumerStrategy extends CompositePricingStrategy{

    @Override
    public Money getTotal(Sale sale) {
        Money lowets = new Money(Integer.MAX_VALUE);

        for(Iterator<ISalePricingStrategy> i =strategies.iterator();i.hasNext();){
            ISalePricingStrategy strategy =i.next();
            Money total = strategy.getTotal(sale);
            lowets = total.min(lowets);
        }
        return lowets;
    }
}
