package app.com.project215.activities.barcode;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import app.com.project215.R;
import app.com.project215.activities.products.CreateProductActivity;
import app.com.project215.util.AppUtility;
import app.com.project215.util.Logger;
import me.dm7.barcodescanner.zbar.Result;
import me.dm7.barcodescanner.zbar.ZBarScannerView;

//public class BarCodeActivity extends BaseScannerActivity implements MessageDialogFragment.MessageDialogListener,
//        ZBarScannerView.ResultHandler, FormatSelectorDialogFragment.FormatSelectorDialogListener,
//        CameraSelectorDialogFragment.CameraSelectorDialogListener {

public class BarCodeActivity extends BaseScannerActivity implements  ZBarScannerView.ResultHandler {
    public JSONObject matched = null;
    private Context context;
    public String selectedRoleId = null ;




    private static final String FLASH_STATE = "FLASH_STATE";

    private ZBarScannerView mScannerView;
    private boolean mFlash;

    private int mCameraId = -1;

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);

        selectedRoleId = new String(getIntent().getStringExtra("role_id"));

        if (state != null) {
            mFlash = state.getBoolean(FLASH_STATE, false);

        } else {
            mFlash = false;

        }

        setContentView(R.layout.activity_bar_code);

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        toolbar.setTitle("Barcode Scanner");
//        setSupportActionBar(toolbar);
        setupToolbar();

        ViewGroup contentFrame = (ViewGroup) findViewById(R.id.content_frame);
        mScannerView = new ZBarScannerView(this);
        //  setupFormats();
        contentFrame.addView(mScannerView);
    }


    @Override
    public void onResume() {
        Logger.log("INFO 0", "onResume");
        super.onResume();
        mScannerView.setResultHandler(this);
        mScannerView.startCamera(mCameraId);
        mScannerView.setFlash(mFlash);
        mScannerView.setAutoFocus(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem menuItem;

        Logger.log("INFO 1", "onCreateOptionsMenu");

        //flash
        if (mFlash) {
            menuItem = menu.add(Menu.NONE, R.id.menu_flash, 0, R.string.flash_on);
        } else {
            menuItem = menu.add(Menu.NONE, R.id.menu_flash, 0, R.string.flash_off);
        }
        MenuItemCompat.setShowAsAction(menuItem, MenuItem.SHOW_AS_ACTION_NEVER);


        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.menu_flash:
                mFlash = !mFlash;
                if (mFlash) {
                    item.setTitle(R.string.flash_on);
                } else {
                    item.setTitle(R.string.flash_off);
                }
                mScannerView.setFlash(mFlash);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void handleResult(Result rawResult) {
        Logger.log("INFO 2", "handleResult");

        try {
            Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
            r.play();
        } catch (Exception e) {
        }

        showMessageDialog(rawResult);
    }

    public void showMessageDialog(Result rawResult) {
        String c_barcode = rawResult.getContents();
        matched = null;
        String jsonLocation = null;
        try {
            //jb = (JSONObject) jsonArray.get(i);


            jsonLocation = AppUtility.AssetJSONFile("json/getProducts.json", this);
            Log.i("jsonLocation", jsonLocation);
            JSONObject jsonObject = new JSONObject(jsonLocation);
            if (jsonObject.has("products")) {
                JSONArray jsonArray = (JSONArray) jsonObject.getJSONArray("products");
                for (int i = 1; i < jsonArray.length(); i++) {

                    JSONObject jb = (JSONObject) jsonArray.get(i);
                    jb.put("role_id",selectedRoleId);
                    Logger.log("c_barcode", c_barcode);
                    if (c_barcode.contains(jb.getString("barcode"))) {
                        matched = jb;
                        break;
                    }

                }
            }
            Logger.log("matched",String.valueOf(matched));
          //  AppUtility.showToast(context,String.valueOf(matched));

            if (matched == null) {
                //not matched

                AlertDialog.Builder builder1 = new AlertDialog.Builder(BarCodeActivity.this);
                builder1.setTitle("It's a new Product");
                builder1.setMessage("Please send an Email to Inventory Manager to add this product.");
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Ok",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                mScannerView.resumeCameraPreview(BarCodeActivity.this);
                                dialog.cancel();
                            }
                        });

                builder1.create().show();

            } else {
                //matched

                AlertDialog.Builder builder1 = new AlertDialog.Builder(BarCodeActivity.this);
                builder1.setTitle("Product Exist");
                builder1.setMessage(("Barcode: "+ c_barcode +"\nProduct name: "+matched.getString("name")));
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "View",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Logger.log("matched.toString()",matched.toString());
                                startActivity(new Intent(BarCodeActivity.this, CreateProductActivity.class).putExtra("product", matched.toString()));
                            }
                        });

                builder1.setNegativeButton(
                        "Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                mScannerView.resumeCameraPreview(BarCodeActivity.this);
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();

            }


        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    public void closeMessageDialog() {
        closeDialog("scan_results");
    }

    public void closeFormatsDialog() {
        closeDialog("format_selector");
    }

    public void closeDialog(String dialogName) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        DialogFragment fragment = (DialogFragment) fragmentManager.findFragmentByTag(dialogName);
        if (fragment != null) {
            fragment.dismiss();
        }
    }




    @Override
    public void onPause() {
        Logger.log("INFO", "onPause");

        super.onPause();
        mScannerView.stopCamera();
        closeMessageDialog();
        closeFormatsDialog();
    }
}
