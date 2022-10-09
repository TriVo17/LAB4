/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import data.Products;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import tools.MyTool;
import static tools.MyTool.SC;

/**
 *
 * @author Admin
 */
public class ProductDaoImpl implements ProductDao{
    private List<Products> products;
    String filename = "product.txt";
    private String dataFile = "";

    public ProductDaoImpl() {
        products = new ArrayList<>();
    }
    
    public List<Products> getNewProducts() {
        if(products.isEmpty()) {
            System.out.println("Empty List.");
        } 
        return products;
    }
    
    public List<Products> printFileProducts(String name) {
        List<Products> list = new ArrayList<>();
        list = MyTool.loadFromFile(filename);
        for (Products product : list) 
            if (product.getName().equalsIgnoreCase(name)){
                System.out.println(product.getID() + ", "
                                + product.getName()+ ", " + product.getPrice()
                                + ", " + product.getQuantity() + ", " 
                                + product.getStatus());
            }
        return products;
    } // case 3
    
    public List<Products> getAllProducts(){
        products = MyTool.loadFromFile(filename);
        Collections.sort(products);
        return products;
    }
    
    public Products updateID(int ID) {
        for (Products product : products) 
            if (product.getID()==ID) 
                return product;
        return null;
    }
    
    public boolean searchListProductID(int ID) {
        for (Products product : products) 
            if (product.getID()==ID)
                return true;
        return false;
    }
    
    public boolean searchFileProductID(int ID) {
        List<Products> list = new ArrayList<>();
        list = MyTool.loadFromFile(filename);
        for (Products product : list) 
            if (product.getID()==ID)
                return true;
        return false;
    }
    
    public boolean searchFileProductName(String productname) {
        List<Products> list = new ArrayList<>();
        list = MyTool.loadFromFile(filename);
        for (Products product : list) 
            if (product.getName().equalsIgnoreCase(productname)) 
                return true;
        return false;
    }//
    
    public boolean searchListProductName(String productname) {
        for (Products product : products) 
            if (product.getName().equalsIgnoreCase(productname)) 
                return true;
        return false;
    }//

    public Products printListProduct(String productname) {
        for (Products product : products)
            if (product.getName().equalsIgnoreCase(productname)) 
                System.out.println(product.getID() + ", "
                                + product.getName()+ ", " + product.getPrice()
                                + ", " + product.getQuantity() + ", " 
                                + product.getStatus());
        return null;
    }//
    

    
    public void createProduct() {
        int ID;
        String name;
        int price;
        int quantity;
        String status = "AVAILABLE";
        boolean check = true;
        int pos;
        System.out.println("    Enter New Product Details");
        do {   
            ID = MyTool.getInt("Enter ID(1-500): ", 1, 500);
            if(searchListProductID(ID)){
                System.out.println("    ID is Duplicated!");
            } else if(searchFileProductID(ID)){
                System.out.println("    ID is Duplicated!");
            } else {
                check = false;
            }
        } while (check);
        MyTool.SC.nextLine();
        do {
            check = true;
            name = MyTool.readPattern("Enter Name(Atleast 5 chars): ", Products.NAME_FORMAT);
            name = name.toUpperCase();
            if (searchListProductName(name)) {
                System.out.println("  Product is Duplicated!");
            } else if (searchFileProductName(name)){
                System.out.println("  Product is Duplicated!");
            } else {
                check = false;
            }
        } while (check);
        price = MyTool.getInt("Enter Price(0-10000): ", 0, 10000);
        quantity = MyTool.getInt("Enter Quantity(1-1000): ", 1, 1000);
        products.add(new Products(ID, name, price, quantity, status));
        System.out.println("");
        System.out.println("    New Product Has Been Added!");
    }

    @Override
    public void updateProduct(Products product) {
        String newName = "";
        int newPrice;
        int newQuantity;
        String newStatus = "";
        String response;
        boolean check = true;
        System.out.println("    Enter New Details For Product");
        MyTool.SC.nextLine();
        do {  
            newName = MyTool.readPattern("Enter Name(Atleast 5 chars): ", 
                    Products.NAME_FORMAT);
            newName = newName.toUpperCase();
            if (searchListProductName(newName)) {
                System.out.println("  Product is Duplicated!");
            } else if (searchFileProductName(newName)){
                System.out.println("  Product is Duplicated!");
            } else {
                product.setName(newName);
                check = false;
            }
        } while (check);
        newPrice = MyTool.getInt("Enter Price(0-10000): ", 0, 10000);
        product.setPrice(newPrice);
        newQuantity = MyTool.getInt("Enter Quantity(1-1000): ", 1, 1000);
        product.setQuantity(newQuantity);
        do {
            check = true;
            System.out.print("Is The Product Still AVAILABLE (Y/N)?  ");
            response = MyTool.SC.next().toUpperCase();
            if(response.startsWith("Y")){
                newStatus = "AVAILABLE";
                product.setStatus(newStatus);
                check = false;
            } else if (response.startsWith("N")){
                newStatus = "NOT AVAILABLE";
                product.setStatus(newStatus);
                check = false;
            } else {
                check = true;
            }
        } while(check);
        System.out.println("    Update Complete!");
    }

    @Override
    public void deleteProduct(Products product) {
        products.remove(product);
        System.out.println("    Product is Deleted!");
    }

    @Override
    public void saveFile() {
        if(products.isEmpty()){
            System.out.println("    Empty List!");
        } else {
            MyTool.saveToFile(products);
            System.out.println("    New Product(s) Has Been Saved to File.");
        }
    }

    

}
