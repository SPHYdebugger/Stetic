package com.sphy.stetic.view.Shops;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.mapbox.geojson.Point;
import com.mapbox.maps.CameraOptions;
import com.mapbox.maps.MapView;
import com.mapbox.maps.Style;
import com.mapbox.maps.plugin.annotation.AnnotationConfig;
import com.mapbox.maps.plugin.annotation.AnnotationPlugin;
import com.mapbox.maps.plugin.annotation.AnnotationPluginImplKt;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManager;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManagerKt;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationOptions;
import com.mapbox.maps.plugin.gestures.GesturesPlugin;
import com.sphy.stetic.Db.AppDatabase;
import com.sphy.stetic.Db.ProductDao;
import com.sphy.stetic.Domain.Product;
import com.sphy.stetic.Domain.Shop;
import com.sphy.stetic.R;
import com.sphy.stetic.contract.Shops.ShopDetailsContract;
import com.sphy.stetic.presenter.Shops.ShopDetailsPresenter;

public class ShopDetailsView extends AppCompatActivity implements ShopDetailsContract.View,  Style.OnStyleLoaded {

    private TextView tvId;
    private TextView tvName;
    private TextView tvAddress;
    private TextView tvCity;
    private TextView tvSolarium;
    private TextView tvLatitude;
    private TextView tvLongitude;

    private PointAnnotationManager pointAnnotationManager;
    private MapView mapView;



    private ShopDetailsContract.Presenter presenter;
    private long id;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_details);

        tvId = findViewById(R.id.detail_id);
        tvName = findViewById(R.id.detail_name);
        tvAddress = findViewById(R.id.detail_address);
        tvCity = findViewById(R.id.detail_city);
        tvSolarium = findViewById(R.id.detail_solarium);




        presenter = new ShopDetailsPresenter(this);

        Intent intent = getIntent();
        id = intent.getLongExtra("id", id);
        presenter.getShopDetails(id);


        mapView = findViewById(R.id.mapViewShopDetails);
        mapView.getMapboxMap().loadStyleUri(Style.MAPBOX_STREETS, this);
        initializePointAnnotationManager();
    }

    private void initializePointAnnotationManager() {
        AnnotationPlugin annotationPlugin = AnnotationPluginImplKt.getAnnotations(mapView);
        AnnotationConfig annotationConfig = new AnnotationConfig();
        pointAnnotationManager = PointAnnotationManagerKt.createPointAnnotationManager(annotationPlugin, annotationConfig);
    }

    @Override
    public void onStyleLoaded(@NonNull Style style) {
        Intent intent = getIntent();
        double latitude = intent.getDoubleExtra("latitude", 0.0);
        double longitude = intent.getDoubleExtra("longitude", 0.0);
        String title = intent.getStringExtra("name");

        addMarker(latitude, longitude, title);
        setCameraPosition(latitude, longitude);

    }

    private void addMarker(double latitude, double longitude, String title) {
        PointAnnotationOptions pointAnnotationOptions = new PointAnnotationOptions()
                .withPoint(Point.fromLngLat(longitude, latitude))
                .withIconImage(BitmapFactory.decodeResource(getResources(), R.mipmap.red_marker))
                .withTextField(title);
        pointAnnotationManager.create(pointAnnotationOptions);




    }


    private void setCameraPosition(double latitude, double longitude) {
        CameraOptions cameraPosition = new CameraOptions.Builder()
                .center(Point.fromLngLat(longitude, latitude))
                .pitch(45.0)
                .zoom(15.5)
                .bearing(-17.6)
                .build();
        mapView.getMapboxMap().setCamera(cameraPosition);
    }



    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        id = intent.getLongExtra("id",id);
        presenter.getShopDetails(id);
    }

    @Override
    public void displayShopDetails(Shop shop) {
        tvId.setText(String.valueOf(shop.getId()));
        tvName.setText(shop.getName());
        tvAddress.setText(shop.getAddress());
        tvCity.setText(shop.getCity());
        tvSolarium.setText(String.valueOf(shop.isSolarium()));


        addMarker(shop.getLatitude(), shop.getLongitude(), shop.getName());
    }



    @Override
    public void showDeleteSuccessMessage() {
        Toast.makeText(this, "Tienda eliminada correctamente", Toast.LENGTH_LONG).show();
        finish();
    }

    @Override
    public void showDeleteErrorMessage() {
        Toast.makeText(this, "Error al eliminar la tienda", Toast.LENGTH_LONG).show();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_bar, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.edit){
            Intent intent = new Intent(ShopDetailsView.this, ShopEditView.class);
            intent.putExtra("id", id);
            startActivity(intent);

            return true;
        }
        if (item.getItemId() == R.id.delete) {
            presenter.deleteShop(id);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }




}