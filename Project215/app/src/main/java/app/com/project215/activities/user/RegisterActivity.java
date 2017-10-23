package app.com.project215.activities.user;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import app.com.project215.AppController;
import app.com.project215.R;
import app.com.project215.model.RoleModel;
import app.com.project215.util.AppUtility;
import app.com.project215.util.Logger;


public class RegisterActivity extends AppCompatActivity implements View.OnClickListener, Spinner.OnItemSelectedListener {

    private Context context;

    // String edit_role_firstTime = "1";
    int check_role = 0;

    Spinner spRole;
    String selectedRoleId = "0";
    ArrayList arrayListRole;
    ArrayAdapter<RoleModel> roleAdapter;


    EditText name, mobile, phone, fax, email, password, confirmPassword, address, city;
    TextView text_select_role;
    TextView location;
    LinearLayout addressView, cityView, city_addressView, locationView;
    LinearLayout passwordView, confirmView, passwordConfirmView;
    TextView text_header;

    String str_name, str_email, str_mobile, str_phone, str_fax, str_address, str_city, str_latitude, str_longitude;

    TextView registerBtn;
    ProgressDialog mDialog;

    JSONObject c_jsonObject = null;
    Bundle extras = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        context = RegisterActivity.this;

        try {
            extras = getIntent().getExtras();
            if (extras != null) {
                c_jsonObject = new JSONObject(getIntent().getStringExtra("user"));
                selectedRoleId = c_jsonObject.getString("role_id");
            }
        } catch (JSONException e) {

        }


        initView();

