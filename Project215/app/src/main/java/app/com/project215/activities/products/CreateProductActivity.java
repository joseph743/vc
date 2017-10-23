package app.com.project215.activities.products;


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
import app.com.project215.activities.datePickerActivity;
import app.com.project215.model.BrandModel;
import app.com.project215.model.CategoryModel;
import app.com.project215.util.AppUtility;
import app.com.project215.util.Logger;

import static app.com.project215.R.id.ll_category;
import static app.com.project215.R.id.ll_parent;
import static app.com.project215.R.id.tv_create_product;
import static app.com.project215.R.id.tv_select_brand;
import static app.com.project215.R.id.tv_select_category;
import static app.com.project215.R.id.tv_selected_expiry_date;

public class CreateProductActivity extends AppCompatActivity implements View.OnClickListener, Spinner.OnItemSelectedListener {
    int check_brand = 0, check_category = 0, check_product_type = 0;
    int delete_counter = 1;
    private Context context;
    private ProgressDialog mDialog;
    String selectedRoleId;
    ImageView deleteBtn;

    TextView text_product_type, text_static_product_type;
    TextView text_select_brand, text_select_category, sp_product_type, text_select_product_type;
    TextView text_static_code;
    TextView text_name, text_qty, text_cost, text_sale_price, text_weight, text_description, text_barcode, text_code, text_selected_expiry_date, text_selected_created_date, text_discount, text_old_price;
    EditText edit_name, edit_qty, edit_cost, edit_sale_price, edit_weight, edit_description, edit_barcode, edit_code, edit_discount, edit_old_price;
    String str_name, str_qty, str_cost, str_sale_price, str_weight, str_description, str_barcode, str_code, str_expiry_date, str_created_date, str_static_product_type;

    LinearLayout linear_discount, linear_old_price;

    Spinner spBrand;
    String selectedBrandID = "0";
    ArrayList arrayListBrand;
    ArrayAdapter<BrandModel> brandAdapter;

    Spinner spCategory;
    String selectedCategoryID = "0";
    ArrayList arrayListCategory;
    ArrayAdapter<CategoryModel> categoryAdapter;

//    Spinner spProductType;
//    String selectedProductTypeID = "0";
//    ArrayList arrayListProductType;
//    ArrayAdapter<ProductTypeModel> ProductTypeAdapter;


    TextView create_product, page_title;

    JSONObject c_jsonObject = null, c_promotionObject = null;
    Bundle extras = null;

    LinearLayout linear_parent;
    LinearLayout linear_location;
    TextView text_numberOfLocation;
    LinearLayout linear_horizontal;
    TextView text_location;
    EditText edit_location;
    TextView text_addMoreLocation;

