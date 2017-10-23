package app.com.project215.model;


public class RoleModel {

    public String role_id;
    public String role_name;

    public  RoleModel (){};
    @Override

    public String toString() {
        return this.role_name;
    }


    public String getRole_id() {
        return this.role_id;
    }
}
