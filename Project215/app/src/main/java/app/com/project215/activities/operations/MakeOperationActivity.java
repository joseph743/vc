package app.com.project215.activities.operations;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
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
import app.com.project215.model.LocationModel;
import app.com.project215.model.ProductModel;
import app.com.project215.util.AppUtility;
import app.com.project215.util.Logger;

import static app.com.project215.util.AppUtility.AssetJSONFile;

public class MakeOperationActivity extends AppCompatActivity implements View.OnClickListener, Spinner.OnItemSelectedListener {
    int check_source = 0, check_destination = 0;
    int delete_counter = 1;
    private Context context;
    private ProgressDialog mDialog;
    String selectedRoleId;
    ImageView deleteBtn;

    LinearLayout linear_source, linear_destination, linear_date, linear_parent, linear_product, linear_horizontal, linear_big_parent;
    TextView text_numberOfProduct, text_addMoreProduct, text_product;
    EditText edit_product;
    Spinner spProduct;
    ArrayList arrayListProduct;
    ArrayAdapter<ProductModel> ProductAdapter;

    TextView text_submit, text_selected_date, text_assigne, text_route;

    TextView text_select_source;
    Spinner spSource;
    String selectedSourceID = "0";
    ArrayList arrayListSource;
    ArrayAdapter<LocationModel> SourceAdapter;

    TextView text_select_destination;
    Spinner spDestination;
    String selectedDestinationID = "0";
    ArrayList arrayListDestination;
    ArrayAdapter<LocationModel> DestinationAdapter;

    TextView text_page_title;
    String str_date;

    ArrayList<EditText> edit_arrayList = new ArrayList<EditText>();
    ArrayList<TextView> text_arrayList = new ArrayList<TextView>();
    ArrayList<Spinner> spinner_arrayList = new ArrayList<Spinner>();


