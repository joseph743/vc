package app.com.project215.activities.user;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import app.com.project215.R;
import app.com.project215.util.AppUtility;

public class ChangePasswordActivity extends AppCompatActivity implements View.OnClickListener {

    private Context context;

    private TextView txt_SubmitBtn;
    private EditText edit_old_password, edit_new_password, edit_confirm_new_password;
    String str_old_password, str_new_password, str_confirm_new_password;


    private ProgressDialog mDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_change_password);

        context = ChangePasswordActivity.this;

        mDialog = new ProgressDialog(context);

        initView();
    }

    /**
     * Initialize the view
     */
    private void initView() {
        txt_SubmitBtn = (TextView) findViewById(R.id.txt_change_pass_submit);
        txt_SubmitBtn.setOnClickListener(this);

        edit_old_password = (EditText) findViewById(R.id.ed_old_password);
        edit_new_password = (EditText) findViewById(R.id.ed_new_password);
        edit_confirm_new_password = (EditText) findViewById(R.id.ed_confirm_new_password);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.txt_change_pass_submit:
                changePassEvent();
                break;
            default:
                break;
        }
    }

    public void changePassEvent() {

        str_old_password = edit_old_password.getText().toString().trim();
        str_new_password = edit_new_password.getText().toString().trim();
        str_confirm_new_password = edit_confirm_new_password.getText().toString().trim();

        if ( str_old_password.length() == 0) {
            AppUtility.showToast(context, getString(R.string.old_password_requirement));
        } else if (str_new_password.length() == 0) {
            AppUtility.showToast(context, getString(R.string.new_password_requirement));
        } else if (str_confirm_new_password.length() == 0) {
            AppUtility.showToast(context, getString(R.string.confirm_new_password_requirement));
        } else if(!str_confirm_new_password.equalsIgnoreCase(str_new_password)){
            AppUtility.showToast(context, getString(R.string.password_matching_requirement));
        } else {
            AppUtility.showToast(context, "callApiChangePassword");
        }

    }


    private Map<String, String> getParams() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("password", str_old_password);
        params.put("new_password", str_new_password);
        params.put("confirm_new_password", str_confirm_new_password);
        return params;
    }
}