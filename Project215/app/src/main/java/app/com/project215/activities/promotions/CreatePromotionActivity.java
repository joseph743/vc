package app.com.project215.activities.promotions;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import org.json.JSONObject;

import app.com.project215.R;

public class CreatePromotionActivity extends AppCompatActivity implements View.OnClickListener {
    String edit_brand_firstTime = "1", edit_category_firstTime = "1", edit_product_type_firstTime = "1";
    int delete_counter = 1;
    private Context context;
    private ProgressDialog mDialog;
    String selectedRoleId;
    ImageView deleteBtn;



    JSONObject c_jsonObject = null;
    Bundle extras = null;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_create);

        context = CreatePromotionActivity.this;

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
//
//
//        spBrand.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
//                // your code here
//
//                if (c_jsonObject != null) {
//                    if (edit_brand_firstTime != "1") {
//                        selectedBrandID = brandAdapter.getItem(position).getBrand_id();
//                        text_static_code.setText(brandAdapter.getItem(position).getBrand_reference());
//                        edit_code.requestFocus();
//                    } else {
//                        edit_brand_firstTime = "2";
//                    }
//                } else {
//                    selectedBrandID = brandAdapter.getItem(position).getBrand_id();
//                    text_static_code.setText(brandAdapter.getItem(position).getBrand_reference());
//                    edit_code.requestFocus();
//                }
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parentView) {
//                // your code here
//            }
//
//        });



    }


    /**
     * Initialize the view
     */

    private void initView() {


        deleteBtn = (ImageView) findViewById(R.id.img_delete);

        mDialog = new ProgressDialog(context);



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

            default:
                break;
        }
    }



    public void fillArrays() {
      //  fillbrand();

    }

//    public void fillbrand() {
//        try {
//
//            arrayListBrand = new ArrayList<BrandModel>();
//            String jsonLocation = AssetJSONFile("json/getBrand.json", context);
//
//            JSONObject jsonObject = new JSONObject(jsonLocation);
//            if (jsonObject.has("brand")) {
//                JSONArray jsonArray = (JSONArray) jsonObject.getJSONArray("brand");
//                for (int i = 0; i < jsonArray.length(); i++) {
//                    BrandModel brandModel = new BrandModel();
//                    JSONObject jb = (JSONObject) jsonArray.get(i);
//                    brandModel.id = jb.getString("id");
//                    brandModel.name = jb.getString("name");
//                    brandModel.reference = jb.getString("reference");
//
//                    arrayListBrand.add(brandModel);
//                }
//
//                brandAdapter = new ArrayAdapter<BrandModel>(CreatePromotionActivity.this, R.layout.spinner_text, arrayListBrand);
//                brandAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
//                spBrand.setAdapter(brandAdapter);
//
//
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//    }






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
