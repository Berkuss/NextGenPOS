package com.company.pricing;

import com.company.customTypes.Money;
import com.company.domain.Sale;

public interface ISalePricingStrategy {
    public Money getTotal(Sale sale);
}
