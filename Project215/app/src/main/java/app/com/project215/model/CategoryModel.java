package app.com.project215.model;


public class CategoryModel {

    public String id , name ;

    public CategoryModel(){};
    @Override

    public String toString() {
        return this.name;
    }


    public String getCategory_id() {
        return this.id;
    }



}
