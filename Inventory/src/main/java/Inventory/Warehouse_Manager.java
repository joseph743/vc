/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Inventory;

import java.io.Serializable;

/**
 *
 * @author joseph
 */
public class Warehouse_Manager extends Person implements Serializable {
    
    public Warehouse_Manager(String Name,  String Phone, String Mobile, String Fax, String Email, String Username,Role role, String Password) {
        super(Name, Phone, Mobile, Fax, Email,Username,Password,role);
        
    }
}