        fillArrays();

    }


    private void initView() {
        text_header = (TextView) findViewById(R.id.tv_header_title);

        mDialog = new ProgressDialog(context);

        //return to default lat long
        ((AppController) this.getApplication()).setLocation(33.888630, 35.495480);

        text_select_role = (TextView) findViewById(R.id.tv_select_role);

        city = (EditText) findViewById(R.id.ed_city);
        location = (TextView) findViewById(R.id.ed_location);

        locationView = (LinearLayout) findViewById(R.id.view_location);

        locationView.setOnClickListener(this);

        spRole = (Spinner) findViewById(R.id.sp_role);
        spRole.setOnItemSelectedListener(this);

        email = (EditText) findViewById(R.id.ed_email);
        name = (EditText) findViewById(R.id.ed_name);


        mobile = (EditText) findViewById(R.id.ed_mobile_number);
        phone = (EditText) findViewById(R.id.ed_phone_number);
        fax = (EditText) findViewById(R.id.ed_fax);

        passwordConfirmView = (LinearLayout) findViewById(R.id.view_password_confirm);
        confirmView = (LinearLayout) findViewById(R.id.view_password);
        passwordView = (LinearLayout) findViewById(R.id.view_confirm);
        password = (EditText) findViewById(R.id.ed_pass);
        confirmPassword = (EditText) findViewById(R.id.ed_confirm_pass);


        city_addressView = (LinearLayout) findViewById(R.id.view_city_address);
        addressView = (LinearLayout) findViewById(R.id.view_address);
        address = (EditText) findViewById(R.id.ed_address);

        cityView = (LinearLayout) findViewById(R.id.view_city);


        registerBtn = (TextView) findViewById(R.id.tv_create_account);
        registerBtn.setOnClickListener(this);


        if (c_jsonObject != null) {

            text_header.setText("Profile");
            registerBtn.setText("Edit");

            passwordConfirmView.removeView(confirmView);
            passwordConfirmView.removeView(passwordView);

            if (selectedRoleId.contains("2") || selectedRoleId.contains("3") || selectedRoleId.contains("4") || selectedRoleId.contains("5")) {
                city_addressView.removeView(cityView);
                city_addressView.removeView(addressView);
                city_addressView.removeView(locationView);
            }

            try {
                Logger.log("c_jsonObject", c_jsonObject.toString());


                //role
                text_select_role.setVisibility(View.VISIBLE);
                spRole.setEnabled(false);
                text_select_role.setText(c_jsonObject.getString("role_name"));
                selectedRoleId = c_jsonObject.getString("role_id");


                email.setText(c_jsonObject.getString("email"));
                name.setText(c_jsonObject.getString("name"));
                mobile.setText(c_jsonObject.getString("mobile"));
                phone.setText(c_jsonObject.getString("phone"));
                fax.setText(c_jsonObject.getString("fax"));


                if (c_jsonObject.getString("role_id").contains("3")) { // warehouse manager


                } else if (c_jsonObject.getString("role_id").contains("2")) { // inventory manager


                } else if (c_jsonObject.getString("role_id").contains("4")) { // Driver


                } else if (c_jsonObject.getString("role_id").contains("5")) { // Driver Manager


                } else if (c_jsonObject.getString("role_id").contains("1")) { // client side

                    city.setText(c_jsonObject.getString("city"));
                    address.setText(c_jsonObject.getString("address"));
                    location.setText(("View Location"));
                    ((AppController) this.getApplication()).setLocation(Double.valueOf(c_jsonObject.getString("latitude")), Double.valueOf(c_jsonObject.getString("longitude")));

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

        } else {

            city_addressView.removeView(cityView);
            city_addressView.removeView(addressView);
            city_addressView.removeView(locationView);

        }

    }


    @Override
    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

        switch (parentView.getId()) {
            case R.id.sp_role:
                check_role++;
                if (check_role > 1) {

                    selectedRoleId = roleAdapter.getItem(position).getRole_id();

                    if (selectedRoleId == "1") {

                        if (city_addressView.getChildCount() == 0) {
                            name.setHint("Company name*");
                            city_addressView.addView(cityView);
                            city_addressView.addView(addressView);
                            city_addressView.addView(locationView);

                        }


                    } else {

                        if (city_addressView.getChildCount() > 0) {
                            name.setHint("Name*");
                            city_addressView.removeView(cityView);
                            city_addressView.removeView(addressView);
                            city_addressView.removeView(locationView);

                        }

                    }


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
            case R.id.view_location:
                startActivityForResult(new Intent(this, LocationMapActivity.class), 1);
                //startActivity(new Intent(this, LocationMapActivity.class));
                break;
            case R.id.tv_create_account:
                registerEvent();
                break;
        }

    }

    private void registerEvent() {

        Logger.log("selectedRoleId", selectedRoleId);

        str_email = email.getText().toString().trim();
        str_name = name.getText().toString().trim();
        str_mobile = mobile.getText().toString().trim();
        str_phone = phone.getText().toString().trim();
        str_fax = fax.getText().toString().trim();

        Logger.log("str_name", str_name);
        Logger.log("str_email", str_email);
        Logger.log("str_mobile", str_mobile);
        Logger.log("str_phone", str_phone);
        Logger.log("str_fax", str_fax);


        if (selectedRoleId.contains("1")) {
            str_address = address.getText().toString().trim();
            str_city = city.getText().toString().trim();
            str_latitude = String.valueOf(((AppController) this.getApplication()).returnLatitude());
            str_longitude = String.valueOf(((AppController) this.getApplication()).returnLongitude());

            Logger.log("str_city", str_city);
            Logger.log("str_address", str_address);
            Logger.log("str_latitude", str_latitude);
            Logger.log("str_longitude", str_longitude);
        }

        if (selectedRoleId == "0") {
            AppUtility.showToast(context, getString(R.string.role_requirement));
        } else if (str_email.length() == 0) {
            AppUtility.showToast(context, getString(R.string.email_requirement));
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(str_email).matches()) {
            AppUtility.showToast(context, getString(R.string.email_validation));
        } else if (str_name.length() == 0) {
            AppUtility.showToast(context, getString((selectedRoleId.contains("1") ? R.string.company_name_requirement : R.string.name_requirement)));
        } else if (str_mobile.length() == 0) {
            AppUtility.showToast(context, getString(R.string.mobile_requirement));
        } else if (str_mobile.length() > 0 && !Patterns.PHONE.matcher(str_mobile).matches()) {
            AppUtility.showToast(context, getString(R.string.mobile_validation));
        } else if (c_jsonObject == null && password.getText().toString().trim().length() == 0) {
            AppUtility.showToast(context, getString(R.string.password_requirement));
        } else if (c_jsonObject == null && confirmPassword.getText().toString().trim().length() == 0) {
            AppUtility.showToast(context, getString(R.string.password_confirm_requirement));
        } else if (c_jsonObject == null && !confirmPassword.getText().toString().trim().equalsIgnoreCase(password.getText().toString().trim())) {
            AppUtility.showToast(context, getString(R.string.password_matching_requirement));
        } else if (selectedRoleId.contains("1") && str_city.length() == 0) {
            AppUtility.showToast(context, getString(R.string.city_requirement));
        } else if (selectedRoleId.contains("1") && str_address.length() == 0) {
            AppUtility.showToast(context, getString(R.string.address_requirement));
        } else if (selectedRoleId.contains("1") && str_latitude.contains("33.88863") && str_longitude.contains("35.49548")) {
            AppUtility.showToast(context, getString(R.string.location_requirement));
        } else {
            AppUtility.showToast(RegisterActivity.this, c_jsonObject == null ? "callApiRegister" : "callApiEdit");
        }


        //  AppUtility.showToast(RegisterActivity.this, ((AppController) this.getApplication()).returnLatitude() + "/" + ((AppController) this.getApplication()).returnLongitude());

//        if (c_jsonObject != null) {
//            AppUtility.showToast(context, "EDIT MODE");
//            Logger.log("EDIT MODE", "ON");
//        } else {
//            AppUtility.showToast(context, "CREATE MODE");
//            Logger.log("CREATE MODE", "ON");
//        }


    }

    // received location event from LocationMapActivity
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                String strEditText = data.getStringExtra("editTextValue");

                if (c_jsonObject != null) {
                    location.setText("Location Changed");
                } else {
                    location.setText(strEditText);
                }
                location.setTextColor(Color.RED);
            }
        }
    }

    private void fillArrays() {


        try {

            arrayListRole = new ArrayList<RoleModel>();
            String jsonLocation = AppUtility.AssetJSONFile("json/getRole.json", context);

            JSONObject jsonObject = new JSONObject(jsonLocation);
            if (jsonObject.has("role")) {
                JSONArray jsonArray = (JSONArray) jsonObject.getJSONArray("role");
                for (int i = 0; i < jsonArray.length(); i++) {
                    Logger.log("arrayListRole i", String.valueOf(i));
                    RoleModel roleModel = new RoleModel();
                    JSONObject jb = (JSONObject) jsonArray.get(i);
                    roleModel.role_id = jb.getString("id");
                    roleModel.role_name = jb.getString("name");
                    arrayListRole.add(roleModel);
                }

                roleAdapter = new ArrayAdapter<RoleModel>(RegisterActivity.this, R.layout.spinner_text, arrayListRole);
                roleAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                spRole.setAdapter(roleAdapter);

            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }


}
