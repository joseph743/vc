package app.com.project215.activities.warehouse;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import app.com.project215.AppController;
import app.com.project215.R;
import app.com.project215.activities.user.LocationMapActivity;
import app.com.project215.util.AppUtility;
import app.com.project215.util.Logger;


public class CreateWarehouseActivity extends AppCompatActivity implements View.OnClickListener {
    int delete_counter = 1;
    private Context context;
    private ProgressDialog mDialog;
    String selectedRoleId;
    ImageView deleteBtn;
    TextView location;
    String latitude, longitude;

    TextView text_name, text_address, text_description, text_city;
    EditText edit_name, edit_address, edit_description, edit_city;
    String str_name, str_address, str_description, str_city;

//    Spinner spCity;
//    String selectedCityID = "0";
//    ArrayList arrayListCity;
//    ArrayAdapter<CityRoleModel> cityAdapter;

    TextView create_warehouse;

    JSONObject jsonObject = null;
    Bundle extras = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warehouse_create);

        context = CreateWarehouseActivity.this;


        try {
            extras = getIntent().getExtras();
            if (extras != null)
                jsonObject = new JSONObject(getIntent().getStringExtra("warehouse"));

        } catch (JSONException e) {

        }


        initView();

        //   fillArrays();

    }


    /**
     * Initialize the view
     */
    private void initView() {

        deleteBtn = (ImageView) findViewById(R.id.img_delete);
        text_name = (TextView) findViewById(R.id.tv_name);
        text_name.setOnClickListener(this);
        edit_name = (EditText) findViewById(R.id.ed_name);

        text_address = (TextView) findViewById(R.id.tv_address);
        text_address.setOnClickListener(this);
        edit_address = (EditText) findViewById(R.id.ed_address);

        text_description = (TextView) findViewById(R.id.tv_description);
        text_description.setOnClickListener(this);
        edit_description = (EditText) findViewById(R.id.ed_description);

        text_city = (TextView) findViewById(R.id.tv_city);
        text_city.setOnClickListener(this);
        edit_city = (EditText) findViewById(R.id.ed_city);
//        spCity = (Spinner) findViewById(R.id.sp_city);

        location = (TextView) findViewById(R.id.tv_location);
        location.setOnClickListener(this);

        create_warehouse = (TextView) findViewById(R.id.tv_create_warehouse);
        create_warehouse.setOnClickListener(this);

        mDialog = new ProgressDialog(context);


        if (jsonObject != null) {
            try {
                selectedRoleId = jsonObject.getString("role_id");

                edit_name.setText(jsonObject.getString("name"));
                edit_address.setText(jsonObject.getString("address"));
                edit_description.setText(jsonObject.getString("description"));
                edit_city.setText(jsonObject.getString("city"));
                location.setText(("View Warehouse Location"));
                ((AppController) this.getApplication()).setLocation(Double.valueOf(jsonObject.getString("latitude")), Double.valueOf(jsonObject.getString("longitude")));

                if (jsonObject.getString("role_id").contains("3")) { // warehouse manager just can view

                    edit_name.setEnabled(false);
                    edit_address.setEnabled(false);
                    edit_description.setEnabled(false);
                    edit_city.setEnabled(false);

                    edit_name.setTextColor(Color.GRAY);
                    edit_address.setTextColor(Color.GRAY);
                    edit_description.setTextColor(Color.GRAY);
                    edit_city.setTextColor(Color.GRAY);


                    create_warehouse.setVisibility(View.INVISIBLE);
                } else if (jsonObject.getString("role_id").contains("2")) { // inventory manager can do anything

                    deleteBtn.setVisibility(View.VISIBLE);
                    deleteBtn.setOnClickListener(this);

                    ((AppController) this.getApplication()).setLocation(Double.valueOf(jsonObject.getString("latitude")), Double.valueOf(jsonObject.getString("longitude")));
                    create_warehouse.setText("Edit Warehouse");
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            //return to default lat long
            ((AppController) this.getApplication()).setLocation(33.888630, 35.495480);
        }


    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_name:
                edit_name.requestFocus();
                break;
            case R.id.tv_address:
                edit_address.requestFocus();
                break;
            case R.id.tv_description:
                edit_description.requestFocus();
                break;
            case R.id.tv_city:
                edit_city.requestFocus();
                break;
            case R.id.img_delete:

                if (delete_counter == 1) {
                    delete_counter++;
                    AppUtility.showToast(context, "Press a second time to delete warehouse");

                } else {
                    Intent intent = new Intent();
                    setResult(RESULT_OK, intent);
                    finish();
                }

                break;
            case R.id.tv_location:
                startActivityForResult(new Intent(this, LocationMapActivity.class).putExtra("role_id", selectedRoleId), 1);
                break;

            case R.id.tv_create_warehouse:
                createWarehouseEvent();
                break;
            default:
                break;
        }
    }

    private void createWarehouseEvent() {

        str_name = edit_name.getText().toString().trim();
        str_address = edit_address.getText().toString().trim();
        str_description = edit_description.getText().toString().trim();
        // selectedCityID = cityAdapter.getItem(spCity.getSelectedItemPosition()).getCity_id();
        str_city = edit_city.getText().toString().trim();
        latitude = String.valueOf(((AppController) this.getApplication()).returnLatitude());
        longitude = String.valueOf(((AppController) this.getApplication()).returnLongitude());
//
        Logger.log("str_name", str_name);
        Logger.log("str_address", str_address);
        Logger.log("str_description", str_description);
        Logger.log("str_city", str_city);
        Logger.log("latitude", latitude);
        Logger.log("longitude", longitude);

//        if (jsonObject != null) {
//            AppUtility.showToast(CreateWarehouseActivity.this, "EDIT MODE");
//            Logger.log("EDIT MODE", "ON");
//        }else{
//            AppUtility.showToast(CreateWarehouseActivity.this, "CREATE MODE");
//            Logger.log("CREATE MODE", "ON");
//        }


        if (str_name.length() == 0) {
            AppUtility.showToast(context, getString(R.string.name_requirement));
        } else if (str_address.length() == 0) {
            AppUtility.showToast(context, getString(R.string.address_requirement));
        } else if (str_description.length() == 0) {
            AppUtility.showToast(context, getString(R.string.description_requirement));
        } else if (str_city.length() == 0) {
            AppUtility.showToast(context, getString(R.string.city_requirement));
        } else if (latitude.contains("33.88863") && longitude.contains("35.49548")) {
            AppUtility.showToast(context, getString(R.string.location_requirement));
        } else {
            AppUtility.showToast(context, jsonObject == null ? "callApiCreateWarehouse" : "callApiEditWarehouse");
            // callApiRegister();
        }


    }

    // received location event from LocationMapActivity
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                String strEditText = data.getStringExtra("editTextValue");
                location.setText(strEditText);
                location.setTextColor(Color.RED);
            }
        }
    }

