package com.company.domain;

import com.company.fileOperations.CostumerCatalogFileOperator;

import java.util.Hashtable;
import java.util.Map;

public class CustomerCatolog {
    private Map<String , Customer> customerCatalog;
    private CostumerCatalogFileOperator fileOperator = new CostumerCatalogFileOperator("..\\NextGenPOS\\CustomerCatalog.txt");

    public CustomerCatolog() {
        loadProdSpecs();
    }

    public Map<String, Customer> getCustomerCatalog() {
        return customerCatalog;
    }
    public Customer find(String  ID){
        if(customerCatalog.containsKey(ID)){
            return customerCatalog.get(ID);
        }
        return null;
    }
    private void loadProdSpecs(){
        fileOperator.readFromFile();
        this.customerCatalog = fileOperator.getCustomerMap();
    }
}
