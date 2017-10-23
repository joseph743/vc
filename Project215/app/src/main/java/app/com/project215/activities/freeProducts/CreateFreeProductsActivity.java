package app.com.project215.activities.freeProducts;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import app.com.project215.R;
import app.com.project215.model.ProductModel;
import app.com.project215.model.ProductTypeModel;
import app.com.project215.util.AppUtility;
import app.com.project215.util.Logger;

public class CreateFreeProductsActivity extends AppCompatActivity implements View.OnClickListener, Spinner.OnItemSelectedListener {
    int check_product = 0, check_product_type = 0;
    int delete_counter = 1;
    private Context context;
    private ProgressDialog mDialog;
    String selectedRoleId;
    ImageView deleteBtn;

    TextView text_select_product, text_select_product_type, text_qty, text_description, text_create_free_product;
    EditText edit_qty, edit_description;
    String str_qty, str_description;

    Spinner spProduct;
    String selectedProductID = "0";
    ArrayList arrayListProduct;
    ArrayAdapter<ProductModel> ProductAdapter;

    Spinner spProductType;
    String selectedProductTypeID = "0";
    ArrayList arrayListProductType;
    ArrayAdapter<ProductTypeModel> ProductTypeAdapter;


    JSONObject c_jsonObject = null;
    Bundle extras = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_free_products_create);

        context = CreateFreeProductsActivity.this;

        try {
            extras = getIntent().getExtras();
            if (extras != null) {
                c_jsonObject = new JSONObject(getIntent().getStringExtra("product"));
                selectedRoleId = c_jsonObject.getString("role_id");
            }
        } catch (JSONException e) {

        }

        initView();

        fillArrays();


    }


    /**
     * Initialize the view
     */

    private void initView() {


        deleteBtn = (ImageView) findViewById(R.id.img_delete);

        mDialog = new ProgressDialog(context);


        text_select_product = (TextView) findViewById(R.id.tv_select_product);
        text_select_product.setOnClickListener(this);
        spProduct = (Spinner) findViewById(R.id.sp_product);
        spProduct.setOnItemSelectedListener(this);

        text_select_product_type = (TextView) findViewById(R.id.tv_select_product_type);
        text_select_product_type.setOnClickListener(this);
        spProductType = (Spinner) findViewById(R.id.sp_product_type);
        spProductType.setOnItemSelectedListener(this);

        text_qty = (TextView) findViewById(R.id.tv_qty);
        text_qty.setOnClickListener(this);
        edit_qty = (EditText) findViewById(R.id.ed_qty);
        edit_qty.setOnClickListener(this);

        text_description = (TextView) findViewById(R.id.tv_description);
        text_description.setOnClickListener(this);
        edit_description = (EditText) findViewById(R.id.ed_description);
        edit_description.setOnClickListener(this);

        text_create_free_product = (TextView) findViewById(R.id.tv_create_free_product);
        text_create_free_product.setOnClickListener(this);

        //edit here
        if (c_jsonObject != null) {

            try {
                Logger.log("c_jsonObject", c_jsonObject.toString());
                selectedRoleId = c_jsonObject.getString("role_id");
                edit_qty.setText(c_jsonObject.getString("total_quantity"));
                edit_description.setText(c_jsonObject.getString("description"));


                if (c_jsonObject.getString("role_id").contains("3")) { // warehouse manager just can view


//                    edit_name.setEnabled(false);
//                    edit_qty.setEnabled(false);
//
//                    edit_name.setTextColor(Color.GRAY);
//                    edit_qty.setTextColor(Color.GRAY);
//                    edit_cost.setTextColor(Color.GRAY);


                } else if (c_jsonObject.getString("role_id").contains("2")) { // inventory manager can do anything

                    deleteBtn.setVisibility(View.VISIBLE);
                    deleteBtn.setOnClickListener(this);

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


    }


    @Override
    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

        switch (parentView.getId()) {
            case R.id.sp_product:
                check_product++;
                if (check_product > 1) {
                    selectedProductID = ProductAdapter.getItem(position).getProduct_id();
                }
                break;

            case R.id.sp_product_type:
                check_product_type++;
                if (check_product_type > 1) {
                    selectedProductTypeID = ProductTypeAdapter.getItem(position).getProductType_id();
                }
                break;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_description:
                edit_description.requestFocus();
                break;
            case R.id.tv_qty:
                edit_qty.requestFocus();
                break;

            case R.id.tv_create_free_product:
                createProductEvent();
                break;

//            case R.id.img_delete:
//                if (delete_counter == 1) {
//                    delete_counter++;
//                    AppUtility.showToast(context, "Press a second time to delete warehouse");
//
//                } else {
//                    Intent intent = new Intent();
//                    setResult(RESULT_OK, intent);
//                    finish();
//                }
//                break;


            default:
                break;
        }
    }


    public void fillArrays() {

        fillProductType();
        fillproduct();
    }

    public void fillproduct() {
        try {

            arrayListProduct = new ArrayList<ProductModel>();
            String jsonLocation = AppUtility.AssetJSONFile("json/getProducts.json", context);

            JSONObject jsonObject = new JSONObject(jsonLocation);
            if (jsonObject.has("products")) {
                JSONArray jsonArray = (JSONArray) jsonObject.getJSONArray("products");
                for (int i = 0; i < jsonArray.length(); i++) {

                    ProductModel productModel = new ProductModel();
                    JSONObject jb = (JSONObject) jsonArray.get(i);

                    //add only service or promotions and select product wich id ==0
                    if (i == 0 || !(jb.getString("Product_type_id").contains("2") || jb.getString("Product_type_id").contains("3") || jb.getString("Product_type_id").contains("4"))) {
                        productModel.id = jb.getString("id");
                        productModel.name = jb.getString("name");

                        arrayListProduct.add(productModel);
                    }


                }//end for

                ProductAdapter = new ArrayAdapter<ProductModel>(CreateFreeProductsActivity.this, R.layout.spinner_text, arrayListProduct);
                ProductAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                spProduct.setAdapter(ProductAdapter);

            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void fillProductType() {
        try {

            arrayListProductType = new ArrayList<ProductTypeModel>();
            String jsonLocation = AppUtility.AssetJSONFile("json/getFreeProductType.json", context);

            JSONObject jsonObject = new JSONObject(jsonLocation);
            if (jsonObject.has("product_type")) {
                JSONArray jsonArray = (JSONArray) jsonObject.getJSONArray("product_type");
                for (int i = 0; i < jsonArray.length(); i++) {
                    ProductTypeModel productTypeModel = new ProductTypeModel();
                    JSONObject jb = (JSONObject) jsonArray.get(i);
                    productTypeModel.id = jb.getString("id");
                    productTypeModel.name = jb.getString("name");


                    arrayListProductType.add(productTypeModel);
                }

                Logger.log("arrayListProductType.size()", String.valueOf(arrayListProductType.size()));

                ProductTypeAdapter = new ArrayAdapter<ProductTypeModel>(CreateFreeProductsActivity.this, R.layout.spinner_text, arrayListProductType);
                ProductTypeAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                spProductType.setAdapter(ProductTypeAdapter);


            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }


    private void createProductEvent() {


        str_qty = edit_qty.getText().toString().trim();
        str_description = edit_description.getText().toString().trim();


        Logger.log("str_qty", str_qty);
        Logger.log("str_description", str_description);
        Logger.log("selectedProductID", selectedProductID);
        Logger.log("selectedProductTypeID", selectedProductTypeID);

        if (selectedProductID.contains("0")) {
            AppUtility.showToast(context, getString(R.string.product_requirement));
        } else if (selectedProductTypeID.contains("0")) {
            AppUtility.showToast(context, getString(R.string.product_type_requirement));
        } else if (str_qty.length() == 0) {
            AppUtility.showToast(context, getString(R.string.qty_requirement));
        } else if (str_description.length() == 0) {
            AppUtility.showToast(context, getString(R.string.reason_requirement));
        } else {
            AppUtility.showToast(context, c_jsonObject == null ? "callApiCreateFreeProduct" : "callApiEditFreeProduct");
            // callApiRegister();
        }

//        if (c_jsonObject != null) {
//            AppUtility.showToast(CreateFreeProductsActivity.this, "EDIT MODE");
//            Logger.log("EDIT MODE", "ON");
//        } else {
//            AppUtility.showToast(CreateFreeProductsActivity.this, "CREATE MODE");
//            Logger.log("CREATE MODE", "ON");
//        }


    }


    // received delete event from createProductActivity
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == 1) {
//            if (resultCode == RESULT_OK) {
//                text_selected_expiry_date.setText(data.getStringExtra("calendarValue"));
//            }
//        }
//    }


//    private Map<String, String> getParams() {
//        Map<String, String> params = new HashMap<String, String>();
//        params.put("name", str_name);
//        params.put("address", str_description);
//        params.put("description", str_address);
//        // params.put("city", selectedCityID);
//        params.put("city", str_city);
//        params.put("latitude", latitude);
//        params.put("longitude", longitude);
//        return params;
//    }

}
