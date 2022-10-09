/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import dao.ProductDao;
import dao.ProductDaoImpl;
import data.Products;
import java.util.ArrayList;
import java.util.List;
import tools.MyTool;

/**
 *
 * @author Admin
 */
public class ProductMain {

    public static void main(String[] args) {
        boolean changed = false;
        String response;
        Products product;
        int inputID;
        String[] options = {"Print the list", "Create a product",
            "Check existing product", "Search for product information in list",
            "Update product", "Save products to file", 
            "Print list of products from the file"
        };
        Menu mnu = new Menu(options);
        ProductDao productDao = new ProductDaoImpl();
        int choice = 0;
        int choice2 = 0;
        do {
            choice = mnu.getChoice("PRODUCTS MANAGEMENT");
            switch (choice) {
                case 1:
                    for (Products products : productDao.getNewProducts()) {
                        System.out.println(products.getID() + ", "
                                + products.getName()+ ", " + products.getPrice()
                                + ", " + products.getQuantity() + ", " 
                                + products.getStatus());
                    }
                    break;
                case 2:
                    productDao.createProduct();
                    changed = true;
                    break;
                case 3:
                    MyTool.SC.nextLine();
                    String productName = MyTool.readPattern("Enter Name of Product: ", 
                    Products.NAME_FORMAT);
                    boolean item = new ProductDaoImpl().searchFileProductName(productName);
                    if (item){
                        System.out.println("    Product Exist!");
                        productDao.printFileProducts(productName);
                    } else {
                        System.out.println("    No Product Found!");
                    }  
                    break;
                case 4:
                    MyTool.SC.nextLine();
                    String productName2 = MyTool.readPattern("Enter Name of Product: ", 
                    Products.NAME_FORMAT);
                    boolean item2 = new ProductDaoImpl().searchListProductName(productName2);
                    if (!item2){
                        productDao.printListProduct(productName2);
                    } else {
                        System.out.println("    No Product Found!");
                    }
                    break;
                case 5:
                    boolean quit = false;
                    do {
                        mnu.list.clear();
                        mnu.list.add("Update a product");
                        mnu.list.add("Delete a product");
                        choice2 = mnu.getChoice2("UPDATE PRODUCT");
                        switch (choice2) {
                            case 1:
                                inputID = MyTool.getInt("Enter ID: ", 1, 500);
                                product = productDao.updateID(inputID);
                                if (product != null) {
                                    productDao.updateProduct(product);
                                } else {
                                    System.out.println("    No Product Found!");
                                }
                                changed = true;
                                break;
                            case 2:
                                inputID = MyTool.getInt("Enter ID: ", 1, 500);
                                product = productDao.updateID(inputID);
                                if (product != null) {
                                    productDao.deleteProduct(product);
                                } else {
                                    System.out.println("    No Product Found!");
                                }
                                changed = true;
                                break;
                            default:
                                quit = true;
                        }
                    } while (!quit);
                    break;
                case 6:
                    productDao.saveFile();
                    changed = false;
                    break;
                case 7:
                    for (Products products : new ProductDaoImpl().getAllProducts()) {
                        System.out.println(products.getID() + ", "
                                + products.getName()+ ", " + products.getPrice()
                                + ", " + products.getQuantity() + ", " 
                                + products.getStatus());
                    }
                    break;
                default:
                    if (changed) {
                        boolean check = true;
                        do {
                            System.out.print("Save changes Y/N? ");
                            response = MyTool.SC.next().toUpperCase();
                            if(response.startsWith("Y")){
                                    productDao.saveFile();
                                check = false;
                            } else if (response.startsWith("N")){
                                check = false;
                            } else {
                                check = true;
                            }
                        } while(check);
                    }
            }
        } while (choice > 0 && choice < 8);
        System.out.println("");
        System.out.println("Goodbye.");
    }
}
