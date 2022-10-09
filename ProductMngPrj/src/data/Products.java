/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.util.Collections;
import java.util.List;
import tools.MyTool;

/**
 *
 * @author Admin
 */
public class Products implements Comparable<Products>{
    public static final char SEPARATOR = ',';
    public static final String NAME_FORMAT = "^[a-zA-Z0-9]{5,}$";
    private int ID;
    private String name;
    private int price;
    private int quantity;
    private String status;

    public Products(int ID, String name, int price, int quantity, String status) {
        this.ID = ID;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.status = status;
    }
    
    public Products(String line) {
        String[] parts = line.split("" + this.SEPARATOR);
        ID = Integer.parseInt(parts[0].trim());
        name = parts[1].trim();
        price = Integer.parseInt(parts[2].trim());
        quantity = Integer.parseInt(parts[3].trim());
        status = parts[4].trim();
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public int compareTo(Products o) {
        if(this.quantity == o.quantity){
            return o.price - this.price;
        } else {
            return this.quantity - o.quantity;
        }
    }
}