//    private void fillArrays() {
//
//
//        //Logger.log("callApiBusinessRoles response", response);
//
//
//        // JSONObject jsonObject = null;
//        try {
//            // jsonObject = new JSONObject(response);
//
//            JSONArray jsonArray;
//            JSONObject jsonSubObject;
//
//            arrayListCity = new ArrayList<CityRoleModel>();
//
//            JSONObject jsonObject = new JSONObject();
//
//            // static country
//            JSONObject countryObject0 = new JSONObject();
//            countryObject0.put("city_id", 0);
//            countryObject0.put("city_name", "Select City");
//
//            JSONObject countryObject1 = new JSONObject();
//            countryObject1.put("city_id", 1);
//            countryObject1.put("city_name", "Beirut");
//
//            JSONObject countryObject2 = new JSONObject();
//            countryObject2.put("city_id", 2);
//            countryObject2.put("city_name", "Dbayeh");
//
//            JSONArray countriesArray = new JSONArray();
//            countriesArray.put(countryObject0);
//            countriesArray.put(countryObject1);
//            countriesArray.put(countryObject2);
//
//
//            jsonObject.put("cities", countriesArray);
//            // end static country
//            if (jsonObject.has("cities")) {
//                jsonArray = jsonObject.getJSONArray("cities");
//                for (int i = 0; i < jsonArray.length(); i++) {
//                    CityRoleModel cityRoleModel = new CityRoleModel();
//                    jsonSubObject = jsonArray.getJSONObject(i);
//                    cityRoleModel.city_id = jsonSubObject.getString("city_id");
//                    cityRoleModel.city_name = jsonSubObject.getString("city_name");
//
//                    arrayListCity.add(cityRoleModel);
//                }
//
//                System.out.println("Arraylist Size : " + arrayListCity.size());
//
//                cityAdapter = new ArrayAdapter<CityRoleModel>(CreateWarehouseActivity.this, R.layout.spinner_text, arrayListCity);
//                cityAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
//                spCity.setAdapter(cityAdapter);
//            }
//
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//    }


    private Map<String, String> getParams() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("name", str_name);
        params.put("address", str_description);
        params.put("description", str_address);
        // params.put("city", selectedCityID);
        params.put("city", str_city);
        params.put("latitude", latitude);
        params.put("longitude", longitude);
        return params;
    }


}
