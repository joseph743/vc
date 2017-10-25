package app.com.project215.activities.operations;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import app.com.project215.R;
import app.com.project215.util.AppUtility;

public class OperationsHistoryActivity extends AppCompatActivity {


    private Context context;
    private ProgressDialog mDialog;

    TableLayout table_parent;
    TableRow row, tableRow_parent;
    TextView text_view, text_type, text_source, text_destination , text_driver,text_start_time, text_end_time;
    JSONObject jb;

    public String selectedRoleID;

    JSONObject jsonObject = null;
    Bundle extras = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_operations);

        context = OperationsHistoryActivity.this;


//        try {
//            extras = getIntent().getExtras();
//            if (extras != null)
//                jsonObject = new JSONObject(getIntent().getStringExtra("warehouse"));
//
//        } catch (JSONException e) {
//
//        }


        initView();

        //   fillArrays();

        fillProducts();

    }

    public void initView() {
        selectedRoleID = getIntent().getStringExtra("role_id");

        tableRow_parent = (TableRow) findViewById(R.id.tr_parent);
        text_source = (TextView) findViewById(R.id.tv_source);
        text_destination = (TextView) findViewById(R.id.tv_destination);
        text_type = (TextView) findViewById(R.id.tv_type);
        text_driver = (TextView) findViewById(R.id.tv_driver);
        text_start_time = (TextView) findViewById(R.id.tv_start_time);
        text_end_time = (TextView) findViewById(R.id.tv_end_time);

        if (selectedRoleID.contains("1")) {
            tableRow_parent.removeView(text_type);
            tableRow_parent.removeView(text_source);
            tableRow_parent.removeView(text_destination);
        }

        if(!selectedRoleID.contains("5")){
            tableRow_parent.removeView(text_driver);
        }

        if(!selectedRoleID.contains("4")){
            tableRow_parent.removeView(text_start_time);
            tableRow_parent.removeView(text_end_time);
        }
    }

    public void fillProducts() {


        try {


            String jsonLocation = AppUtility.AssetJSONFile("json/getOperations.json", context);
            JSONObject jsonObject = new JSONObject(jsonLocation);
            if (jsonObject.has("operations")) {
                JSONArray jsonArray = (JSONArray) jsonObject.getJSONArray("operations");
                for (int i = 0; i < jsonArray.length(); i++) {
                    jb = (JSONObject) jsonArray.get(i);


                    //if client and internal
                    if ((selectedRoleID.contains("1") && jb.getString("type").contains("external")) || selectedRoleID.contains("3") || selectedRoleID.contains("2") || selectedRoleID.contains("4") || selectedRoleID.contains("5")) {

                        //static if is driver and user id == driver_id (just to appear driver opperations)
                        if ((selectedRoleID.contains("4") && jb.getString("driver_id").contains("1")) || selectedRoleID.contains("3") || selectedRoleID.contains("2") || selectedRoleID.contains("1") || selectedRoleID.contains("5")) {
                            jb.put("role_id", selectedRoleID);

                            table_parent = (TableLayout) findViewById(R.id.tl_parent);

                            row = new TableRow(this);
                            TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
                            row.setLayoutParams(lp);
                         //   row.setBackgroundColor(Color.BLACK);


                            if (!selectedRoleID.contains("1")) {
                                drowField(jb.toString(), jb.getString("type"));
                                drowField(jb.toString(), jb.getString("source_name"));
                                drowField(jb.toString(), jb.getString("destination_name"));
                            }

                            drowField(jb.toString(), jb.getString("status"));

                            if (selectedRoleID.contains("5")) {
                                if (jb.getString("driver_name").length() == 0) {
                                    drowField(jb.toString(), "-");
                                } else {
                                    drowField(jb.toString(), jb.getString("driver_name"));
                                }
                            }


                            drowField(jb.toString(), jb.getString("date"));

                            //if driver end time + start time
                            if (selectedRoleID.contains("4")) {
                                drowField(jb.toString(), jb.getString("start_time"));
                                drowField(jb.toString(), jb.getString("end_time"));
                            }

                            drowField(jb.toString(), String.valueOf("View More"));

                            table_parent.addView(row);

                        }//end if driver

                    }//end big if
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    public void drowField(final String toNextPage, String title) {

        app.com.project215.util.Logger.log("toNextPage", toNextPage);
        text_view = new TextView(this);
        text_view.setText(title);
        text_view.setPadding(5,5,5,5);
        text_view.setBackground(getResources().getDrawable(R.drawable.table_row_border));
        text_view.setTextSize(18);
        text_view.setTextColor(Color.BLACK);
        text_view.setGravity(Gravity.CENTER);

        if (title.toLowerCase().contains("pending")) {
            text_view.setTextColor(Color.RED);
        } else if (title.toLowerCase().contains("packaging")) {
            text_view.setTextColor(Color.YELLOW);
        } else if (title.toLowerCase().contains("transferring")) {
            text_view.setTextColor(Color.GREEN);
        } else if (title.toLowerCase().contains("completed")) {
            text_view.setTextColor(Color.BLUE);
        }

        TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(AppUtility.dpToPx(this, 120), TableRow.LayoutParams.MATCH_PARENT);
        layoutParams.setMargins(AppUtility.dpToPx(this, 1), AppUtility.dpToPx(this, 1), AppUtility.dpToPx(this, 1), AppUtility.dpToPx(this, 1));

        if (title == "View More") {
            text_view.setTextColor(Color.RED);
            text_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivityForResult(new Intent(OperationsHistoryActivity.this, MakeOperationActivity.class).putExtra("operation_detail", toNextPage), 1);
                }
            });
        }


        row.addView(text_view, layoutParams);
    }


    // received delete event from createProductActivity
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                finish();
            }
        }
    }


}
