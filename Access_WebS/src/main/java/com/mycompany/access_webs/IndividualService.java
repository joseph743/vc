/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.access_webs;

import Inventory.Person;
import Inventory.Person;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.tasks.Task;
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
class IndividualService {
    
    FireBase_DataBase test;
    static DatabaseReference IndividualRef;
   static  DatabaseReference ref;
   List<Person> Individuales;
   static Person per;
    
    IndividualService() {
        try {
            this.test = new FireBase_DataBase();
             FirebaseDatabase database = FirebaseDatabase.getInstance();
             ref = database.getReference("");
             IndividualRef = ref.child("Person");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(IndividualService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public static List<Person> getAllIndividuals(){
        
    final List<Person> Individuales=new ArrayList<Person>();    
        
// Attach a listener to read the data at our posts reference
IndividualRef.addValueEventListener(new ValueEventListener() {
    
    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        Person post = dataSnapshot.getValue(Person.class);
        Individuales.add(post);
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {
        System.out.println("The read failed: " + databaseError.getCode());
    }
        });
        
return Individuales;
    }
    
    
    
      public static Person getIndividualForId(String id) {
        
          Query mQuery = ref.equalTo(id);
        
mQuery.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot,  String s) {
                per= (Person) dataSnapshot.getValue();
                
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
    
      
      
      
      
      
      public static Person CreateIndividual(Person Per) {
        
         Map<Integer,Person> Individuales=new HashMap<Integer,Person>(); //Integer for the ID;
         Individuales.put(Per.getID(), Per);
         
       IndividualRef.setValue(Individuales);
       return Per;
    }
      
      
 
      public static Person updateIndividual(Person Per) {
          
          DatabaseReference NewRef = IndividualRef.child(String.valueOf(Per.getID()));
        Map<String, Object> PerUpdates = new HashMap<String, Object >();
        PerUpdates.put(String.valueOf(Per.getID()),Per);
        NewRef.updateChildren(PerUpdates);
        return Per;
    }
        
      
 
    public static void deleteIndividual(String id) {
       
        DatabaseReference  DelRef= IndividualRef.child(id);
        
        DelRef.removeValue();
        
    
    }  

    
      
}
