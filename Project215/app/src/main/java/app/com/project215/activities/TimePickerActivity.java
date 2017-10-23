package app.com.project215.activities;


import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;

import app.com.project215.R;


public class TimePickerActivity extends AppCompatActivity implements View.OnClickListener {

    private Context context;


    Bundle extras = null;

    TimePicker timePicker;
    TextView submitTime;

    String c_time = null;
    ;

    static double dragend_latitude, dragend_longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_picker);

        context = TimePickerActivity.this;




        initView();


    }


    private void initView() {

        c_time = new String(getIntent().getStringExtra("time"));


        timePicker = (TimePicker) findViewById(R.id.picker_time_picker);
        submitTime= (TextView) findViewById(R.id.tv_submit_time);
        submitTime.setOnClickListener(this);
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_submit_time:
                Intent intent = new Intent();

                if (c_time.contains("-")) {

                    int hours = timePicker.getHour();
                    int minutes = timePicker.getMinute();
                    String ampm = hours >= 12 ? "PM" : "AM";

                    hours = hours % 12;
                    hours = (hours != 0) ? hours : 12;
                    //kermel eza kenit 0 tsir 12

                    minutes = minutes < 10 ? ('0' + minutes) : minutes;

                    String totalTime = hours + ":" + minutes + " "  + ampm;

                    intent.putExtra("timeValue", totalTime);
                }

                setResult(RESULT_OK, intent);
                finish();
                break;

        }

    }


}
