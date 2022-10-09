/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import data.Products;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface ProductDao {
    List<Products> getNewProducts();
    List<Products> getAllProducts();
    
    Products updateID(int ID);
    boolean searchListProductID(int ID);
    boolean searchFileProductID(int ID);
    
    boolean searchListProductName(String productname);
    List<Products> printFileProducts(String name);
    Products printListProduct(String productname);
    boolean searchFileProductName(String productname);
    
    
    void createProduct();
    void updateProduct(Products product);
    void deleteProduct(Products product);
    void saveFile();
}
