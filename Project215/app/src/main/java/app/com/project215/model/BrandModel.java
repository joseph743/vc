package app.com.project215.model;


public class BrandModel {

    public String id , name , reference;

    public BrandModel(){};
    @Override

    public String toString() {
        return this.name;
    }


    public String getBrand_id() {
        return this.id;
    }

    public String getBrand_reference() {
        return this.reference;
    }


}
