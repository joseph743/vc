package app.com.project215.activities.products;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import app.com.project215.R;
import app.com.project215.util.AppUtility;

public class ListProductsActivity extends AppCompatActivity {


    private Context context;
    private ProgressDialog mDialog;

    TableLayout table_parent;
    TableRow row;
    TextView text_view;
    JSONObject jb;

    EditText edit_search_code;


    public String selectedRoleID;

    JSONObject jsonObject = null;
    Bundle extras = null;
    JSONArray jsonArray;

    ArrayList<TableRow> tr_arrayList = new ArrayList<TableRow>();
    ArrayList<TextView> tv_arrayList = new ArrayList<TextView>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_products);

        context = ListProductsActivity.this;
        selectedRoleID = getIntent().getStringExtra("role_id");
        table_parent = (TableLayout) findViewById(R.id.tl_parent);
        edit_search_code = (EditText) findViewById(R.id.ed_search_code);

        edit_search_code.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }


            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Logger.log("ewewew",searchView.getText().toString());
                String str_search_code = edit_search_code.getText().toString().toLowerCase().trim();


                try {
                    for (int i = 1; i < jsonArray.length(); i++) {
                        jb = (JSONObject) jsonArray.get(i);

                        String code = jb.getString("code").toLowerCase();
                        String name = jb.getString("name").toLowerCase();

                        if (code.contains(str_search_code) || name.contains(str_search_code)) {

                            TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(AppUtility.dpToPx(context, 120), TableRow.LayoutParams.MATCH_PARENT);
                            layoutParams.setMargins(AppUtility.dpToPx(context, 1), AppUtility.dpToPx(context, 1), AppUtility.dpToPx(context, 1), AppUtility.dpToPx(context, 1));
                            tv_arrayList.get(Integer.valueOf(i - 1) * 10).setLayoutParams(layoutParams);

                        } else {

                            // if 1 of text view set to height 0 all the row will be 0  // row can not set to 0
                            TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(AppUtility.dpToPx(context, 0), AppUtility.dpToPx(context, 0));
                            tv_arrayList.get(Integer.valueOf(i - 1) * 10).setLayoutParams(layoutParams);

                        }
                    }// end for
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        });//end search
//        try {
//            extras = getIntent().getExtras();
//            if (extras != null)
//                jsonObject = new JSONObject(getIntent().getStringExtra("warehouse"));
//
//        } catch (JSONException e) {
//
//        }


        //   initView();


        fillProducts();


    }


    /**
     * Initialize the view
     */
    private void initView() {

    } // end init view


    public void fillProducts() {

        try {


            String jsonLocation = AppUtility.AssetJSONFile("json/getProducts.json", context);
            JSONObject jsonObject = new JSONObject(jsonLocation);

            if (jsonObject.has("products")) {
                jsonArray = (JSONArray) jsonObject.getJSONArray("products");
                for (int i = 1; i < jsonArray.length(); i++) {
                    tr_arrayList.add(drowRow(i));
                    table_parent.addView(tr_arrayList.get(tr_arrayList.size() - 1));


                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public TableRow drowRow(int i) {

        try {

            row = new TableRow(this);
            TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);

            row.setLayoutParams(lp);
            //  row.setBackgroundColor(Color.RED);


            jb = (JSONObject) jsonArray.get(i);
            jb.put("role_id", selectedRoleID);

            drowField(jb.toString(), jb.getString("code"));
            drowField(jb.toString(), jb.getString("Product_type_name"));
            drowField(jb.toString(), jb.getString("name"));
            drowField(jb.toString(), jb.getString("total_quantity"));
            drowField(jb.toString(), jb.getString("brand_name"));
            drowField(jb.toString(), jb.getString("category_name"));
            drowField(jb.toString(), jb.getString("cost"));
            drowField(jb.toString(), jb.getString("sale_price"));
            drowField(jb.toString(), jb.getString("barcode"));
            drowField(jb.toString(), String.valueOf("View More"));


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return row;
    }

    public void drowField(final String toNextPage, String title) {

        // app.com.project215.util.Logger.log("toNextPage", toNextPage);
        text_view = new TextView(this);
        text_view.setText(title);
        text_view.setPadding(5, 5, 5, 5);
        text_view.setBackground(getResources().getDrawable(R.drawable.table_row_border));
        text_view.setTextSize(18);
        text_view.setTextColor(Color.BLACK);
        text_view.setGravity(Gravity.CENTER);

        TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(AppUtility.dpToPx(this, 120), TableRow.LayoutParams.MATCH_PARENT);
        layoutParams.setMargins(AppUtility.dpToPx(this, 1), AppUtility.dpToPx(this, 1), AppUtility.dpToPx(this, 1), AppUtility.dpToPx(this, 1));
        text_view.setLayoutParams(layoutParams);


        if (title == "View More") {
            text_view.setTextColor(Color.RED);
            text_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivityForResult(new Intent(ListProductsActivity.this, CreateProductActivity.class).putExtra("product", toNextPage), 1);
                }
            });
        }


        tv_arrayList.add(text_view);
        row.addView(tv_arrayList.get(tv_arrayList.size() - 1));
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
