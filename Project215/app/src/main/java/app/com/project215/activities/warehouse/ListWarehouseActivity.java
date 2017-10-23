package app.com.project215.activities.warehouse;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONException;
import org.json.JSONObject;

import app.com.project215.R;

public class ListWarehouseActivity extends AppCompatActivity {
    //initial data
   String selectedRoleId;

    Warehouse[] myWarehousesArray = new Warehouse[]{
            new Warehouse("Warehouse A", "Tripoli , next to hallab", "For all products", "Tripoli", "32.885353", "13.180161"),
            new Warehouse("Warehouse B", "Jal el dib , next to zaatar w zet","For all products", "Jal el dib", "33.9088", "35.582"),
    };

    private ListView mListView;
    private WarehouseAdapter mWarehouseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_warehouse);

        mListView = (ListView) findViewById(R.id.myListView);

        mWarehouseAdapter = new WarehouseAdapter(getApplicationContext(), R.layout.row_warehouse, myWarehousesArray);

        if (mListView != null) {
            mListView.setAdapter(mWarehouseAdapter);
        }

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                JSONObject jsonObject = new JSONObject();
                selectedRoleId = getIntent().getStringExtra("role_id");
                try {

                    jsonObject.put("name", myWarehousesArray[i].name);
                    jsonObject.put("address", myWarehousesArray[i].address);
                    jsonObject.put("description", myWarehousesArray[i].description);
                    jsonObject.put("city", myWarehousesArray[i].city);
                    jsonObject.put("latitude", myWarehousesArray[i].latitude);
                    jsonObject.put("longitude", myWarehousesArray[i].longitude);
                    jsonObject.put("role_id",selectedRoleId);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                startActivityForResult(new Intent(ListWarehouseActivity.this, CreateWarehouseActivity.class).putExtra("warehouse", jsonObject.toString()), 1);
                //startActivity(new Intent(this, CreateWarehouseActivity.class)).putExtra("warehouse", jsonObject.toString()));

            }
        });
    }

    // received location event from LocationMapActivity
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                    finish();
            }
        }
    }
}//end activity






/* @Override
              public boolean onCreateOptionsMenu(Menu menu) {

                  // Inflate the menu; this adds items to the action bar if it is present.
                  getMenuInflater().inflate(R.menu.list_view, menu);
                  return true;
              }*/

             /* @Override
              public boolean onOptionsItemSelected(MenuItem item) {
                  // Handle action bar item clicks here. The action bar will
                  // automatically handle clicks on the Home/Up button, so long
                  // as you specify a parent activity in AndroidManifest.xml.
                  int id = item.getItemId();
                  if (id == R.id.action_settings) {
                      return true;
                  }
                  return super.onOptionsItemSelected(item);
              }*/



