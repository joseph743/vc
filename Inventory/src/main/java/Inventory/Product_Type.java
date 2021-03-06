/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Inventory;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author joseph
 */
public class Product_Type implements Serializable { // stockable , consumable ,Service
    
    private int ID;
    private String Name;

    
    
    public Product_Type(String Name) {
        this.ID=this.hashCode();
        this.Name = Name;
    }
    
    
    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        
        hash = 89 * hash + Objects.hashCode(this.Name);
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
        final Product_Type other = (Product_Type) obj;
        if (this.ID != other.ID) {
            return false;
        }
        if (!Objects.equals(this.Name, other.Name)) {
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
    
    
    
}
