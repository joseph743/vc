package app.com.project215.activities.user;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import app.com.project215.R;
import app.com.project215.util.AppUtility;

public class ForgotPasswordActivity extends AppCompatActivity implements View.OnClickListener {

    private Context context;

    private TextView txt_SubmitBtn;
    private EditText edit_email;

    private String str_email = "";
    private ProgressDialog mDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_forgot_password);

        context = ForgotPasswordActivity.this;

        mDialog = new ProgressDialog(context);

        initView();
    }

    /**
     * Initialize the view
     */
    private void initView() {
        txt_SubmitBtn = (TextView) findViewById(R.id.txt_forgot_pass_submit);
        edit_email = (EditText) findViewById(R.id.edit_email);

        txt_SubmitBtn.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.txt_forgot_pass_submit:
                forgetPassEvent();
                break;
            default:
                break;
        }
    }

    public void forgetPassEvent() {

        str_email = edit_email.getText().toString().trim();

        if (TextUtils.isEmpty(str_email)) {
            AppUtility.showToast(context, getString(R.string.email_requirement));
            return;
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(str_email).matches()) {
            AppUtility.showToast(context, getString(R.string.email_validation));
            return;
        }


        AppUtility.showToast(context, "callApiForgetPassword");

    }

    private Map<String, String> getParams() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("email", str_email);
        return params;
    }
}