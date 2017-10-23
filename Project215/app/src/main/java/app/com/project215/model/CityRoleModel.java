package app.com.project215.model;


public class CityRoleModel {

    public String city_id;//zone_id

    public String city_name;//zone_name

    @Override
    public String toString() {
        return this.city_name;
    }

    public String getCity_id() {
        return this.city_id;
    }
}
