package app.com.project215.model;


public class LocationModel {

    public String id , name ;

    @Override

    public String toString() {
        return this.name;
    }


    public String getLocation_id() {
        return this.id;
    }



}