    ArrayList<EditText> edit_arrayList = new ArrayList<EditText>();
    ArrayList<TextView> text_arrayList = new ArrayList<TextView>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_create);

        context = CreateProductActivity.this;

        try {
            extras = getIntent().getExtras();
            if (extras != null) {
                if (getIntent().hasExtra("product")) {
                    c_jsonObject = new JSONObject(getIntent().getStringExtra("product"));
                    selectedRoleId = c_jsonObject.getString("role_id");
                } else if (getIntent().hasExtra("promotion")) {
                    c_promotionObject = new JSONObject(getIntent().getStringExtra("promotion"));
                }
            }
        } catch (JSONException e) {

        }

        initView();
        fillArrays();


        Logger.log("the end", "the end");

    }


    /**
     * Initialize the view
     */

    private void initView() {


        linear_parent = (LinearLayout) findViewById(ll_parent);

        linear_discount = (LinearLayout) findViewById(R.id.ll_discount);
        linear_old_price = (LinearLayout) findViewById(R.id.ll_old_price);


        page_title = (TextView) findViewById(R.id.tv_page_title);

        text_selected_expiry_date = (TextView) findViewById(tv_selected_expiry_date);
        text_selected_expiry_date.setOnClickListener(this);

        text_selected_created_date = (TextView) findViewById(R.id.tv_selected_created_date);
        text_selected_created_date.setOnClickListener(this);

        text_select_brand = (TextView) findViewById(tv_select_brand);
        text_select_brand.setOnClickListener(this);

        text_select_category = (TextView) findViewById(tv_select_category);
        text_select_category.setOnClickListener(this);

//        text_select_product_type = (TextView) findViewById(tv_select_product_type);
//        text_select_product_type.setOnClickListener(this);

        spBrand = (Spinner) findViewById(R.id.sp_brand);
        spBrand.setOnItemSelectedListener(this);

        spCategory = (Spinner) findViewById(R.id.sp_category);
        spCategory.setOnItemSelectedListener(this);

        //   spProductType = (Spinner) findViewById(R.id.sp_product_type);
        // spProductType.setOnItemSelectedListener(this);

        text_static_product_type = (TextView) findViewById(R.id.tv_static_product_type);

        text_code = (TextView) findViewById(R.id.tv_code);
        text_code.setOnClickListener(this);
        edit_code = (EditText) findViewById(R.id.ed_code);
        text_static_code = (TextView) findViewById(R.id.tv_static_code);
        text_static_code.setOnClickListener(this);

        deleteBtn = (ImageView) findViewById(R.id.img_delete);

        text_name = (TextView) findViewById(R.id.tv_name);
        text_name.setOnClickListener(this);
        edit_name = (EditText) findViewById(R.id.ed_name);

        text_qty = (TextView) findViewById(R.id.tv_qty);
        text_qty.setOnClickListener(this);
        edit_qty = (EditText) findViewById(R.id.ed_qty);

        text_cost = (TextView) findViewById(R.id.tv_cost);
        text_cost.setOnClickListener(this);
        edit_cost = (EditText) findViewById(R.id.ed_cost);

        text_sale_price = (TextView) findViewById(R.id.tv_sale_price);
        text_sale_price.setOnClickListener(this);
        edit_sale_price = (EditText) findViewById(R.id.ed_sale_price);

        text_weight = (TextView) findViewById(R.id.tv_weight);
        text_weight.setOnClickListener(this);
        edit_weight = (EditText) findViewById(R.id.ed_weight);

        text_description = (TextView) findViewById(R.id.tv_description);
        text_description.setOnClickListener(this);
        edit_description = (EditText) findViewById(R.id.ed_description);

        text_barcode = (TextView) findViewById(R.id.tv_barcode);
        text_barcode.setOnClickListener(this);
        edit_barcode = (EditText) findViewById(R.id.ed_barcode);

        create_product = (TextView) findViewById(R.id.tv_create_product);
        create_product.setOnClickListener(this);

        mDialog = new ProgressDialog(context);


        //edit here
        if (c_jsonObject != null) {


            try {

                //add to promotion discount , old price
                if (c_jsonObject.getString("Product_type_id").contains("1")) {

                    if (c_jsonObject.has("discount")) {
                        text_discount = (TextView) findViewById(R.id.tv_discount);
                        text_discount.setOnClickListener(this);
                        edit_discount = (EditText) findViewById(R.id.ed_discount);
                        edit_discount.setText(c_jsonObject.getString("discount"));
                    } else {
                        linear_parent.removeView(linear_discount);
                    }

                    text_old_price = (TextView) findViewById(R.id.tv_old_price);
                    text_old_price.setOnClickListener(this);
                    edit_old_price = (EditText) findViewById(R.id.ed_old_price);

                    edit_old_price.setText(c_jsonObject.getString("old_price"));

                } else {
                    linear_parent.removeView(linear_discount);
                    linear_parent.removeView(linear_old_price);
                }


                Logger.log("c_jsonObject", c_jsonObject.toString());
                selectedRoleId = c_jsonObject.getString("role_id");

                edit_name.setText(c_jsonObject.getString("name"));
                edit_qty.setText(c_jsonObject.getString("total_quantity"));
                edit_cost.setText(c_jsonObject.getString("cost"));
                edit_sale_price.setText(c_jsonObject.getString("sale_price"));
                edit_weight.setText(c_jsonObject.getString("weight"));
                edit_description.setText(c_jsonObject.getString("description"));
                edit_barcode.setText(c_jsonObject.getString("barcode"));
                text_selected_expiry_date.setText(c_jsonObject.getString("expiry_date"));
                text_selected_created_date.setText(c_jsonObject.getString("created_date"));

                //brand
                text_select_brand.setVisibility(View.VISIBLE);
                text_select_brand.setText(c_jsonObject.getString("brand_name"));
                selectedBrandID = c_jsonObject.getString("brand_id");

                //code
                if (c_jsonObject.getString("code").contains(c_jsonObject.getString("brand_reference"))) {
                    text_static_code.setText(c_jsonObject.getString("brand_reference"));
                    edit_code.setText(c_jsonObject.getString("code").replace(c_jsonObject.getString("brand_reference"), ""));
                }

                //category
                text_select_category.setVisibility(View.VISIBLE);
                text_select_category.setText(c_jsonObject.getString("category_name"));
                selectedCategoryID = c_jsonObject.getString("category_id");

                //product type
                //text_select_product_type.setVisibility(View.VISIBLE);
                //text_select_product_type.setText(c_jsonObject.getString("Product_type_name"));
                //selectedProductTypeID = c_jsonObject.getString("Product_type_id");
                text_static_product_type.setText(c_jsonObject.getString("Product_type_name"));

                //create product
                create_product.setText("Edit Product");

                //when it is free EDIT
                if (c_jsonObject.getString("sale_price").contains("-")) {
                    edit_sale_price.setEnabled(false);
                    edit_sale_price.setTextColor(Color.GRAY);
                    edit_sale_price.setTextColor(Color.BLACK);
                    text_description.setText("Reason");
                }

                //promotion mode EDIT
                if (c_jsonObject.getString("Product_type_id").contains("1")) {

                    text_select_brand.setEnabled(false);
                    text_select_category.setEnabled(false);
                    edit_weight.setEnabled(false);
                    text_select_brand.setTextColor(Color.GRAY);
                    text_select_category.setTextColor(Color.GRAY);
                    edit_weight.setTextColor(Color.GRAY);
                }

                //location
                JSONObject jsonObject = new JSONObject(c_jsonObject.toString());
                if (jsonObject.has("location")) {
                    JSONArray jsonArray = (JSONArray) jsonObject.getJSONArray("location");
                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject jb = (JSONObject) jsonArray.get(i);
                        // Logger.log("ewewew",jb.getString("corridor"));

                        drowLocation(i + 1, jb, jsonArray.length());

                    }
                }

                if (c_jsonObject.getString("role_id").contains("3")) { // warehouse manager just can view


                    edit_name.setEnabled(false);
                    //  edit_qty.setEnabled(false);
                    edit_cost.setEnabled(false);
                    edit_sale_price.setEnabled(false);
                    edit_weight.setEnabled(false);
                    edit_description.setEnabled(false);
                    edit_barcode.setEnabled(false);
                    edit_code.setEnabled(false);
                    text_select_brand.setEnabled(false);
                    text_select_category.setEnabled(false);
//                    text_select_product_type.setEnabled(false);
                    text_selected_expiry_date.setEnabled(false);
                    text_selected_created_date.setEnabled(false);


                    if (edit_discount instanceof EditText) {
                        edit_discount.setEnabled(false);
                        edit_discount.setTextColor(Color.GRAY);
                    }
                    if (edit_old_price instanceof EditText) {
                        edit_old_price.setEnabled(false);
                        edit_old_price.setTextColor(Color.GRAY);
                    }

                    edit_name.setTextColor(Color.GRAY);
                    //  edit_qty.setTextColor(Color.GRAY);
                    edit_cost.setTextColor(Color.GRAY);
                    edit_sale_price.setTextColor(Color.GRAY);
                    edit_weight.setTextColor(Color.GRAY);
                    edit_description.setTextColor(Color.GRAY);
                    edit_barcode.setTextColor(Color.GRAY);
                    edit_code.setTextColor(Color.GRAY);
                    text_static_code.setTextColor(Color.GRAY);
                    text_select_brand.setTextColor(Color.GRAY);
                    text_select_category.setTextColor(Color.GRAY);
//                    text_select_product_type.setTextColor(Color.GRAY);
                    text_selected_expiry_date.setTextColor(Color.GRAY);
                    text_selected_created_date.setTextColor(Color.GRAY);


                } else if (c_jsonObject.getString("role_id").contains("2")) { // inventory manager can do anything

                    for (int i = 0; i < edit_arrayList.size(); i++) {
                        edit_arrayList.get(i).setEnabled(false);
                        edit_arrayList.get(i).setTextColor(Color.GRAY);
                    }
                    for (int i = 0; i < text_arrayList.size(); i++) {
                        text_arrayList.get(i).setEnabled(false);
                        text_arrayList.get(i).setTextColor(Color.GRAY);
                    }

                    deleteBtn.setVisibility(View.VISIBLE);
                    deleteBtn.setOnClickListener(this);

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else if (c_promotionObject != null) {
            Logger.log("c_promotionObject", c_promotionObject.toString());
            //{"brand_id":"2","brand_reference":"5900","promotion_type":"discount","discount":"866","products":[{"product_id":"2","qty":"6"}]}
            LinearLayout linear_category, linear_expiry_date, linear_manifacture_date, linear_weight, linear_brand, linear_cost, linear_sale_price;
            linear_category = (LinearLayout) findViewById(ll_category);
            linear_expiry_date = (LinearLayout) findViewById(R.id.ll_expiry_date);
            linear_manifacture_date = (LinearLayout) findViewById(R.id.ll_manifacture_date);
            linear_weight = (LinearLayout) findViewById(R.id.ll_weight);
            linear_brand = (LinearLayout) findViewById(R.id.ll_brand);
            linear_cost = (LinearLayout) findViewById(R.id.ll_cost);
            linear_sale_price = (LinearLayout) findViewById(R.id.ll_sale_price);
            text_static_product_type.setText("Promotion");
            create_product.setText("Make Promotion");
            page_title.setText("Step 2 : Fill Promotion Details");


            try {
                text_static_code.setText(c_promotionObject.getString("brand_reference"));

                selectedBrandID = c_promotionObject.getString("brand_id");

//                JSONArray productArray = (JSONArray) c_disountObject.getJSONArray("products");
//                double costSum = 0;
//                for (int i = 0; i < productArray.length(); i++) {
//                    JSONObject jb = (JSONObject) productArray.get(i);
//                    costSum = costSum+ Double.valueOf(jb.getString("cost"));
//                }
//                text_cost.setText(String.valueOf(costSum));

            } catch (JSONException e) {
                e.printStackTrace();
            }


            linear_parent.removeView(linear_category);
            linear_parent.removeView(linear_expiry_date);
            linear_parent.removeView(linear_manifacture_date);
            linear_parent.removeView(linear_weight);
            linear_parent.removeView(linear_brand);
            linear_parent.removeView(linear_cost);
            linear_parent.removeView(linear_old_price);
            linear_parent.removeView(linear_discount);
            linear_parent.removeView(linear_sale_price);

            drowLocation(1, null, 1);

        } else {

            linear_parent.removeView(linear_discount);
            linear_parent.removeView(linear_old_price);

            drowLocation(1, null, 1);
        }


    }

    public void drowLocation(final int index, final JSONObject jObject, int fullLength) {

        //linear_location
        linear_location = new LinearLayout(this);
        LinearLayout.LayoutParams LinearlayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        LinearlayoutParams.setMargins(AppUtility.dpToPx(this, 0), AppUtility.dpToPx(this, 10), AppUtility.dpToPx(this, 0), AppUtility.dpToPx(this, 0));
        linear_location.setOrientation(LinearLayout.VERTICAL);


        linear_parent.addView(linear_location, LinearlayoutParams);

        //text_numberOfLocation
        text_numberOfLocation = new TextView(this);
        text_numberOfLocation.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        text_numberOfLocation.setGravity(Gravity.CENTER);
        text_numberOfLocation.setPadding(AppUtility.dpToPx(this, 10), AppUtility.dpToPx(this, 10), AppUtility.dpToPx(this, 10), AppUtility.dpToPx(this, 0));
        text_numberOfLocation.setText("Location " + index + " of product in warehouse");
        text_numberOfLocation.setTextColor(Color.BLACK);
        text_numberOfLocation.setTextSize(16);
        linear_location.addView(text_numberOfLocation);


        try {
            drowLocationRow("Corridor", jObject != null ? jObject.getString("corridor") : " ");
            drowLocationRow("Shelf", jObject != null ? jObject.getString("shelf") : " ");
            drowLocationRow("Height", jObject != null ? jObject.getString("height") : " ");
            drowLocationRow("Warehouse Name", jObject != null ? jObject.getString("warehouse_name") : " ");
            drowLocationRow("Quantity", jObject != null ? jObject.getString("quantity") : " ");
        } catch (JSONException e) {

        }

        if (index >= fullLength) {
            //add more location
            text_addMoreLocation = new TextView(this);
            text_addMoreLocation.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            text_addMoreLocation.setPadding(AppUtility.dpToPx(this, 10), AppUtility.dpToPx(this, 20), AppUtility.dpToPx(this, 10), AppUtility.dpToPx(this, 10));
            text_addMoreLocation.setText("Add more locations");
            text_addMoreLocation.setTextColor(Color.BLACK);
            text_addMoreLocation.setTextSize(14);
            text_arrayList.add(text_addMoreLocation);
            linear_location.addView(text_arrayList.get(text_arrayList.size() - 1));
            text_addMoreLocation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    linear_location.removeView(text_addMoreLocation);
                    drowLocation(index + 1, null, 1);
                }
            });
        }


    }

    public void drowLocationRow(String text, String value) {


        //linear_horizontal
        linear_horizontal = new LinearLayout(this);
        LinearLayout.LayoutParams LinearHorizontalParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        LinearHorizontalParams.setMargins(AppUtility.dpToPx(this, 0), AppUtility.dpToPx(this, 10), AppUtility.dpToPx(this, 0), AppUtility.dpToPx(this, 0));
        linear_horizontal.setOrientation(LinearLayout.HORIZONTAL);
        linear_horizontal.setTag(2);
        linear_horizontal.setBackground(getResources().getDrawable(R.drawable.edittext_bg));
        linear_location.addView(linear_horizontal, LinearHorizontalParams);

        //text_location
        text_location = new TextView(this);
        LinearLayout.LayoutParams textParams = new LinearLayout.LayoutParams(AppUtility.dpToPx(this, 130), LinearLayout.LayoutParams.WRAP_CONTENT);
        textParams.setMargins(AppUtility.dpToPx(this, 0), AppUtility.dpToPx(this, 0), AppUtility.dpToPx(this, 0), AppUtility.dpToPx(this, 2));
        text_location.setPadding(AppUtility.dpToPx(this, 10), AppUtility.dpToPx(this, 10), AppUtility.dpToPx(this, 10), AppUtility.dpToPx(this, 10));
        text_location.setText(text);
        text_location.setBackgroundColor(Color.WHITE);
        text_location.setTextColor(Color.BLACK);
        text_location.setTextSize(14);
        linear_horizontal.addView(text_location, textParams);


        //edit_location
        edit_location = new EditText(this);
        LinearLayout.LayoutParams editParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        editParams.setMargins(AppUtility.dpToPx(this, 0), AppUtility.dpToPx(this, 0), AppUtility.dpToPx(this, 0), AppUtility.dpToPx(this, 2));
        edit_location.setPadding(AppUtility.dpToPx(this, 10), AppUtility.dpToPx(this, 10), AppUtility.dpToPx(this, 10), AppUtility.dpToPx(this, 10));
        edit_location.setBackgroundColor(Color.WHITE);
        if (text == "Quantity") {
            edit_location.setInputType(InputType.TYPE_CLASS_NUMBER);
        } else {
            edit_location.setInputType(InputType.TYPE_CLASS_TEXT);
        }

        edit_location.setTextColor(Color.BLACK);
        edit_location.setTextSize(14);

        if (value != null) {
            edit_location.setText(value);
        }

        edit_arrayList.add(edit_location);
        linear_horizontal.addView(edit_arrayList.get(edit_arrayList.size() - 1), editParams);


    }

    @Override
    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

        switch (parentView.getId()) {
            case R.id.sp_brand:
                check_brand++;
                if (check_brand > 1) {
                    if (c_jsonObject != null) {
                        text_select_brand.setVisibility(View.INVISIBLE);
                    }
                    selectedBrandID = brandAdapter.getItem(position).getBrand_id();
                    text_static_code.setText(brandAdapter.getItem(position).getBrand_reference());
                    edit_code.requestFocus();
                }
                break;

            case R.id.sp_category:
                check_category++;
                if (check_category > 1) {
                    if (c_jsonObject != null) {
                        text_select_category.setVisibility(View.INVISIBLE);
                    }
                    selectedCategoryID = categoryAdapter.getItem(position).getCategory_id();
                }
                break;
//            case R.id.sp_product_type:
//                check_product_type++;
//                if (check_product_type > 1) {
//                    selectedProductTypeID = ProductTypeAdapter.getItem(position).getProductType_id();
//                }
//                break;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_name:
                edit_name.requestFocus();
                break;
            case R.id.tv_qty:
                edit_qty.requestFocus();
                break;
            case R.id.tv_cost:
                edit_cost.requestFocus();
                break;
            case R.id.tv_sale_price:
                edit_sale_price.requestFocus();
                break;
            case R.id.tv_weight:
                edit_weight.requestFocus();
                break;
            case R.id.tv_barcode:
                edit_barcode.requestFocus();
                break;
            case R.id.tv_description:
                edit_description.requestFocus();
                break;
            case R.id.tv_static_code:
                edit_code.requestFocus();
                break;
            case R.id.tv_code:
                edit_code.requestFocus();
                break;
            case tv_select_brand:
                spBrand.performClick();
//                text_select_brand.setVisibility(View.INVISIBLE);
                break;
            case tv_select_category:
                spCategory.performClick();
                //               text_select_category.setVisibility(View.INVISIBLE);
                break;
//            case tv_select_product_type:
//                spProductType.performClick();
//                text_select_product_type.setVisibility(View.INVISIBLE);
//                break;
            case tv_create_product:
                createProductEvent();
                break;
            case R.id.img_delete:
                if (delete_counter == 1) {
                    delete_counter++;
                    AppUtility.showToast(context, "Press a second time to delete Product");

                } else {
                    Intent intent = new Intent();
                    setResult(RESULT_OK, intent);
                    finish();
                }
                break;
            case R.id.tv_selected_expiry_date:
                startActivityForResult(new Intent(CreateProductActivity.this, datePickerActivity.class).putExtra("date", "expiry"), 1);
                break;
            case R.id.tv_selected_created_date:
                startActivityForResult(new Intent(CreateProductActivity.this, datePickerActivity.class).putExtra("date", "created"), 2);
                break;

            default:
                break;
        }
    }


    private void createProductEvent() {

        str_name = edit_name.getText().toString().trim();
        str_qty = edit_qty.getText().toString().trim();
        str_cost = edit_cost.getText().toString().trim();
        str_sale_price = edit_sale_price.getText().toString().trim();
        str_weight = edit_weight.getText().toString().trim();
        str_description = edit_description.getText().toString().trim();
        str_barcode = edit_barcode.getText().toString().trim();
        str_code = text_static_code.getText().toString().trim() + edit_code.getText().toString().trim();
        str_expiry_date = text_selected_expiry_date.getText().toString().trim();
        str_created_date = text_selected_created_date.getText().toString().trim();
        str_static_product_type = text_static_product_type.getText().toString().trim();

        Logger.log("str_name", str_name);
        Logger.log("str_qty", str_qty);
        Logger.log("str_cost", str_cost);
        Logger.log("str_sale_price", str_sale_price);
        Logger.log("str_weight", str_weight);
        Logger.log("str_description", str_description);
        Logger.log("str_barcode", str_barcode);
        Logger.log("str_code", str_code);
        Logger.log("selectedBrandID", selectedBrandID);
        Logger.log("selectedCategoryID", selectedCategoryID);
        // Logger.log("selectedProductTypeID", selectedProductTypeID);
        Logger.log("str_static_product_type", str_static_product_type);
        Logger.log("str_expiry_date", str_expiry_date);
        Logger.log("str_created_date", str_created_date);


        //fill location array
        JSONArray locationArray = new JSONArray();
        try {
            for (int i = 0; i < edit_arrayList.size(); i = i + 5) {
                JSONObject productObject = new JSONObject();
                String str_corridor = edit_arrayList.get(i).getText().toString().trim();
                String str_shelf = edit_arrayList.get(i + 1).getText().toString().trim();
                String str_height = edit_arrayList.get(i + 2).getText().toString().trim();
                String str_warehouse_name = edit_arrayList.get(i + 3).getText().toString().trim();
                String str_qty = edit_arrayList.get(i + 4).getText().toString().trim();

                if (str_corridor.length() != 0 && str_shelf.length() != 0 && str_height.length() != 0 && str_warehouse_name.length() != 0 && str_qty.length() != 0) {
                    productObject.put("corridor", str_corridor);
                    productObject.put("shelf", str_shelf);
                    productObject.put("height", str_height);
                    productObject.put("warehouse name", str_warehouse_name);
                    productObject.put("quantity", str_qty);
                    locationArray.put(productObject);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Logger.log("locationArray", locationArray.toString());

//        if (c_jsonObject.getString("Product_type_id").contains("1")) {
//
//            if (c_jsonObject.has("discount")) {
//
//            }

        try {
            if (selectedBrandID == "0") {
                AppUtility.showToast(context, getString(R.string.brand_requirement));
            } else if (edit_code.getText().toString().trim().length() == 0) {
                AppUtility.showToast(context, getString(R.string.complete_code_requirement));
            } else if (c_jsonObject != null ? (!c_jsonObject.getString("Product_type_id").contains("1") && selectedCategoryID == "0") : (c_promotionObject == null && selectedCategoryID == "0")) {
                AppUtility.showToast(context, getString(R.string.category_requirement));
            } else if (str_name.length() == 0) {
                AppUtility.showToast(context, getString(R.string.product_name_requirement));
            } else if (str_qty.length() == 0) {
                AppUtility.showToast(context, getString(R.string.qty_requirement));
            } else if (str_barcode.length() == 0) {
                AppUtility.showToast(context, getString(R.string.barcode_requirement));
            } else if (c_promotionObject == null && str_created_date.contains("Select Date")) {
                AppUtility.showToast(context, getString(R.string.date_manifacture_requirement));
            } else if (c_promotionObject == null && str_expiry_date.contains("Select Date")) {
                AppUtility.showToast(context, getString(R.string.date_expiry_requirement));
            } else if (c_promotionObject == null && str_cost.length() == 0) {
                AppUtility.showToast(context, getString(R.string.cost_requirement));
            } else if (c_jsonObject != null && c_promotionObject == null && c_jsonObject.getString("Product_type_id").contains("1") && edit_old_price.getText().toString().trim().length() == 0) {
                AppUtility.showToast(context, getString(R.string.old_price_requirement));
            } else if (c_jsonObject != null && c_promotionObject == null && c_jsonObject.getString("Product_type_id").contains("1") && c_jsonObject.has("discount") && edit_discount.getText().toString().trim().length() == 0) {
                AppUtility.showToast(context, getString(R.string.discount_requirement));
            } else if (c_promotionObject == null && str_sale_price.length() == 0) {
                AppUtility.showToast(context, getString(R.string.sale_price_requirement));
            } else if (c_promotionObject == null && str_weight.length() == 0) {
                AppUtility.showToast(context, getString(R.string.weight_requirement));
            } else if (str_description.length() == 0) {
                AppUtility.showToast(context, getString(R.string.description_requirement));
            } else if (locationArray.length() == 0) {
                AppUtility.showToast(context, getString(R.string.location_array_requirement));
            } else {
                AppUtility.showToast(context, c_jsonObject == null ? (c_promotionObject == null ? "callApiCreateProduct" : "callApiPromotion") : "callApiEdit");
                //     callApiRegister();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

//        if (c_jsonObject != null) {
//            AppUtility.showToast(CreateProductActivity.this, "EDIT MODE");
//            Logger.log("EDIT MODE", "ON");
//        } else {
//            AppUtility.showToast(CreateProductActivity.this, "CREATE MODE");
//            Logger.log("CREATE MODE", "ON");
//        }


    }


    public void fillArrays() {
        fillbrand();
        fillCategory();
        //  fillProductType();
    }

    public void fillbrand() {
        try {

            arrayListBrand = new ArrayList<BrandModel>();
            String jsonLocation = AppUtility.AssetJSONFile("json/getBrand.json", context);

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

                brandAdapter = new ArrayAdapter<BrandModel>(CreateProductActivity.this, R.layout.spinner_text, arrayListBrand);
                brandAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                spBrand.setAdapter(brandAdapter);


            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void fillCategory() {
        try {

            arrayListCategory = new ArrayList<CategoryModel>();
            String jsonLocation = AppUtility.AssetJSONFile("json/getCategory.json", context);

            JSONObject jsonObject = new JSONObject(jsonLocation);
            if (jsonObject.has("category")) {
                JSONArray jsonArray = (JSONArray) jsonObject.getJSONArray("category");
                for (int i = 0; i < jsonArray.length(); i++) {
                    CategoryModel categoryModel = new CategoryModel();
                    JSONObject jb = (JSONObject) jsonArray.get(i);
                    categoryModel.id = jb.getString("id");
                    categoryModel.name = jb.getString("name");


                    arrayListCategory.add(categoryModel);
                }

                categoryAdapter = new ArrayAdapter<CategoryModel>(CreateProductActivity.this, R.layout.spinner_text, arrayListCategory);
                categoryAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                spCategory.setAdapter(categoryAdapter);


            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

   /* public void fillProductType() {
        try {

            arrayListProductType = new ArrayList<ProductTypeModel>();
            String jsonLocation = AssetJSONFile("json/getProductType.json", context);

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

                ProductTypeAdapter = new ArrayAdapter<ProductTypeModel>(CreateProductActivity.this, R.layout.spinner_text, arrayListProductType);
                ProductTypeAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                spProductType.setAdapter(ProductTypeAdapter);

            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }*/

    // received delete event from createProductActivity
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                String expiryDate = data.getStringExtra("expiryDateValue");
                text_selected_expiry_date.setText(expiryDate);
            } else if (requestCode == 2) {
                String createdDate = data.getStringExtra("createdDateValue");
                text_selected_created_date.setText(createdDate);
            }
        }
    }


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
