package app.com.project215.activities.user;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import app.com.project215.R;
import app.com.project215.activities.MenuActivity;
import app.com.project215.model.RoleModel;
import app.com.project215.model.UserModel;
import app.com.project215.util.AppUtility;
import app.com.project215.util.Logger;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    public static final int ZBAR_CAMERA_PERMISSION = 1;

    private Context context;

    private TextView txt_ForgotPassword;
    private TextView txt_SignInBtn;

    Spinner spRole;
    String selectedRoleId = "0";
    ArrayList arrayListRole;
    ArrayAdapter<RoleModel> roleAdapter;

    private TextView txt_Registration;

    private EditText edit_email;
    private EditText edit_Password;

    private String str_email = "";
    private String str_Password = "";

    private ProgressDialog mDialog;

    private UserModel userModel;

    // View EditText1 = R.layout.custom_EditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        context = LoginActivity.this;

        mDialog = new ProgressDialog(context);

        initView();

        fillRole();
    }


    /**
     * Initialize the view
     */
    private void initView() {
        spRole = (Spinner) findViewById(R.id.sp_role);


        txt_ForgotPassword = (TextView) findViewById(R.id.txt_forgot_pass);
        txt_SignInBtn = (TextView) findViewById(R.id.txt_sign_in);

        txt_Registration = (TextView) findViewById(R.id.txt_sign_up);

        edit_email = (EditText) findViewById(R.id.email);
        edit_Password = (EditText) findViewById(R.id.password);

        txt_ForgotPassword.setOnClickListener(this);
        txt_SignInBtn.setOnClickListener(this);

        txt_Registration.setOnClickListener(this);


        // AppUtility.txtbtnEnabledFunction(txt_SignInBtn, edit_email);
        // AppUtility.txtbtnEnabledFunction(txt_SignInBtn, edit_Password);
    }


    private void fillRole() {

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

                roleAdapter = new ArrayAdapter<RoleModel>(LoginActivity.this, R.layout.spinner_text, arrayListRole);
                roleAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                spRole.setAdapter(roleAdapter);

            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.txt_forgot_pass:
                startActivity(new Intent(context, ForgotPasswordActivity.class));
                break;
            case R.id.txt_sign_in:
                signInEvent();
                break;
            case R.id.txt_sign_up:
                startActivity(new Intent(context, RegisterActivity.class));
                break;
            default:
                break;
        }
    }


    private void signInEvent() {

        str_email = edit_email.getText().toString().trim();
        str_Password = edit_Password.getText().toString().trim();
        selectedRoleId = roleAdapter.getItem(spRole.getSelectedItemPosition()).getRole_id();

        //if role is chosen
        if (selectedRoleId == "0") {
            AppUtility.showToast(context, getString(R.string.role_requirement));
            return;
        }

        // if email empty
        if (TextUtils.isEmpty(str_email)) {
            AppUtility.showToast(context, getString(R.string.email_requirement));
            return;
        }

        // validate email
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(str_email).matches()) {
            AppUtility.showToast(context, getString(R.string.email_validation));
            return;
        }

        // if password empty
        if (TextUtils.isEmpty(str_Password)) {
            AppUtility.showToast(context, getString(R.string.password_requirement));
            return;
        }

        //Enter the MenuActivity
        try {
            String jsonLocation = AppUtility.AssetJSONFile("json/getUsers.json", context);
            JSONObject jsonObject = new JSONObject(jsonLocation);
            if (jsonObject.has("users")) {
                JSONArray jsonArray = (JSONArray) jsonObject.getJSONArray("users");
                for (int i = 0; i < jsonArray.length(); i++) {

                    JSONObject jb = (JSONObject) jsonArray.get(i);

                    if (jb.getString("id").contains(selectedRoleId)) {
                        //   AppUtility.showToast(context,"selectedRoleId");
                        jb.put("role_id", selectedRoleId);
                        startActivity(new Intent(LoginActivity.this, MenuActivity.class).putExtra("user", jb.toString()));
                        //  finish();
                        break;
                    }

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }





    private Map<String, String> getParams() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("email", str_email);
        params.put("password", str_Password);
        params.put("role", selectedRoleId);
        return params;
    }
}
