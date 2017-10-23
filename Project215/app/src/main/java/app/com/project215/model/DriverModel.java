package app.com.project215.model;


public class DriverModel {

    public String id;
    public String name;

    public DriverModel(){};
    @Override

    public String toString() {
        return this.name;
    }


    public String getDriver_id() {
        return this.id;
    }
}
