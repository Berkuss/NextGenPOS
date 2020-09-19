package com.company.domain;

import com.company.customTypes.CurrencyException;
import com.company.customTypes.Money;
import com.company.pricing.CompositeForAllStrategy;
import com.company.pricing.ISalePricingStrategy;
import com.company.pricing.PricingStrategyFactory;
import com.company.pricing.SLIDiscountPricingStrategy;

import java.util.*;

public class Sale {
    private List<SalesLineItem> lineItems = new ArrayList<>();
    private Date date = new Date();
    private boolean isComplete = false;
    private Payment payment;
    private final Tax tax = new Tax();
    private Customer customer;
    private CompositeForAllStrategy pricingStrategy ;

    public Money getBalance() {
        return payment.getAmount().minus(getTotal());
    }

    public void becomeComplete() {
        isComplete = true;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public String makeLineItem(ProductDescription desc, int quantity) throws CurrencyException {
        SalesLineItem sl = new SalesLineItem(desc, quantity);
        String slString=sl.toString();
        if (lineItems.size() > 0) { //check if there is any product in the list
            Currency firstItemCurrency =  lineItems.get(0).getSubTotal().getCurrency();
            if (firstItemCurrency == desc.getPrice().getCurrency()) {//check to see if currencies are the same
                lineItems.add(sl);
            } else {
                throw new CurrencyException();
            }
        } else {
            lineItems.add(sl);
        }
        return slString;
    }
    public Money preDiscountTotal(){
        Money total = new Money();
        Money subTotal = null;

        for (SalesLineItem lineItem : lineItems) {
            subTotal = lineItem.getSubTotal();
            total = total.add(subTotal);
        }
        return total;
    }

    public Money getTotal() {
        Money total = new Money();

        PricingStrategyFactory.getInstance().addCostumerPricingStrategy(this);
        PricingStrategyFactory.getInstance().getSalePricingStrategy(this);
        total = pricingStrategy.getTotal(this);
        //Tax calculation
        tax.setTaxAmount(total);
        total = total.add(tax.getTaxAmount());

        return total;
    }

    public void makePayment(Money cashTendered) {
        payment = new Payment(cashTendered);
    }


    @Override
    public String toString() {
        Money totalWithoutTax = new Money();
        Money subTotal = null;
        String s = "Date: " + date.toString() + "\n";
        Iterator i = lineItems.iterator();
        while (i.hasNext()) {
            SalesLineItem sli = (SalesLineItem) i.next();
            s += sli.toString();
            subTotal = sli.getSubTotal();
            totalWithoutTax = totalWithoutTax.add(subTotal);
        }
        s += "\n";

        s += "Total (without tax and discount): " + totalWithoutTax + "\n";
        s += "Total: " + getTotal() + "\n";
        s += "(Tax Amount:" + tax.getTaxAmount()+ ")\n";

        if(payment!=null){
            s += "Payment: " + payment.toString() + "\n";
            s += "Balance: " + getBalance() + "\n";
        }
        s += "--------------------------------------\n";
        return s;
    }

    public Iterator<SalesLineItem> getLineItems() {
        return lineItems.iterator();
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }


    public boolean isConsumerSenior(){
        return this.customer.isSenior();
    }

    public CompositeForAllStrategy getPricingStrategy() {
        return pricingStrategy;
    }

    public void setPricingStrategy(CompositeForAllStrategy pricingStrategy) {
        this.pricingStrategy = pricingStrategy;
    }
}
