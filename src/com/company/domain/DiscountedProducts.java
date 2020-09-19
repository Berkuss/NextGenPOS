package com.company.domain;

import com.company.fileOperations.DiscountedProductFileOperator;

import java.util.Hashtable;
import java.util.Map;

public class DiscountedProducts {
    private Map<String, Double> diProductDescription;
    private DiscountedProductFileOperator fileOperator = new DiscountedProductFileOperator("..\\NextGenPOS\\DiscountedProduct.txt");

    public DiscountedProducts() { loadProdSpecs();
    }

    public Map<String, Double> getDiProductDescription() {
        return diProductDescription;
    }
    private void loadProdSpecs(){
        fileOperator.readFromFile();
        this.diProductDescription = fileOperator.getStringDoubleMap();
    }
}
