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
class Unit implements Serializable {
    
    private int ID;
    private String Name;
    private Unit_Category Cat;

    
    
    public Unit(String Name, Unit_Category Cat) {
        this.ID=this.hashCode();
        this.Name = Name;
        this.Cat = Cat;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        
        hash = 19 * hash + Objects.hashCode(this.Name);
        hash = 19 * hash + Objects.hashCode(this.Cat);
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
        final Unit other = (Unit) obj;
        if (this.ID != other.ID) {
            return false;
        }
        if (!Objects.equals(this.Name, other.Name)) {
            return false;
        }
        if (!Objects.equals(this.Cat, other.Cat)) {
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

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public Unit_Category getCat() {
        return Cat;
    }

    public void setCat(Unit_Category Cat) {
        this.Cat = Cat;
    }

    
    
    
    
}
