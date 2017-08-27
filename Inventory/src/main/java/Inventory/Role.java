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
public class Role implements Serializable {
    
    private int ID;
    private String Role_Name;
    //inventory manager,Client , Warehouse Manager;
    
    
     public Role(String Role_Name) {
        this.ID = this.hashCode();
        this.Role_Name = Role_Name;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

   

    public String getRole_Name() {
        return Role_Name;
    }

    public void setRole_Name(String Role_Name) {
        this.Role_Name = Role_Name;
    }

    @Override
    public int hashCode() {
        int hash = 7;
      
        hash = 29 * hash + Objects.hashCode(this.Role_Name);
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
        final Role other = (Role) obj;
        if (this.ID != other.ID) {
            return false;
        }
        if (!Objects.equals(this.Role_Name, other.Role_Name)) {
            return false;
        }
        return true;
    }
    
    
    
    
    
}
