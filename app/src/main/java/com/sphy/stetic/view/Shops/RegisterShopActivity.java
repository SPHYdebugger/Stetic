package com.sphy.stetic.view.Shops;

import static com.sphy.stetic.Util.Constants.DATABASE_NAME;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.mapbox.maps.Style;
import com.mapbox.maps.MapView;
import com.mapbox.maps.plugin.annotation.AnnotationConfig;
import com.mapbox.maps.plugin.annotation.AnnotationPlugin;
import com.mapbox.maps.plugin.annotation.AnnotationPluginImplKt;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManager;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManagerKt;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationOptions;
import com.mapbox.maps.plugin.gestures.GesturesPlugin;
import com.mapbox.maps.plugin.gestures.GesturesUtils;
import com.mapbox.maps.plugin.gestures.OnMapClickListener;
import com.sphy.stetic.Db.AppDatabase;
import com.sphy.stetic.Domain.Shop;
import com.sphy.stetic.R;



public class RegisterShopActivity extends AppCompatActivity implements Style.OnStyleLoaded,
        OnMapClickListener {


    private MapView mapView;
    private PointAnnotationManager pointAnnotationManager;
    private GesturesPlugin gesturesPlugin;
    private com.mapbox.geojson.Point currentPoint;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_shop);

        mapView = findViewById(R.id.mapView);
        mapView.getMapboxMap().loadStyleUri(Style.MAPBOX_STREETS, this);
        initializePointAnnotationManager();

        gesturesPlugin = GesturesUtils.getGestures(mapView);
        gesturesPlugin.addOnMapClickListener(this);
    }

    public void createShop(View view) {
        EditText etName = findViewById(R.id.shop_name);
        EditText etAddress = findViewById(R.id.shop_address);
        EditText etCity = findViewById(R.id.shop_city);
        CheckBox checkSolarium = findViewById(R.id.shop_solarium);

        String name = etName.getText().toString();
        String address = etAddress.getText().toString();
        String city = etCity.getText().toString();
        boolean solarium = checkSolarium.isChecked();

        Shop shop = new Shop(name,address,city,solarium,currentPoint.latitude(),currentPoint.longitude());
        AppDatabase db = Room.databaseBuilder(this, AppDatabase.class, DATABASE_NAME).allowMainThreadQueries().build();
        db.shopDao().insert(shop);

        Toast.makeText(this, R.string.shop_insert, Toast.LENGTH_LONG).show();

        etName.setText("");
        etAddress.setText("");
        etCity.setText("");



        etName.requestFocus();
    }

    @Override
    public void onStyleLoaded(@NonNull Style style) {

    }

    private void initializePointAnnotationManager() {
        AnnotationPlugin annotationPlugin = AnnotationPluginImplKt.getAnnotations(mapView);
        AnnotationConfig annotationConfig = new AnnotationConfig();
        pointAnnotationManager = PointAnnotationManagerKt.createPointAnnotationManager(annotationPlugin, annotationConfig);
    }

    private void addMarker(double latitude, double longitude, String title) {
        PointAnnotationOptions pointAnnotationOptions = new PointAnnotationOptions()
                .withPoint(com.mapbox.geojson.Point.fromLngLat(longitude, latitude))
                .withIconImage(BitmapFactory.decodeResource(getResources(), R.mipmap.red_marker))
                .withTextField(title);
        pointAnnotationManager.create(pointAnnotationOptions);
    }


    @Override
    public boolean onMapClick(@NonNull com.mapbox.geojson.Point point) {
        pointAnnotationManager.deleteAll();
        currentPoint = point;
        addMarker(point.latitude(), point.longitude(), "Here");
        return false;
    }
}