package com.company.fileOperations;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class DiscountedProductFileOperator {
    private String filepath;
    private Map<String ,Double> stringDoubleMap = new HashMap<>();

    public DiscountedProductFileOperator(String filepath) {
        this.filepath = filepath;
    }
    public void readFromFile(){
        try{
            FileInputStream fis = new FileInputStream(filepath);
            Scanner scanner = new Scanner(fis);
            while (scanner.hasNextLine()){
                String line  = scanner.nextLine();
                String[] splitted = line.split(" ");
                stringDoubleMap.put(splitted[0],Double.parseDouble(splitted[1]));
            }
            scanner.close();
        }
        catch (IOException ex){
            ex.printStackTrace();
            System.exit(-1);
        }
    }

    public Map<String, Double> getStringDoubleMap() {
        return stringDoubleMap;
    }
}