    JSONObject c_jsonObject = null;
    Bundle extras = null;
    String pageType;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_operation);

        context = MakeOperationActivity.this;


        extras = getIntent().getExtras();

        if (extras != null) {

            if (getIntent().hasExtra("operation")) {
                pageType = getIntent().getStringExtra("operation");
                selectedRoleId = null;
            } else if (getIntent().hasExtra("operation_detail")) {
                try {
                    c_jsonObject = new JSONObject(getIntent().getStringExtra("operation_detail"));
                    selectedRoleId = c_jsonObject.getString("role_id");
                    Logger.log("c_jsonObject", c_jsonObject.toString());
                    pageType = c_jsonObject.getString("type");

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }

        initView();

        fillArrays();

    }


    /**
     * Initialize the view
     */

    private void initView() {
        linear_big_parent = (LinearLayout) findViewById(R.id.ll_bigParent);
        linear_parent = (LinearLayout) findViewById(R.id.ll_parent);
        linear_source = (LinearLayout) findViewById(R.id.ll_source);
        linear_destination = (LinearLayout) findViewById(R.id.ll_destination);
        linear_date = (LinearLayout) findViewById(R.id.ll_date);

        text_page_title = (TextView) findViewById(R.id.tv_page_title);

        deleteBtn = (ImageView) findViewById(R.id.img_delete);

        mDialog = new ProgressDialog(context);


        text_selected_date = (TextView) findViewById(R.id.tv_selected_date);
        text_selected_date.setOnClickListener(this);

        text_select_source = (TextView) findViewById(R.id.tv_select_source);
        text_select_source.setOnClickListener(this);
        spSource = (Spinner) findViewById(R.id.sp_source);
        spSource.setOnItemSelectedListener(this);


        text_select_destination = (TextView) findViewById(R.id.tv_select_destination);
        text_select_destination.setOnClickListener(this);
        spDestination = (Spinner) findViewById(R.id.sp_destination);
        spDestination.setOnItemSelectedListener(this);


        // if (pageType.contains("external")) {
        if (selectedRoleId != null ? selectedRoleId.contains("1") : pageType.contains("external")) {
            linear_parent.removeView(linear_source);
            linear_parent.removeView(linear_destination);
            linear_parent.removeView(linear_date);
        }

        text_submit = (TextView) findViewById(R.id.tv_submit);
        text_submit.setOnClickListener(this);

        text_assigne = (TextView) findViewById(R.id.tv_assigne);
        text_assigne.setOnClickListener(this);

        text_route = (TextView) findViewById(R.id.tv_route);
        text_route.setOnClickListener(this);


        //edit here
        if (c_jsonObject != null) {

            try {
                Logger.log("c_jsonObject", c_jsonObject.toString());
                selectedRoleId = c_jsonObject.getString("role_id");


                //source
                text_select_source.setVisibility(View.VISIBLE);
                text_select_source.setText(c_jsonObject.getString("source_name"));

                //destination
                text_select_destination.setVisibility(View.VISIBLE);
                text_select_destination.setText(c_jsonObject.getString("destination_name"));

                //date
                text_selected_date.setText(c_jsonObject.getString("date"));
                text_selected_date.setEnabled(false);

                //products
                JSONObject jsonObject = new JSONObject(c_jsonObject.toString());
                if (jsonObject.has("products")) {
                    JSONArray jsonArray = (JSONArray) jsonObject.getJSONArray("products");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jb = (JSONObject) jsonArray.get(i);
                        drowProduct(i + 1, jb);
                    }
                }

                //change title
                text_page_title.setText("Operation Status");

                //remove submit btn
                linear_big_parent.removeView(text_submit);

                //if not driver manager and status not pending remove assigne btn
                if (!(c_jsonObject.getString("role_id").contains("5") && c_jsonObject.getString("status").toLowerCase().contains("pending") && c_jsonObject.getString("driver_name").length() == 0)) {
                    linear_big_parent.removeView(text_assigne);
                }

                if (!(c_jsonObject.getString("role_id").contains("4") && c_jsonObject.getString("status").toLowerCase().contains("transferring"))) {
                    linear_big_parent.removeView(text_route);
                }

                if (c_jsonObject.getString("role_id").contains("5")) { // driver manager


                } else if (c_jsonObject.getString("role_id").contains("3")) { // warehouse manager just can view


                    //                    edit_name.setEnabled(false);
                    //                    edit_qty.setEnabled(false);
                    //
                    //                    edit_name.setTextColor(Color.GRAY);
                    //                    edit_qty.setTextColor(Color.GRAY);
                    //                    edit_cost.setTextColor(Color.GRAY);


                } else if (c_jsonObject.getString("role_id").contains("2")) { // inventory manager can do anything

//         deleteBtn.setVisibility(View.VISIBLE);
//         deleteBtn.setOnClickListener(this);

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            linear_big_parent.removeView(text_assigne);
            linear_big_parent.removeView(text_route);
            drowProduct(1, null);
        }


    }

    public void drowProduct(final int index, final JSONObject jObject) {

        //linear_location
        linear_product = new LinearLayout(this);
        LinearLayout.LayoutParams LinearlayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        LinearlayoutParams.setMargins(AppUtility.dpToPx(this, 0), AppUtility.dpToPx(this, 10), AppUtility.dpToPx(this, 0), AppUtility.dpToPx(this, 0));
        linear_product.setOrientation(LinearLayout.VERTICAL);
        linear_parent.addView(linear_product, LinearlayoutParams);

        //text_numberOfLocation
        text_numberOfProduct = new TextView(this);
        text_numberOfProduct.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        text_numberOfProduct.setGravity(Gravity.CENTER | Gravity.LEFT);
        text_numberOfProduct.setPadding(AppUtility.dpToPx(this, 10), AppUtility.dpToPx(this, 10), AppUtility.dpToPx(this, 10), AppUtility.dpToPx(this, 0));
        text_numberOfProduct.setText("Product (" + index + ")");
        text_numberOfProduct.setTextColor(Color.BLACK);
        text_numberOfProduct.setTextSize(14);
        linear_product.addView(text_numberOfProduct);


        try {
            drowProductRow("Product", jObject != null ? jObject.getString("products_name") : " ");
            drowProductRow("Quantity", jObject != null ? jObject.getString("qty") : " ");
        } catch (JSONException e) {

        }
//
        //add more products
        if (jObject == null) {
            text_addMoreProduct = new TextView(this);
            text_addMoreProduct.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            text_addMoreProduct.setPadding(AppUtility.dpToPx(this, 10), AppUtility.dpToPx(this, 20), AppUtility.dpToPx(this, 10), AppUtility.dpToPx(this, 10));
            text_addMoreProduct.setText("Add more products");
            text_addMoreProduct.setTextColor(Color.BLACK);
            text_addMoreProduct.setTextSize(14);
            text_arrayList.add(text_addMoreProduct);
            linear_product.addView(text_arrayList.get(text_arrayList.size() - 1));
            text_addMoreProduct.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    linear_product.removeView(text_addMoreProduct);
                    drowProduct(index + 1, null);
                }
            });
        }

    }


    public void drowProductRow(String text, String value) {


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

            edit_arrayList.add(edit_product);
            linear_horizontal.addView(edit_arrayList.get(edit_arrayList.size() - 1), editParams);
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
                        if (i == 0 || !(jb.getString("Product_type_id").contains("2") || jb.getString("Product_type_id").contains("3") || jb.getString("Product_type_id").contains("4") || jb.getString("Product_type_id").contains("5"))) {
                            productModel.id = jb.getString("id");
                            productModel.name = jb.getString("name");

                            arrayListProduct.add(productModel);
                        }


                    }//end for

                    ProductAdapter = new ArrayAdapter<ProductModel>(MakeOperationActivity.this, R.layout.spinner_text, arrayListProduct);
                    ProductAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                    spProduct.setAdapter(ProductAdapter);

                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }


            spinner_arrayList.add(spProduct);
            linear_horizontal.addView(spinner_arrayList.get(spinner_arrayList.size() - 1), editParams);

        }

    }


