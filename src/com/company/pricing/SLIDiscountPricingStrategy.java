package com.company.pricing;

import com.company.customTypes.Money;
import com.company.domain.DiscountedProducts;
import com.company.domain.Sale;
import com.company.domain.SalesLineItem;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;

public class SLIDiscountPricingStrategy implements ISalePricingStrategy {
    private DiscountedProducts discountedProducts;

    public SLIDiscountPricingStrategy(DiscountedProducts discountedProducts) {
        this.discountedProducts = discountedProducts;//Dosyadan alıncak
    }

    @Override
    public Money getTotal(Sale sale) {
        SalesLineItem item ;
        Money money = new Money(0);

        Map<String ,Double> table = discountedProducts.getDiProductDescription();
        for(Iterator<SalesLineItem> i = sale.getLineItems();i.hasNext();){
            item = i.next();
            Money m1 ;

            if(table.containsKey(item.getDescription().getDescription())){
                double percentage = table.get(item.getDescription().getDescription());

                m1 = item.getSubTotal().times(percentage);
                money = money.add(m1);
            }
        }
        return sale.preDiscountTotal().minus(money);
    }


}
