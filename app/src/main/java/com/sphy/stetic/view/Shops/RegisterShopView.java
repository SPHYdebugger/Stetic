package com.sphy.stetic.view.Shops;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.mapbox.maps.MapView;
import com.mapbox.maps.Style;
import com.mapbox.maps.plugin.annotation.AnnotationConfig;
import com.mapbox.maps.plugin.annotation.AnnotationPlugin;
import com.mapbox.maps.plugin.annotation.AnnotationPluginImplKt;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManager;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManagerKt;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationOptions;
import com.mapbox.maps.plugin.gestures.GesturesPlugin;
import com.mapbox.maps.plugin.gestures.GesturesUtils;
import com.mapbox.maps.plugin.gestures.OnMapClickListener;
import com.sphy.stetic.Domain.Shop;
import com.sphy.stetic.R;
import com.sphy.stetic.contract.Shops.ShopRegisterContract;
import com.sphy.stetic.presenter.Shops.ShopRegisterPresenter;

public class RegisterShopView extends AppCompatActivity implements ShopRegisterContract.View, Style.OnStyleLoaded, OnMapClickListener {

    private ShopRegisterPresenter presenter;

    private MapView mapView;
    private PointAnnotationManager pointAnnotationManager;
    private GesturesPlugin gesturesPlugin;
    private com.mapbox.geojson.Point currentPoint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_shop);

        presenter = new ShopRegisterPresenter(this);

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

        CheckBox cbSolarium = findViewById(R.id.shop_solarium);


        String name = etName.getText().toString();
        String address = etAddress.getText().toString();
        String city  = etCity.getText().toString();
        double latitude = currentPoint.latitude();
        double longitude = currentPoint.longitude();
        boolean solarium = cbSolarium.isChecked();


        Shop shop = new Shop(name, address, city, solarium, latitude, longitude);
        presenter.insertShop(shop);
    }

    @Override
    public void showInsertSuccessMessage() {
        Toast.makeText(this,"Tienda insertado correctamente", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showInsertErrorMessage() {
        Toast.makeText(this, "Error al insertar la tienda", Toast.LENGTH_LONG).show();
    }

    @Override
    public void clearFields() {


        EditText etName = findViewById(R.id.shop_name);
        EditText etAddress = findViewById(R.id.shop_address);
        EditText etCity = findViewById(R.id.shop_city);
        CheckBox cbSolarium = findViewById(R.id.shop_solarium);



        etName.setText("");
        etAddress.setText("");
        etCity.setText("");
        cbSolarium.clearFocus();


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