//    @Override
//    public boolean onTouch(final View view, MotionEvent motionEvent) {
//        spProduct.setOnItemSelectedListener(this);
//        return false;
//    }

    @Override
    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

        switch (parentView.getId()) {
            case R.id.sp_source:
                check_source++;
                if (check_source > 1) {
                    selectedSourceID = SourceAdapter.getItem(position).getLocation_id();
                }
                break;

            case R.id.sp_destination:
                check_destination++;
                if (check_destination > 1) {
                    selectedDestinationID = DestinationAdapter.getItem(position).getLocation_id();
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
            case R.id.tv_selected_date:
                startActivityForResult(new Intent(MakeOperationActivity.this, datePickerActivity.class).putExtra("date", "-"), 3);
                break;
            case R.id.tv_submit:
                createOperationEvent();
                break;
            case R.id.tv_assigne:
                assigneEvent();
                break;
            case R.id.tv_route:
                getRouteEvent();
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

        fillsource();
        filldestination();
    }

    public void fillsource() {
        try {

            arrayListSource = new ArrayList<LocationModel>();
            String jsonLocation = AssetJSONFile("json/getWarehouses.json", context);

            JSONObject jsonObject = new JSONObject(jsonLocation);
            if (jsonObject.has("warehouses")) {
                JSONArray jsonArray = (JSONArray) jsonObject.getJSONArray("warehouses");
                for (int i = 0; i < jsonArray.length(); i++) {

                    LocationModel locationModel = new LocationModel();
                    JSONObject jb = (JSONObject) jsonArray.get(i);

                    locationModel.id = jb.getString("id");
                    locationModel.name = jb.getString("name");

                    arrayListSource.add(locationModel);

                }//end for

                Logger.log("arrayListSource", String.valueOf(arrayListSource.size()));

                SourceAdapter = new ArrayAdapter<LocationModel>(MakeOperationActivity.this, R.layout.spinner_text, arrayListSource);
                SourceAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                spSource.setAdapter(SourceAdapter);

            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void filldestination() {
        try {

            arrayListDestination = new ArrayList<LocationModel>();
            String jsonLocation = AssetJSONFile("json/getWarehouses.json", context);

            JSONObject jsonObject = new JSONObject(jsonLocation);
            if (jsonObject.has("warehouses")) {
                JSONArray jsonArray = (JSONArray) jsonObject.getJSONArray("warehouses");
                for (int i = 0; i < jsonArray.length(); i++) {

                    LocationModel locationModel = new LocationModel();
                    JSONObject jb = (JSONObject) jsonArray.get(i);

                    locationModel.id = jb.getString("id");
                    locationModel.name = jb.getString("name");

                    arrayListDestination.add(locationModel);

                }//end for


                DestinationAdapter = new ArrayAdapter<LocationModel>(MakeOperationActivity.this, R.layout.spinner_text, arrayListDestination);
                DestinationAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                spDestination.setAdapter(DestinationAdapter);

            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void assigneEvent() {
        startActivity(new Intent(context, AssigneOperationActivity.class));
    }

    private void getRouteEvent() {
        try {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://maps.google.com/maps?q=" + c_jsonObject.getString("destination_latitude") + ',' + c_jsonObject.getString("destination_longitude")));
            startActivity(browserIntent);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void createOperationEvent() {

        if (pageType.contains("internal")) {
            Logger.log("selectedSourceID", selectedSourceID);
            Logger.log("selectedDestinationID", selectedDestinationID);
            str_date = text_selected_date.getText().toString().trim();
            Logger.log("str_date", str_date);

        } else if (pageType.contains("external")) {

            Logger.log("client location", "");
            str_date = AppUtility.currentDate();
            Logger.log("str_date location", str_date);
        }


        JSONArray productArray = new JSONArray();
        try {
            for (int i = 0; i < edit_arrayList.size(); i = i + 1) {
                JSONObject productObject = new JSONObject();
                //if product id is not 0 and qty is not ""
                if (ProductAdapter.getItem(spinner_arrayList.get(i).getSelectedItemPosition()).getProduct_id() != "0" && edit_arrayList.get(0).getText().toString().trim().length() != 0) {
                    productObject.put("product", ProductAdapter.getItem(spinner_arrayList.get(i).getSelectedItemPosition()).getProduct_id());
                    productObject.put("Quantity", edit_arrayList.get(i).getText().toString());
                    productArray.put(productObject);

                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Logger.log("productArray", productArray.toString());

        if (pageType.contains("internal") && selectedSourceID.contains("0")) {
            AppUtility.showToast(context, getString(R.string.warehouse_source_requirement));
        } else if (pageType.contains("internal") && selectedDestinationID.contains("0")) {
            AppUtility.showToast(context, getString(R.string.warehouse_destination_requirement));
        } else if (pageType.contains("internal") && str_date.contains("Select Date")) {
            AppUtility.showToast(context, getString(R.string.date_requirement));
        } else if (productArray.length() == 0) {
            AppUtility.showToast(context, getString(R.string.product_array_requirement));
        } else {
            AppUtility.showToast(context, c_jsonObject == null ? "callApiCreateOperation" : "callApiEditOperation");
            // callApiRegister();
        }
//        if (c_jsonObject != null) {
//            AppUtility.showToast(MakeOperationActivity.this, "EDIT MODE");
//            Logger.log("EDIT MODE", "ON");
//        } else {
//            AppUtility.showToast(MakeOperationActivity.this, "CREATE MODE");
//            Logger.log("CREATE MODE", "ON");
//        }


    }

    // received delete event from createProductActivity

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == 3) {
                text_selected_date.setText(data.getStringExtra("dateValue"));
            }
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
