package app.com.project215.activities.user;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import app.com.project215.AppController;
import app.com.project215.BuildConfig;
import app.com.project215.R;
import app.com.project215.util.AppUtility;
import app.com.project215.util.Logger;


public class LocationMapActivity extends AppCompatActivity implements OnMapReadyCallback, View.OnClickListener {

    private Context context;
    String selectedRoleId = null;
    SupportMapFragment mapFragment;

    TextView tv_submit_location;

    double latitude_, longitude_;

    Bundle extras = null;

    static double dragend_latitude, dragend_longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_map);

        context = LocationMapActivity.this;


        initView();


    }


    private void initView() {


        latitude_ = ((AppController) this.getApplication()).returnLatitude();
        longitude_ = ((AppController) this.getApplication()).returnLongitude();

        tv_submit_location = (TextView) findViewById(R.id.tv_submit_location);
        tv_submit_location.setOnClickListener(this);

        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        extras = getIntent().getExtras();

        if (extras != null) {
            selectedRoleId = getIntent().getStringExtra("role_id");

            if (selectedRoleId != null) {
                if (selectedRoleId.contains("3")) {
                    tv_submit_location.setLayoutParams(new LinearLayout.LayoutParams(AppUtility.dpToPx(this, 0), AppUtility.dpToPx(this, 0)));
                }
            }
        }


    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_submit_location:

                Intent intent = new Intent();
                intent.putExtra("editTextValue", "Location Submited");
                setResult(RESULT_OK, intent);
                //     finish();

                ((AppController) this.getApplication()).setLocation(dragend_latitude, dragend_longitude);
                finish();
                break;

        }

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {


        addMarkerWithCameraZooming(LocationMapActivity.this, googleMap, latitude_, longitude_, "Mark Location", true);


    }

    private static void addMarkerWithCameraZooming(Context ctx, GoogleMap googleMap, double latitude, double longitude, String title, boolean dragabble) {
        LatLng current_latlng = new LatLng(latitude, longitude);

        googleMap.addMarker(new MarkerOptions().position(current_latlng)
                .title(title)
                //.snippet(getLocality(current_latlng, ctx))
                //.icon(BitmapDescriptorFactory.fromResource(R.drawable.person_marker))
                .draggable(dragabble)
        );

        CameraPosition cameraPosition = new CameraPosition.Builder().target(new LatLng(latitude, longitude)).zoom(13).tilt(30).build();
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));


        googleMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
            @Override
            public void onMarkerDragStart(Marker markerDragStart) {
                // TODO Auto-generated method stub
                if (BuildConfig.DEBUG) {
                    //code here
                }
            }

            @Override
            public void onMarkerDragEnd(Marker markerDragEnd) {
                if (BuildConfig.DEBUG) {
                }
                Logger.log("Marker drag lat", String.valueOf(markerDragEnd.getPosition().latitude));

                dragend_latitude = markerDragEnd.getPosition().latitude;


                Logger.log("Marker drag long", String.valueOf(markerDragEnd.getPosition().longitude));
                dragend_longitude = markerDragEnd.getPosition().longitude;
            }

            @Override
            public void onMarkerDrag(Marker markerDrag) {
                if (BuildConfig.DEBUG) {
                    //code here
                }
            }
        });

    }


}
