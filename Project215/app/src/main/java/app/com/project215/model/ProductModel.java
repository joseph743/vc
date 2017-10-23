package app.com.project215.model;


public class ProductModel {

    public String id , name ,cost;

    @Override

    public String toString() {
        return this.name;
    }


    public String getProduct_id() {
        return this.id;
    }

    public String getProduct_cost() {
        return this.cost;
    }



}
