package app.com.project215.activities.products;

public class Product {

    public String name ;
    public int total_quantity;
    public int code;
    public double cost, sale_price;
    public String  weight;
    public String Product_type_name , category_name , brand_name  ;
    public int Product_type_id , category_id , brand_id , brand_reference;
    String description , barcode ;
    ProductLocation[] location ;


    public Product(String name, int total_quantity,int code, double cost , double sale_price , String weight , String Product_type_name , int Product_type_id , String category_name , int category_id , String brand_name , int brand_id , int brand_reference, String description , String barcode , ProductLocation[] location) {

        this.name = name;
        this.total_quantity = total_quantity;
        this.code = code;
        this.cost = cost;
        this.sale_price = sale_price;
        this.weight = weight;
        this.Product_type_name = Product_type_name;
        this.Product_type_id = Product_type_id;
        this.category_name = category_name;
        this.category_id = category_id;
        this.brand_name = brand_name;
        this.brand_id = brand_id;
        this.brand_reference = brand_reference;
        this.description = description;
        this.barcode = barcode;
        this.location = location;
    }


}