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

import java.util.ArrayList;

public class Product {

    private ArrayList<Part> associatedParts;
    private int ProductID;
    private String name;
    private double price;
    private int inStock;
    private int min;
    private int max;

    public Product(){

    }


    public Product(int productID, String name, double price, int inStock, int min, int max, ArrayList<Part> associatedParts){
        this.ProductID = productID;
        this.name = name;
        this.price = price;
        this.inStock = inStock;
        this.min = min;
        this.max = max;
        this.associatedParts = new ArrayList<>(associatedParts);

    }

    //name
    public void setName(String newName){
        name = newName;
    }

    public String getName(){
        return name;
    }

    //price
    public void setPrice(double newPrice){
        price = newPrice;
    }

    public double getPrice(){
        return price;
    }

    //inStock
    public void setInStock(int newStock){
        inStock = newStock;
    }

    public int getInStock(){
        return inStock;
    }

    //min
    public void setMin(int newMin){
        min = newMin;
    }

    public int getMin(){
        return min;
    }

    //max
    public void setMax(int newMax){
        max = newMax;
    }

    public int getMax(){
        return max;
    }


    //parts
    public void addAssociatedPart(ArrayList<Part> newParts){
        associatedParts = newParts;
    }
    public boolean removeAssociatePart(int partID){
        return true;
    }

    public ArrayList<Part> getAssociatedPart(){
        return this.associatedParts;
    }

    public ObservableList<Part> getObAssociatedPart(){
        ObservableList<Part> asparts = FXCollections.observableArrayList(this.associatedParts);
        return asparts;
    }

    //productID
    public void setProductID(int newID){
        ProductID = newID;
    }

    public int getProductID(){
        return ProductID;
    }




}

