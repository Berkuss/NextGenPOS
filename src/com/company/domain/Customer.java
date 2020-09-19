package com.company.domain;

import javax.print.DocFlavor;

public class Customer {
    private static final int SENIOR = 1;
    private static final int NORMAL = 2;

    private String id;
    private String Name;
    private int  type;

    public Customer(String id, String name) {
        this.id = id;
        Name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setType(int type) {
        if(type == SENIOR || type == NORMAL){
            this.type = type;
        }
    }
    public boolean isSenior(){
        if(this.type == SENIOR){
            return true;
        }
        return false;
    }
}
