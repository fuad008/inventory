/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.Models;

/**
 *
 * @author applefan
 */


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;



public class Inventory {

    private static ObservableList<Product> products = FXCollections.observableArrayList();
    private static ObservableList<Part> allparts = FXCollections.observableArrayList();
    private static int productID = 0;
    private static int partID = 0;

    //product
    public static void addProduct(Product p){
        products.add(p);
    }

    public static void removeProduct(Product p){
        products.remove(p);
    }

    public static Product lookupProduct(int num){
        for(Product p: getAllProducts()){
            if(p.getProductID() == num){
                return p;
            }
        }
        return null;
    }

    public static Product lookupProductByName(String st){
        for(Product p: getAllProducts()){
            if(p.getName().equals(st)){
                return p;
            }
        }
        return null;
    }

    public static void updateProduct(int num, Product p){
        products.set(num, p);
    }

    public static ObservableList<Product> getAllProducts() {
        return products;
    }

    public static int getProductID() {
        return productID;
    }

    public static int incProductID(){
         productID++;
         return productID;
    }

    public static int desProductID(){
        productID--;
        return productID;
    }


    //part
    public static void addPart(Part p){
        allparts.add(p);
    }

    public static void deletePart(Part p){
        allparts.remove(p);
    }

    public static Part lookupPart(int num){

        for(Part p: getAllparts()){
            if(p.getPartID()== num){
                    return p;
            }
        }


        return null;
    }

    public static Part lookupbyName(String name){
        for(Part p: getAllparts()){
            if(p.getName().equals(name)){
                return p;
            }
        }
        return null;
    }

    public static void updatePart(int num, Part p){
        allparts.set(num,p);
    }

    public static ObservableList<Part> getAllparts() {
        return allparts;
    }


    public static int getPartID() {
        return partID;
    }

    public static int incPartID(){
         partID++;
         return partID;
    }

    public static int desPartID(){
        partID--;
        return partID;
    }


}

