package app.com.project215.activities.warehouse;

public class Warehouse {

    public String name;
    public String address;
    public String description;
    //public int city_id;
    public String city ;
    public String latitude , longitude ;

    public Warehouse(String name, String address, String description, String city , String latitude , String longitude) {

        this.name = name;
        this.address = address;
        this.description = description;
        this.city = city;
        this.latitude = latitude;
        this.longitude = longitude;

    }


}