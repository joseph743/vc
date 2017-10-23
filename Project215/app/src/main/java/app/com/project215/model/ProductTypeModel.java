package app.com.project215.model;


public class ProductTypeModel {

    public String id;
    public String  name;

    public ProductTypeModel(){};
    @Override

    public String toString() {
        return this.name;
    }


    public String getProductType_id() {
        return this.id;
    }
}
