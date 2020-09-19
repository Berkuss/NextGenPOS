package com.company.pricing;

import com.company.customTypes.Money;
import com.company.domain.Sale;

import java.util.ArrayList;
import java.util.List;

public abstract class CompositePricingStrategy implements ISalePricingStrategy {
    protected List<ISalePricingStrategy> strategies;

    public CompositePricingStrategy() {
        this.strategies = new ArrayList<>();
    }
    public void add(ISalePricingStrategy s){
        this.strategies.add(s);
    }


    @Override
    public abstract Money getTotal(Sale sale);
}
