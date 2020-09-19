package com.company.fileOperations;

import com.company.domain.Customer;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CostumerCatalogFileOperator {
    private String filepath ;
    private Map<String , Customer> customerMap = new HashMap<>();
    private static final int SENIOR = 1;
    private static final int NORMAL = 2;

    public CostumerCatalogFileOperator(String filepath) {
        this.filepath = filepath;
    }

    public void readFromFile(){
        try{
            FileInputStream fis = new FileInputStream(filepath);
            Scanner scanner = new Scanner(fis);

            while (scanner.hasNextLine()){
                String line = scanner.nextLine();
                String[] splitted = line.split(" ");
                addToMap(splitted[0],splitted[1],splitted[2]);
            }
            scanner.close();
        }
        catch (IOException ex){
            ex.printStackTrace();
            System.exit(-1);
        }
    }
    private void addToMap(String id, String name , String type){
        try {
            Customer customer = new Customer(id,name);
            if (type.equals("1")){
                customer.setType(SENIOR);
            }else {
                customer.setType(NORMAL);
            }
            customerMap.put(customer.getId(),customer);
        }catch (Exception e){
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public Map<String, Customer> getCustomerMap() {
        return customerMap;
    }
}
