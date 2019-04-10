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
public abstract class Part {

    protected int partID;
    protected String name;
    protected double price;
    protected int inStock;
    protected int min;
    protected int max;


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

    //stock
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

    //PartID
    public void setPartID(int newID){
        partID = newID;
    }

    public int getPartID(){
        return partID;
    }

}