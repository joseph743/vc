package app.com.project215.activities.operations;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import app.com.project215.R;
import app.com.project215.activities.TimePickerActivity;
import app.com.project215.model.DriverModel;
import app.com.project215.util.AppUtility;
import app.com.project215.util.Logger;

public class AssigneOperationActivity extends AppCompatActivity implements View.OnClickListener, Spinner.OnItemSelectedListener {
    int check_driver = 0, check_destination = 0;
    int delete_counter = 1;
    private Context context;
    private ProgressDialog mDialog;
    String selectedRoleId;
    ImageView deleteBtn;


    TextView text_submit, text_selected_date, text_selected_start_time, text_selected_end_time;

    TextView text_select_driver;
    Spinner spDriver;
    String selectedDriverID = "0";
    ArrayList arrayListDriver;
    ArrayAdapter<DriverModel> DriverAdapter;


    TextView text_page_title;
    String str_date,str_start_time,str_end_time;


    JSONObject c_jsonObject = null;
    Bundle extras = null;
    String pageType;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assigne_operation);

        context = AssigneOperationActivity.this;


        extras = getIntent().getExtras();

//        if (extras != null) {
//
//            if (getIntent().hasExtra("operation")) {
//                pageType = getIntent().getStringExtra("operation");
//                selectedRoleId = null;
//            } else if (getIntent().hasExtra("operation_detail")) {
//                try {
//                    c_jsonObject = new JSONObject(getIntent().getStringExtra("operation_detail"));
//                    selectedRoleId = c_jsonObject.getString("role_id");
//                    Logger.log("c_jsonObject", c_jsonObject.toString());
//                    pageType = c_jsonObject.getString("type");
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        }

        initView();

        fillArrays();

    }


    /**
     * Initialize the view
     */

    private void initView() {


        text_page_title = (TextView) findViewById(R.id.tv_page_title);

        deleteBtn = (ImageView) findViewById(R.id.img_delete);

        mDialog = new ProgressDialog(context);


//        text_selected_date = (TextView) findViewById(R.id.tv_selected_date);
//        text_selected_date.setOnClickListener(this);

        text_selected_start_time = (TextView) findViewById(R.id.tv_selected_start_time);
        text_selected_start_time.setOnClickListener(this);

        text_selected_end_time = (TextView) findViewById(R.id.tv_selected_end_time);
        text_selected_end_time.setOnClickListener(this);

        text_select_driver = (TextView) findViewById(R.id.tv_select_driver);
        text_select_driver.setOnClickListener(this);
        spDriver = (Spinner) findViewById(R.id.sp_driver);
        spDriver.setOnItemSelectedListener(this);


        text_submit = (TextView) findViewById(R.id.tv_submit);
        text_submit.setOnClickListener(this);


        //edit here
        if (c_jsonObject != null) {

//            try {
//                Logger.log("c_jsonObject", c_jsonObject.toString());


//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
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
            case R.id.sp_driver:
                check_driver++;
                if (check_driver > 1) {
                    selectedDriverID = DriverAdapter.getItem(position).getDriver_id();
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
//            case R.id.tv_selected_date:
//                startActivityForResult(new Intent(AssigneOperationActivity.this, datePickerActivity.class).putExtra("date", "-"), 3);
//                break;

            case R.id.tv_selected_start_time:
                startActivityForResult(new Intent(AssigneOperationActivity.this, TimePickerActivity.class).putExtra("time", "-"), 4);
                break;

            case R.id.tv_selected_end_time:
                startActivityForResult(new Intent(AssigneOperationActivity.this, TimePickerActivity.class).putExtra("time", "-"), 5);
                break;

            case R.id.tv_submit:
                  createOperationEvent();
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

    public void createOperationEvent(){

       // str_date = text_selected_date.getText().toString().trim();
        str_start_time = text_selected_start_time.getText().toString().trim();
        str_end_time = text_selected_end_time.getText().toString().trim();

        if ( selectedDriverID.contains("0")) {
            AppUtility.showToast(context, getString(R.string.driver_requirement));
        }
//        else if (str_date.contains("Select Date")) {
//            AppUtility.showToast(context, getString(R.string.date_requirement));
//        }
        else if (str_start_time.contains("Select Departure Time")) {
            AppUtility.showToast(context, getString(R.string.start_time_requirement));
        }else{
            AppUtility.showToast(context, " fire assigne event ");
        }

    }

    public void fillArrays() {

        fillDriver();

    }

    public void fillDriver() {
        try {

            arrayListDriver = new ArrayList<DriverModel>();
            String jsonLocation = AppUtility.AssetJSONFile("json/getDrivers.json", context);

            JSONObject jsonObject = new JSONObject(jsonLocation);
            if (jsonObject.has("drivers")) {
                JSONArray jsonArray = (JSONArray) jsonObject.getJSONArray("drivers");

                for (int i = 0; i < jsonArray.length(); i++) {

                    DriverModel driverModel = new DriverModel();
                    JSONObject jb = (JSONObject) jsonArray.get(i);

                    driverModel.id = (jb.getString("id"));
                    driverModel.name = (jb.getString("name"));

                    arrayListDriver.add(driverModel);

                }//end for

                Logger.log("arrayListDriver", String.valueOf(arrayListDriver.size()));

                DriverAdapter = new ArrayAdapter<DriverModel>(AssigneOperationActivity.this, R.layout.spinner_text, arrayListDriver);
                DriverAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                spDriver.setAdapter(DriverAdapter);

            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    // received delete event from createProductActivity

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == 3) {
             //   text_selected_date.setText(data.getStringExtra("dateValue"));
            } else if (requestCode == 4) {
                text_selected_start_time.setText(data.getStringExtra("timeValue"));
            } else if (requestCode == 5) {
                text_selected_end_time.setText(data.getStringExtra("timeValue"));
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
