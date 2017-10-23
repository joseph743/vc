package app.com.project215.activities;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import app.com.project215.R;


public class datePickerActivity extends AppCompatActivity implements View.OnClickListener {

    private Context context;


    Bundle extras = null;

    DatePicker datePicker;
    TextView submitDate;

    String c_dateType = null;
    ;

    static double dragend_latitude, dragend_longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_picker);

        context = datePickerActivity.this;




        initView();


    }


    private void initView() {

        c_dateType = new String(getIntent().getStringExtra("date"));


        datePicker = (DatePicker) findViewById(R.id.picker_date_picker);
        submitDate = (TextView) findViewById(R.id.tv_submit_date);
        submitDate.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_submit_date:
                Intent intent = new Intent();

                if (c_dateType.contains("expiry")) {
                    intent.putExtra("expiryDateValue", datePicker.getDayOfMonth() + "-" + (datePicker.getMonth() + 1) + "-" + datePicker.getYear());
                } else if (c_dateType.contains("created")) {
                    intent.putExtra("createdDateValue", datePicker.getDayOfMonth() + "-" + (datePicker.getMonth() + 1) + "-" + datePicker.getYear());
                } else if (c_dateType.contains("-")) {
                    intent.putExtra("dateValue", datePicker.getDayOfMonth() + "-" + (datePicker.getMonth() + 1) + "-" + datePicker.getYear());
                }

                setResult(RESULT_OK, intent);
                finish();
                break;

        }

    }


}
