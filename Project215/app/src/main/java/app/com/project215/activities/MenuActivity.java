package app.com.project215.activities;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import app.com.project215.R;
import app.com.project215.activities.freeProducts.CreateFreeProductsActivity;
import app.com.project215.activities.user.ChangePasswordActivity;
import app.com.project215.activities.user.RegisterActivity;
import app.com.project215.activities.operations.MakeOperationActivity;
import app.com.project215.activities.operations.OperationsHistoryActivity;
import app.com.project215.activities.products.CreateProductActivity;
import app.com.project215.activities.products.ListProductsActivity;
import app.com.project215.activities.promotions.CreateDiscountPromotionActivity;
import app.com.project215.activities.promotions.CreateFreePromotionActivity;
import app.com.project215.activities.warehouse.CreateWarehouseActivity;
import app.com.project215.activities.warehouse.ListWarehouseActivity;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {
    public static final int ZBAR_CAMERA_PERMISSION = 1;
    private Class<?> mClss;

    private Context context;
    String selectedRoleId;
    TextView accountManagement , accountManagementProfile , accountManagementPassword;
    TextView warehouseManagement, warehouseManagementView, warehouseManagementCreate;
    TextView productsManagement, productsManagementView, productsManagementCreate;
    TextView promotions, promotionsView, promotionsCreateFree,promotionsCreateDiscount;
    TextView freeProducts, freeProductsView, freeProductsCreate;
    TextView operations, text_operations, operationsHistory;
    TextView adjustments;
    TextView barcodeScanner, text_barcodeScanner;
    LinearLayout parent_of_tv;

    JSONObject c_jsonObject = null;
    Bundle extras = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        context = MenuActivity.this;

        try {
            extras = getIntent().getExtras();

            if (extras != null) {
                if (getIntent().hasExtra("role_id")) {
                    selectedRoleId = getIntent().getStringExtra("role_id");
                } else if (getIntent().hasExtra("user")) {
                    c_jsonObject = new JSONObject(getIntent().getStringExtra("user"));
                    selectedRoleId = c_jsonObject.getString("role_id");
                }
            }

        } catch (JSONException e) {

        }


        initView();

    }


    /**
     * Initialize the view
     */
    private void initView() {
        parent_of_tv = (LinearLayout) findViewById(R.id.parent_of_tv);

        accountManagement = (TextView) findViewById(R.id.tv_account_management);
        accountManagementProfile = (TextView) findViewById(R.id.tv_profile);
        accountManagementProfile.setOnClickListener(this);
        accountManagementPassword = (TextView) findViewById(R.id.tv_change_password);
        accountManagementPassword.setOnClickListener(this);

        warehouseManagement = (TextView) findViewById(R.id.tv_warehouse_management);
        warehouseManagementView = (TextView) findViewById(R.id.tv_view_warehouses);
        warehouseManagementView.setOnClickListener(this);
        warehouseManagementCreate = (TextView) findViewById(R.id.tv_create_warehouse);
        warehouseManagementCreate.setOnClickListener(this);

        productsManagement = (TextView) findViewById(R.id.tv_products_management);
        productsManagementView = (TextView) findViewById(R.id.tv_view_products);
        productsManagementView.setOnClickListener(this);
        productsManagementCreate = (TextView) findViewById(R.id.tv_create_product);
        productsManagementCreate.setOnClickListener(this);

        promotions = (TextView) findViewById(R.id.tv_promotions);
        promotionsCreateFree = (TextView) findViewById(R.id.tv_create_promotion_free);
        promotionsCreateFree.setOnClickListener(this);
        promotionsCreateDiscount = (TextView) findViewById(R.id.tv_create_promotion_discount);
        promotionsCreateDiscount.setOnClickListener(this);

        freeProducts = (TextView) findViewById(R.id.tv_free_products);
        //freeProductsView = (TextView) findViewById(R.id.tv_view_free_products);
        freeProductsCreate = (TextView) findViewById(R.id.tv_create_free_product);
        freeProductsCreate.setOnClickListener(this);

        operations = (TextView) findViewById(R.id.tv_operations);
        text_operations = (TextView) findViewById(R.id.tv_all_operations);
        text_operations.setOnClickListener(this);

        operationsHistory = (TextView) findViewById(R.id.tv_history_operations);
        operationsHistory.setOnClickListener(this);

        // adjustments = (TextView) findViewById(R.id.tv_adjustments);

        barcodeScanner = (TextView) findViewById(R.id.tv_barcode_scanner);

        text_barcodeScanner = (TextView) findViewById(R.id.tv_open_barcode_scanner);
        text_barcodeScanner.setOnClickListener(this);


        if (selectedRoleId.contains("1"))// client
        {

            parent_of_tv.removeView(warehouseManagement);
            parent_of_tv.removeView(warehouseManagementView);
            parent_of_tv.removeView(warehouseManagementCreate);

            parent_of_tv.removeView(productsManagement);
            parent_of_tv.removeView(productsManagementView);
            parent_of_tv.removeView(productsManagementCreate);

            parent_of_tv.removeView(promotions);
          //  parent_of_tv.removeView(promotionsView);
            parent_of_tv.removeView(promotionsCreateFree);
            parent_of_tv.removeView(promotionsCreateDiscount);

            parent_of_tv.removeView(freeProducts);
            // parent_of_tv.removeView(freeProductsView);
            parent_of_tv.removeView(freeProductsCreate);

            //parent_of_tv.removeView(adjustments);
            parent_of_tv.removeView(barcodeScanner);
            parent_of_tv.removeView(text_barcodeScanner);

        } else if (selectedRoleId.contains("2")) //inventory manager
        {

            parent_of_tv.removeView(barcodeScanner);
            parent_of_tv.removeView(text_barcodeScanner);

        } else if (selectedRoleId.contains("3")) //warehouse manager
        {
            parent_of_tv.removeView(warehouseManagementCreate);
            parent_of_tv.removeView(productsManagementCreate);
            parent_of_tv.removeView(promotions);
            parent_of_tv.removeView(promotionsCreateFree);
            parent_of_tv.removeView(promotionsCreateDiscount);
            parent_of_tv.removeView(freeProducts);
            parent_of_tv.removeView(freeProductsCreate);
            // parent_of_tv.removeView(adjustments);


        }else if (selectedRoleId.contains("4"))// driver
        {
            parent_of_tv.removeView(warehouseManagement);
            parent_of_tv.removeView(warehouseManagementView);
            parent_of_tv.removeView(warehouseManagementCreate);

            parent_of_tv.removeView(productsManagement);
            parent_of_tv.removeView(productsManagementView);
            parent_of_tv.removeView(productsManagementCreate);

            parent_of_tv.removeView(promotions);
            //  parent_of_tv.removeView(promotionsView);
            parent_of_tv.removeView(promotionsCreateFree);
            parent_of_tv.removeView(promotionsCreateDiscount);

            parent_of_tv.removeView(freeProducts);
            // parent_of_tv.removeView(freeProductsView);
            parent_of_tv.removeView(freeProductsCreate);

            parent_of_tv.removeView(text_operations);

            //parent_of_tv.removeView(adjustments);
            parent_of_tv.removeView(barcodeScanner);
            parent_of_tv.removeView(text_barcodeScanner);

        }else if (selectedRoleId.contains("5"))// driver manager
        {
            parent_of_tv.removeView(warehouseManagement);
            parent_of_tv.removeView(warehouseManagementView);
            parent_of_tv.removeView(warehouseManagementCreate);

            parent_of_tv.removeView(productsManagement);
            parent_of_tv.removeView(productsManagementView);
            parent_of_tv.removeView(productsManagementCreate);

            parent_of_tv.removeView(promotions);
            //  parent_of_tv.removeView(promotionsView);
            parent_of_tv.removeView(promotionsCreateFree);
            parent_of_tv.removeView(promotionsCreateDiscount);

            parent_of_tv.removeView(freeProducts);
            // parent_of_tv.removeView(freeProductsView);
            parent_of_tv.removeView(freeProductsCreate);

            parent_of_tv.removeView(text_operations);

            //parent_of_tv.removeView(adjustments);
            parent_of_tv.removeView(barcodeScanner);
            parent_of_tv.removeView(text_barcodeScanner);

        }


    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_profile:
                startActivity(new Intent(context, RegisterActivity.class).putExtra("user", c_jsonObject.toString()));
                break;
            case R.id.tv_change_password:
                startActivity(new Intent(context, ChangePasswordActivity.class));
                break;
            case R.id.tv_create_warehouse:
                startActivity(new Intent(context, CreateWarehouseActivity.class));
                break;
            case R.id.tv_view_warehouses:
                startActivity(new Intent(context, ListWarehouseActivity.class).putExtra("role_id", selectedRoleId));
                break;
            case R.id.tv_view_products:
                startActivity(new Intent(context, ListProductsActivity.class).putExtra("role_id", selectedRoleId));
                break;
            case R.id.tv_create_product:
                startActivity(new Intent(context, CreateProductActivity.class));
                break;
            case R.id.tv_create_free_product:
                startActivity(new Intent(context, CreateFreeProductsActivity.class));
                break;
            case R.id.tv_open_barcode_scanner:
                launchActivityWithCameraPermission(app.com.project215.activities.barcode.BarCodeActivity.class);
                break;
            case R.id.tv_all_operations:
                startActivity(new Intent(context, MakeOperationActivity.class).putExtra("operation", selectedRoleId.contains("1") ? "external" : "internal"));
                break;
            case R.id.tv_history_operations:
                startActivity(new Intent(context, OperationsHistoryActivity.class).putExtra("role_id", selectedRoleId));
                break;
            case R.id.tv_create_promotion_free:
                startActivity(new Intent(context, CreateFreePromotionActivity.class));
                break;
            case R.id.tv_create_promotion_discount:
                startActivity(new Intent(context, CreateDiscountPromotionActivity.class));
                break;
            default:
                break;
        }
    }


    /**
     * grant permission to access to barcode scanner
     *
     * @param clss
     */
    public void launchActivityWithCameraPermission(Class<?> clss) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            mClss = clss;
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, ZBAR_CAMERA_PERMISSION);
        } else {
            startActivity(new Intent(this, clss).putExtra("role_id", selectedRoleId));
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case ZBAR_CAMERA_PERMISSION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (mClss != null) {
                        startActivity(new Intent(this, mClss).putExtra("role_id", selectedRoleId));
                    }
                } else {
                    Toast.makeText(this, "Please grant camera permission to use the Barcode Scanner", Toast.LENGTH_SHORT).show();
                }
                return;
        }
    }


}
