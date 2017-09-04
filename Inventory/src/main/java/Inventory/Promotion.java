/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Inventory;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author joseph
 */
public class Promotion implements Serializable{
    
    private int ID;
    private ArrayList<Promotion_Product> List_Of_Product;
    private boolean validate;

    public Promotion(ArrayList<Promotion_Product> List_Of_Product) {
        this.List_Of_Product = List_Of_Product;
        this.validate = false;
    }

     public Promotion(ArrayList<Promotion_Product> List_Of_Product,boolean validate) {
        this.List_Of_Product = List_Of_Product;
        this.validate = validate;
    }
    
    
    public ArrayList<Promotion_Product> getList_Of_Product() {
        return List_Of_Product;
    }

    public void setList_Of_Product(ArrayList<Promotion_Product> List_Of_Product) {
        this.List_Of_Product = List_Of_Product;
    }

    public boolean isValide() {
        return validate;
    }

    public void setValidation(boolean validate) {
        this.validate = validate;
    }
    
    
    
    
    
    
    
    
    
    
    
}
