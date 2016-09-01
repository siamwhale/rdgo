package th.go.rd.manop.rdrun;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;


public class ServiceActivity extends FragmentActivity implements OnMapReadyCallback {
    // Explicit
    private GoogleMap mMap;
    private String idString,avatarString,nameString, surnameString;
    private ImageView imageView;
    private TextView nameTextView, surnameTextView;
    private int[] avataInts;

    private Marker mconnect;
    private double userLatADouble = 13.806715 , userLngADouble =  100.574794;
    private static final LatLng theconnectionLatLng = new LatLng(13.806715, 100.574794);

    private LocationManager locationManager;
    private Criteria criteria; // แกน แนวดิ่ง


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_service);
        // ดึงค่า จาก Intent ที่ส่งมาจาก MainActivity
        idString = getIntent().getStringExtra("id");
        avatarString = getIntent().getStringExtra("Avata");
        nameString = getIntent().getStringExtra("Name");
        surnameString = getIntent().getStringExtra("Surname");

        // Bind widget
        imageView = (ImageView) findViewById(R.id.imageView7);
        nameTextView = (TextView) findViewById(R.id.textView8);
        surnameTextView = (TextView) findViewById(R.id.textView9);

        // setup location service
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        criteria.setAltitudeRequired(false); // ตัดแกน z
        criteria.setBearingRequired(false);  // ตัดแกน z ห่างจากระดับน้ำทะเล

        //show text
        nameTextView.setText(nameString);
        surnameTextView.setText(surnameString);
        // show image
        MyConstant myConstant = new MyConstant();
        avataInts = myConstant.getAvatarInts();
        imageView.setImageResource(avataInts[Integer.parseInt(avatarString)]);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }



    // กรณีถ้ามีการปิดแอพให้ปิดโลเคชั่น
    @Override
    protected void onStop() {
        super.onStop();
        locationManager.removeUpdates(locationListener);
    }
    // กรณีถ้ามีการกลับมาจากพัก
    @Override
    protected void onResume() {
        super.onResume();
        locationManager.removeUpdates(locationListener); // clear old location
        Location networkLocation = myFindLocation(LocationManager.NETWORK_PROVIDER); // ตำแหน่งจาก network Provider
        if (networkLocation != null) {
            userLatADouble = networkLocation.getLatitude();
            userLngADouble = networkLocation.getLongitude();
        }

        Location gpsLocation = myFindLocation(LocationManager.GPS_PROVIDER); // ตำแหน่งจาก GPS
        if (gpsLocation != null) {
            userLatADouble = gpsLocation.getLatitude();
            userLngADouble = gpsLocation.getLongitude();
        }
    }

    public Location myFindLocation(String strProvider) { // ค้นหาตำแหน่งของเครื่อง
        Location location = null;
        if (locationManager.isProviderEnabled(strProvider)) {  // ตรวจสอบว่ามี GPS เปิดไหม
            locationManager.requestLocationUpdates(strProvider,1000,10,locationListener); //ค้นทุก 1 วินาที หรือ 10 เมตร กำหนดให้ค้นหาตำแหน่ง
            location = locationManager.getLastKnownLocation(strProvider);
        } else {
            Log.d("1SepV1", "Cannot find location");
        }
        return location;
    }
    public LocationListener locationListener = new LocationListener() { // android location package
        @Override
        public void onLocationChanged(Location location) { // มีการเปลี่ยนตำแหน่ง
            userLatADouble = location.getLatitude();
            userLngADouble = location.getLongitude();
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {

        }
    };

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // พิกัดบนแผนที่
        LatLng latLng = new LatLng(userLatADouble, userLngADouble);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,16)); // วิ่งแบบ Zoom ตำแหน่ง มีทั้งหมด 20 layers
        // loop เรียกทำงานหาพิกัด
        myLoop();

//        mconnect = mMap.addMarker(new MarkerOptions()
//                .position(theconnectionLatLng)
//                .title("เดอะคอนเน็กชั่น");

    }

    private void myLoop() {
        // To do -
        Log.d("1SepV2","Lat = " + userLatADouble);
        Log.d("1SepV2","Lng = " + userLngADouble);

        // Post delay
        Handler handler = new Handler(); // android.os package
        handler.postDelayed(new Runnable() {
            @Override
            public void run() { // ทำงานทุก 1000 ms
                myLoop();
            }
        },1000);
    }
}
