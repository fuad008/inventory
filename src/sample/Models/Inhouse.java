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


public class Inhouse extends Part{

    private int machineID;


    public  Inhouse(){

    }

    public Inhouse(int id, String name, double price, int stock, int min, int max, int mID){
        this.partID = id;
        this.name = name;
        this.price = price;
        this.inStock = stock;
        this.min = min;
        this.max = max;
        this.machineID = mID;
    }



    public void setMachineID(int newID){
        machineID = newID;
    }

    public int getMachineID(){
        return machineID;
    }


}

