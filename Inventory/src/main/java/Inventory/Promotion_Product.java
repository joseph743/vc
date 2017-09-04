/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Inventory;

import java.util.Objects;

/**
 *
 * @author joseph
 */
public class Promotion_Product {
    
    private int ID;
    private Product produit;
    private double new_price;
    private int qtite;
    private double discount;

    public Promotion_Product(Product produit, double new_price, int qtite) {
        this.produit = produit;
        this.new_price = new_price;
        this.qtite = qtite;
        this.discount = 1.0;
    }
    
     public Promotion_Product(Product produit, double new_price, int qtite,double discount) {
        this.produit = produit;
        this.new_price = new_price;
        this.qtite = qtite;
        this.discount = discount;
    }
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + this.ID;
        hash = 73 * hash + Objects.hashCode(this.produit);
        hash = 73 * hash + (int) (Double.doubleToLongBits(this.new_price) ^ (Double.doubleToLongBits(this.new_price) >>> 32));
        hash = 73 * hash + this.qtite;
        hash = (int) (73 * hash + this.discount);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Promotion_Product other = (Promotion_Product) obj;
        if (this.ID != other.ID) {
            return false;
        }
        if (Double.doubleToLongBits(this.new_price) != Double.doubleToLongBits(other.new_price)) {
            return false;
        }
        if (this.qtite != other.qtite) {
            return false;
        }
        if (this.discount != other.discount) {
            return false;
        }
        if (!Objects.equals(this.produit, other.produit)) {
            return false;
        }
        return true;
    }

    
    
    
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Product getProduit() {
        return produit;
    }

    public void setProduit(Product produit) {
        this.produit = produit;
    }

    public double getNew_price() {
        return new_price;
    }

    public void setNew_price(double new_price) {
        this.new_price = new_price;
    }

    public int getQtite() {
        return qtite;
    }

    public void setQtite(int qtite) {
        this.qtite = qtite;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }
    
    
}
