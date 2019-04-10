/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.Models;

public class Outsourced extends Part{

    private String companyName;

    public Outsourced(){

    }

    public Outsourced(int id, String name, double price, int stock, int min, int max, String cm) {
        this.partID = id;
        this.name = name;
        this.price = price;
        this.inStock = stock;
        this.min = min;
        this.max = max;
        this.companyName = cm;
    }

    public String getCompanyName(){
        return companyName;
    }

    public void setCompanyName(String newName){
        companyName = newName;
    }

}
