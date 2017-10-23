package app.com.project215.activities.products;

public class ProductLocation {

    public String corridor , shelf , height ,  warehouse_name ;
    public int quantity;



    public ProductLocation(String corridor, String shelf , String height , String warehouse_name ,int quantity) {

        this.corridor = corridor;
        this.shelf = shelf;
        this.height = height;
        this.warehouse_name = warehouse_name;
        this.quantity = quantity;

    }


}