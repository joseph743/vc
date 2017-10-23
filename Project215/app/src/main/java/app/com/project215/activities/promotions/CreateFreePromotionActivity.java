package app.com.project215.activities.promotions;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import app.com.project215.R;
import app.com.project215.activities.products.CreateProductActivity;
import app.com.project215.model.BrandModel;
import app.com.project215.model.ProductModel;
import app.com.project215.util.AppUtility;
import app.com.project215.util.Logger;

import static app.com.project215.util.AppUtility.AssetJSONFile;

public class CreateFreePromotionActivity extends AppCompatActivity implements View.OnClickListener, Spinner.OnItemSelectedListener {
    String edit_brand_firstTime = "1", edit_category_firstTime = "1", edit_product_type_firstTime = "1";
    int check_brand = 0;
    int counter_main = 1;
    int counter_free = 1;
    int delete_counter = 1;
    private Context context;
    private ProgressDialog mDialog;
    String selectedRoleId;
    ImageView deleteBtn, plusBtn;

    LinearLayout linear_parent_main, linear_parent_free, linear_horizontal, linear_product, linear_plus;
    LinearLayout linear_numberOfProduct_plus;

    TextView text_numberOfProduct, text_addMoreProduct, text_product, text_proceed;
    EditText edit_product;
    Spinner spProduct;
    ArrayList arrayListProduct;
    ArrayAdapter<ProductModel> ProductAdapter;

    Spinner spBrand;
    String selectedBrandID = "0", selectedBrandReference = "0";
    ArrayList arrayListBrand;
    ArrayAdapter<BrandModel> brandAdapter;


    ArrayList<EditText> edit_arrayList_main = new ArrayList<EditText>();
    ArrayList<TextView> text_arrayList_main = new ArrayList<TextView>();
    ArrayList<Spinner> spinner_arrayList_main = new ArrayList<Spinner>();

    ArrayList<EditText> edit_arrayList_free = new ArrayList<EditText>();
    ArrayList<TextView> text_arrayList_free = new ArrayList<TextView>();
    ArrayList<Spinner> spinner_arrayList_free = new ArrayList<Spinner>();

    JSONObject c_jsonObject = null;
    Bundle extras = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_free_promotion_create);

        context = CreateFreePromotionActivity.this;

