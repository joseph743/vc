/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.access_webs;

import Inventory.Product_Category;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import java.util.List;
import com.mycompany.access_webs.FireBase_DataBase;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author joseph
 */
class Product_CategoryeService {
    
    FireBase_DataBase test;
    static DatabaseReference Product_CategoryRef;
   static  DatabaseReference ref;
   List<Product_Category> Product_Categoryes;
   static Product_Category per;
    
    Product_CategoryeService() {
        try {
            this.test = new FireBase_DataBase();
             FirebaseDatabase database = FirebaseDatabase.getInstance();
             ref = database.getReference("");
             Product_CategoryRef = ref.child("Product_Category");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Product_CategoryService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public static List<Product_Category> getAllProduct_Categorys(){
        
    final List<Product_Category> Product_Categories=new ArrayList<Product_Category>();    
        
// Attach a listener to read the data at our posts reference
Product_CategoryRef.addValueEventListener(new ValueEventListener() {
    
    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        Product_Category post = dataSnapshot.getValue(Product_Category.class);
        Product_Categories.add(post);
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {
        System.out.println("The read failed: " + databaseError.getCode());
    }
});
        
return Product_Categories;
    }
    
    
    
      public static Product_Category getProduct_CategoryForId(String id) {
        Query mQuery = ref.equalTo(id);
        
mQuery.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot,  String s) {
                per= (Product_Category) dataSnapshot.getValue();
                
            }

            @Override
            public void onChildChanged(DataSnapshot ds, String string) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void onChildRemoved(DataSnapshot ds) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void onChildMoved(DataSnapshot ds, String string) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void onCancelled(DatabaseError de) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
 });
          
          return per;  
          
            
    }
    
      
      
      
      
      
      public static Product_Category CreateProduct_Category(Product_Category Pro) {
        
         Map<Integer,Product_Category> Product_Categoryes=new HashMap<Integer,Product_Category>(); //Integer for the ID;
         Product_Categoryes.put(Pro.getID(), Pro);
         
       Product_CategoryRef.setValue(Product_Categoryes);
       return Pro;
    }
      
      
 
      public static Product_Category updateProduct_Category(Product_Category Pro) {
          
          DatabaseReference NewRef = Product_CategoryRef.child(String.valueOf(Pro.getID()));
        Map<String, Object> ProUpdates = new HashMap<String, Object >();
        ProUpdates.put(String.valueOf(Pro.getID()),Pro);
        NewRef.updateChildren(ProUpdates);
        return Pro;
    }
        
      
 
    public static void deleteProduct_Category(String id) {
       
        DatabaseReference  DelRef= Product_CategoryRef.child(id);
        
        DelRef.removeValue();
        
    
    }  

    
      
}
