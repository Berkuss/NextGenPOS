package com.company.pricing;

import com.company.customTypes.Money;
import com.company.domain.DiscountedProducts;
import com.company.domain.Sale;
import com.company.domain.SalesLineItem;

import javax.xml.parsers.SAXParser;
import java.util.Calendar;
import java.util.Iterator;


public class PricingStrategyFactory {
    private static PricingStrategyFactory pricingStrategyFactory;
    private static final double SENIORPERCENT = 0.2;
    private static final double NoDISCOUNT =  0; //indirim yok .
    private static final double MONDAYPERCENT = 0.05;
    private static final double PERCENTAGEDISCOUNT = 0.15;
    private static final Money THRESHOLD = new Money(400);
    private static final Money ABSOLUTEDISCOUNT = new Money(50);
    private DiscountedProducts discountedProducts = new DiscountedProducts();
    private static final String Darjeelingtea = "Darjeelingtea";
    private static final double TEADISCOUNTPERTENGE = 0.15;

       public static PricingStrategyFactory getInstance() {
        if (pricingStrategyFactory == null){
            pricingStrategyFactory = new PricingStrategyFactory();
        }
        return pricingStrategyFactory;
    }

    public void getSalePricingStrategy(Sale sale){

        sale.getPricingStrategy().add(getSeniorPricingStrategy(sale));
        sale.getPricingStrategy().add(getMondayPercentDiscountStrategy());
        sale.getPricingStrategy().add(getAbsoluteDiscountOverThresholdStrategy());
        sale.getPricingStrategy().add(getSLIDDiscountPricingStrategy());
        sale.getPricingStrategy().add(getPercentageDiscountOverThreshold());
        sale.getPricingStrategy().add(getDarjeelingteagediscountoffofeverything(sale));
    }


    public ISalePricingStrategy getSeniorPricingStrategy(Sale sale){
        if (sale.isConsumerSenior()){
            return (new PercentDiscountPricingStrategy(SENIORPERCENT));
        }
        else{
            return (new PercentDiscountPricingStrategy(NoDISCOUNT));
        }
    }

    public ISalePricingStrategy getMondayPercentDiscountStrategy(){
        Calendar cal = Calendar.getInstance();
        if(cal.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY){
            return (new PercentDiscountPricingStrategy(MONDAYPERCENT));
        }
        else {
            return new PercentDiscountPricingStrategy(NoDISCOUNT);
        }
    }

    public  ISalePricingStrategy getAbsoluteDiscountOverThresholdStrategy(){
        return (new AbsoluteDiscountOverThreshhold(ABSOLUTEDISCOUNT,THRESHOLD));
    }

    public ISalePricingStrategy getSLIDDiscountPricingStrategy(){
        return new SLIDiscountPricingStrategy(discountedProducts);
    }

    public void addCostumerPricingStrategy(Sale sale){
        sale.setPricingStrategy(new CompositeForAllStrategy());
    }

    public ISalePricingStrategy getPercentageDiscountOverThreshold(){
        return new PercentageDiscountOverThreshold(PERCENTAGEDISCOUNT, THRESHOLD);
    }

    public ISalePricingStrategy getDarjeelingteagediscountoffofeverything(Sale sale){
           for(Iterator<SalesLineItem> i = sale.getLineItems() ; i.hasNext();){
               SalesLineItem item = i.next();
               if (item.getDescription().getDescription().equals(Darjeelingtea)){
                   return new PercentDiscountPricingStrategy(TEADISCOUNTPERTENGE);
               }
           }
           return new PercentDiscountPricingStrategy(NoDISCOUNT);
    }

}