//        try {
//            extras = getIntent().getExtras();
//            if (extras != null) {
//                c_jsonObject = new JSONObject(getIntent().getStringExtra("product"));
//                selectedRoleId = c_jsonObject.getString("role_id");
//            }
//        } catch (JSONException e) {
//
//        }


        initView();

        fillArrays();

    }


    /**
     * Initialize the view
     */

    private void initView() {


        deleteBtn = (ImageView) findViewById(R.id.img_delete);

        mDialog = new ProgressDialog(context);

        text_proceed = (TextView) findViewById(R.id.tv_proceed);
        text_proceed.setOnClickListener(this);
        linear_parent_main = (LinearLayout) findViewById(R.id.ll_parent_main);
        linear_parent_free = (LinearLayout) findViewById(R.id.ll_parent_free);

        spBrand = (Spinner) findViewById(R.id.sp_brand);
        spBrand.setOnItemSelectedListener(this);

        //edit here
//        if (c_jsonObject != null) {
//
//            try {
//                Logger.log("c_jsonObject", c_jsonObject.toString());
//                selectedRoleId = c_jsonObject.getString("role_id");
//
//                edit_name.setText(c_jsonObject.getString("name"));
//                edit_qty.setText(c_jsonObject.getString("total_quantity"));
//                edit_cost.setText(c_jsonObject.getString("cost"));
//                edit_sale_price.setText(c_jsonObject.getString("sale_price"));
//                edit_weight.setText(c_jsonObject.getString("weight"));
//                edit_description.setText(c_jsonObject.getString("description"));
//                edit_barcode.setText(c_jsonObject.getString("barcode"));
//                text_selected_expiry_date.setText(c_jsonObject.getString("expiry_date"));
//
//
//                if (c_jsonObject.getString("role_id").contains("3")) { // warehouse manager just can view
//
//
//                    edit_name.setEnabled(false);
//                  //  edit_qty.setEnabled(false);
//
//                    edit_name.setTextColor(Color.GRAY);
//                  //  edit_qty.setTextColor(Color.GRAY);
//                    edit_cost.setTextColor(Color.GRAY);
//
//
//                } else if (c_jsonObject.getString("role_id").contains("2")) { // inventory manager can do anything
//
//                    deleteBtn.setVisibility(View.VISIBLE);
//                    deleteBtn.setOnClickListener(this);
//
//                }
//
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }

        drowProduct(1, null, "main");
        drowProduct(1, null, "free");

    }


    public void drowProduct(final int index, final JSONObject jObject, final String type) {

        //linear_location
        linear_product = new LinearLayout(this);
        LinearLayout.LayoutParams LinearlayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        LinearlayoutParams.setMargins(AppUtility.dpToPx(this, 0), AppUtility.dpToPx(this, 10), AppUtility.dpToPx(this, 0), AppUtility.dpToPx(this, 0));
        linear_product.setOrientation(LinearLayout.VERTICAL);

        if (type.contains("main")) {
            linear_parent_main.addView(linear_product, LinearlayoutParams);
        } else {
            linear_parent_free.addView(linear_product, LinearlayoutParams);
        }


        //view for number of location + btn
        linear_numberOfProduct_plus = new LinearLayout(this);
        linear_numberOfProduct_plus.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        linear_numberOfProduct_plus.setOrientation(LinearLayout.HORIZONTAL);
        linear_product.addView(linear_numberOfProduct_plus);

        //1. text_numberOfLocation
        text_numberOfProduct = new TextView(this);
        text_numberOfProduct.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        text_numberOfProduct.setGravity(Gravity.CENTER | Gravity.LEFT);
        text_numberOfProduct.setPadding(AppUtility.dpToPx(this, 10), AppUtility.dpToPx(this, 10), AppUtility.dpToPx(this, 10), AppUtility.dpToPx(this, 0));
        text_numberOfProduct.setText("• " + (type.contains("main") ? "Main " : "Free ") + "Product (" + index + ")");
        text_numberOfProduct.setTextColor(Color.BLACK);
        text_numberOfProduct.setTextSize(14);
        linear_numberOfProduct_plus.addView(text_numberOfProduct);

        if (jObject == null && index == 1) {
            //2.1 plus btn view
            linear_plus = new LinearLayout(this);
            LinearLayout.LayoutParams LinearlayoutPlusParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            linear_plus.setGravity(Gravity.RIGHT);
            linear_numberOfProduct_plus.addView(linear_plus, LinearlayoutPlusParams);

            //2.2 plus btn
            plusBtn = new ImageView(this);
            LinearLayout.LayoutParams LinearlayoutImageParams = new LinearLayout.LayoutParams(AppUtility.dpToPx(this, 35), AppUtility.dpToPx(this, 35));
            plusBtn.setImageResource(R.drawable.ic_plus);
            linear_plus.addView(plusBtn, LinearlayoutImageParams);

            plusBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (type.contains("main")) {
                        counter_main++;
                        drowProduct(counter_main, null, type);
                    } else {
                        counter_free++;
                        drowProduct(counter_free, null, type);
                    }


                }
            });
        }//end plus btn

        try {
            drowProductRow("Product", jObject != null ? jObject.getString("products_name") : " ", type);
            drowProductRow("Quantity", jObject != null ? jObject.getString("qty") : " ", type);
        } catch (JSONException e) {

        }


    }


    public void drowProductRow(String text, String value, String type) {


        //linear_horizontal
        linear_horizontal = new LinearLayout(this);
        LinearLayout.LayoutParams LinearHorizontalParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        LinearHorizontalParams.setMargins(AppUtility.dpToPx(this, 0), AppUtility.dpToPx(this, 10), AppUtility.dpToPx(this, 0), AppUtility.dpToPx(this, 0));
        linear_horizontal.setOrientation(LinearLayout.HORIZONTAL);
        linear_horizontal.setTag(2);
        linear_horizontal.setBackground(getResources().getDrawable(R.drawable.edittext_bg));
        linear_product.addView(linear_horizontal, LinearHorizontalParams);

        //text_product
        text_product = new TextView(this);
        LinearLayout.LayoutParams textParams = new LinearLayout.LayoutParams(AppUtility.dpToPx(this, 130), LinearLayout.LayoutParams.WRAP_CONTENT);
        textParams.setMargins(AppUtility.dpToPx(this, 0), AppUtility.dpToPx(this, 0), AppUtility.dpToPx(this, 0), AppUtility.dpToPx(this, 2));
        text_product.setPadding(AppUtility.dpToPx(this, 10), AppUtility.dpToPx(this, 10), AppUtility.dpToPx(this, 10), AppUtility.dpToPx(this, 10));
        text_product.setText(text);
        text_product.setBackgroundColor(Color.WHITE);
        text_product.setTextColor(Color.BLACK);
        text_product.setTextSize(14);
        linear_horizontal.addView(text_product, textParams);


        if (text.contains("Quantity") || value != " ") {//edit text
            //edit_product
            edit_product = new EditText(this);
            LinearLayout.LayoutParams editParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            editParams.setMargins(AppUtility.dpToPx(this, 0), AppUtility.dpToPx(this, 0), AppUtility.dpToPx(this, 0), AppUtility.dpToPx(this, 2));
            edit_product.setPadding(AppUtility.dpToPx(this, 10), AppUtility.dpToPx(this, 10), AppUtility.dpToPx(this, 10), AppUtility.dpToPx(this, 10));
            edit_product.setBackgroundColor(Color.WHITE);
            edit_product.setInputType(InputType.TYPE_CLASS_NUMBER);
            edit_product.setTextColor(Color.BLACK);
            edit_product.setTextSize(14);

            if (value != " ") {
                edit_product.setText(value);
                edit_product.setEnabled(false);
            }

            if (type.contains("main")) {
                edit_arrayList_main.add(edit_product);
                linear_horizontal.addView(edit_arrayList_main.get(edit_arrayList_main.size() - 1), editParams);
            } else {
                edit_arrayList_free.add(edit_product);
                linear_horizontal.addView(edit_arrayList_free.get(edit_arrayList_free.size() - 1), editParams);
            }

        } else { // it's a spinner
            spProduct = new Spinner(this);

            LinearLayout.LayoutParams editParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            editParams.setMargins(AppUtility.dpToPx(this, 0), AppUtility.dpToPx(this, 0), AppUtility.dpToPx(this, 0), AppUtility.dpToPx(this, 2));
            spProduct.setPadding(AppUtility.dpToPx(this, 10), AppUtility.dpToPx(this, 10), AppUtility.dpToPx(this, 10), AppUtility.dpToPx(this, 10));
            spProduct.setBackgroundColor(Color.WHITE);

//            if (value != null) {
//                sp_product.setText(value);
//            }

            try {

                arrayListProduct = new ArrayList<ProductModel>();
                String jsonLocation = AssetJSONFile("json/getProducts.json", context);

                JSONObject jsonObject = new JSONObject(jsonLocation);
                if (jsonObject.has("products")) {
                    JSONArray jsonArray = (JSONArray) jsonObject.getJSONArray("products");
                    for (int i = 0; i < jsonArray.length(); i++) {

                        ProductModel productModel = new ProductModel();
                        JSONObject jb = (JSONObject) jsonArray.get(i);

                        //add only service or promotions and select product wich id ==0
                        if (i == 0 || !(jb.getString("Product_type_id").contains("1") || jb.getString("Product_type_id").contains("2") || jb.getString("Product_type_id").contains("3") || jb.getString("Product_type_id").contains("4"))) {
                            productModel.id = jb.getString("id");
                            productModel.name = jb.getString("name");

                            arrayListProduct.add(productModel);
                        }


                    }//end for

                    ProductAdapter = new ArrayAdapter<ProductModel>(CreateFreePromotionActivity.this, R.layout.spinner_text, arrayListProduct);
                    ProductAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                    spProduct.setAdapter(ProductAdapter);

                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            if (type.contains("main")) {
                spinner_arrayList_main.add(spProduct);
                linear_horizontal.addView(spinner_arrayList_main.get(spinner_arrayList_main.size() - 1), editParams);
            } else {
                spinner_arrayList_free.add(spProduct);
                linear_horizontal.addView(spinner_arrayList_free.get(spinner_arrayList_free.size() - 1), editParams);
            }

        }

    }


    @Override
    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

        switch (parentView.getId()) {
            case R.id.sp_brand:
                check_brand++;
                if (check_brand > 1) {
                    if (c_jsonObject == null) {
                        selectedBrandReference = brandAdapter.getItem(position).getBrand_reference();
                    }
                    selectedBrandID = brandAdapter.getItem(position).getBrand_id();
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
//            case R.id.tv_name:
//                edit_name.requestFocus();
//                break;
//            case R.id.tv_qty:
//                edit_qty.requestFocus();
//                break;
//
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
//            case R.id.tv_selected_expiry_date:
//                startActivityForResult(new Intent(CreatePromotionActivity.this, datePickerActivity.class), 1);
//                break;

            case R.id.tv_proceed:
                proceedToNextStep();
                break;

            default:
                break;
        }
    }


    public void proceedToNextStep() {

        try {
            JSONObject promotionObject = new JSONObject();

            JSONArray productArray = new JSONArray();
            for (int i = 0; i < edit_arrayList_main.size(); i = i + 1) {
                Logger.log("--------------- product main ---------------", "--------------- product main ---------------");
                JSONObject productObject = new JSONObject();

                String str_product = ProductAdapter.getItem(spinner_arrayList_main.get(i).getSelectedItemPosition()).getProduct_id();
                String str_qty = edit_arrayList_main.get(i).getText().toString();

                if (str_product.length() != 0 && str_qty.length() != 0) {
                    productObject.put("product_id", str_product);
                    productObject.put("qty", str_qty);

                    productArray.put(productObject);
                }

            }

            JSONArray productFreeArray = new JSONArray();
            for (int i = 0; i < edit_arrayList_free.size(); i = i + 1) {
                Logger.log("--------------- product free ---------------", "--------------- product free ---------------");
                JSONObject productObject = new JSONObject();

                String str_product = ProductAdapter.getItem(spinner_arrayList_free.get(i).getSelectedItemPosition()).getProduct_id();
                String str_qty = edit_arrayList_free.get(i).getText().toString();

                if (str_product.length() != 0 && str_qty.length() != 0) {
                    productObject.put("product_id", str_product);
                    productObject.put("qty", str_qty);

                    productFreeArray.put(productObject);
                }
            }

            promotionObject.put("promotion_type", "free");
            promotionObject.put("brand_id", selectedBrandID);
            promotionObject.put("brand_reference", selectedBrandReference);
            promotionObject.put("products", productArray);
            promotionObject.put("products_free", productFreeArray);

            Logger.log("bigObject", String.valueOf(promotionObject));

            if (selectedBrandID == "0") {
                AppUtility.showToast(context, getString(R.string.brand_requirement));
            } else if (productArray.length() == 0) {
                AppUtility.showToast(context, getString(R.string.main_product_array_requirement));
            } else if (productFreeArray.length() == 0) {
                AppUtility.showToast(context, getString(R.string.free_product_array_requirement));
            } else {
                startActivityForResult(new Intent(CreateFreePromotionActivity.this, CreateProductActivity.class).putExtra("promotion", promotionObject.toString()), 1);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void fillArrays() {
        fillbrand();

    }

    public void fillbrand() {
        try {

            arrayListBrand = new ArrayList<BrandModel>();
            String jsonLocation = AssetJSONFile("json/getBrand.json", context);

            JSONObject jsonObject = new JSONObject(jsonLocation);
            if (jsonObject.has("brand")) {
                JSONArray jsonArray = (JSONArray) jsonObject.getJSONArray("brand");
                for (int i = 0; i < jsonArray.length(); i++) {
                    BrandModel brandModel = new BrandModel();
                    JSONObject jb = (JSONObject) jsonArray.get(i);
                    brandModel.id = jb.getString("id");
                    brandModel.name = jb.getString("name");
                    brandModel.reference = jb.getString("reference");

                    arrayListBrand.add(brandModel);
                }

                brandAdapter = new ArrayAdapter<BrandModel>(CreateFreePromotionActivity.this, R.layout.spinner_text, arrayListBrand);
                brandAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                spBrand.setAdapter(brandAdapter);


            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

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
