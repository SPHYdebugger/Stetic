package com.sphy.stetic.view.Shops;

import static com.sphy.stetic.Util.Constants.DATABASE_NAME;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.mapbox.maps.CameraOptions;
import com.mapbox.maps.MapView;
import com.mapbox.maps.Style;
import com.mapbox.maps.plugin.annotation.AnnotationConfig;
import com.mapbox.maps.plugin.annotation.AnnotationPlugin;
import com.mapbox.maps.plugin.annotation.AnnotationPluginImplKt;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManager;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManagerKt;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationOptions;
import com.sphy.stetic.Db.AppDatabase;
import com.sphy.stetic.Domain.Shop;
import com.sphy.stetic.R;

public class ShopDetailsActivity extends AppCompatActivity implements Style.OnStyleLoaded{

    private MapView mapViewShopDetails;
    private PointAnnotationManager pointAnnotationManager;
    private com.mapbox.geojson.Point point;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_details);

        mapViewShopDetails = findViewById(R.id.mapViewShopDetails);
        mapViewShopDetails.getMapboxMap().loadStyleUri(Style.MAPBOX_STREETS, this);
        initializePointAnnotationManager();

        Intent intent = getIntent();
        String shopName = intent.getStringExtra("name");
        AppDatabase db = Room.databaseBuilder(this, AppDatabase.class, DATABASE_NAME).allowMainThreadQueries().build();
        Shop shop = db.shopDao().findByName(shopName);
        loadShop(shop);

        Button backButton = findViewById(R.id.btnBack);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShopDetailsActivity.this, ShopListActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void loadShop(Shop shop) {

        String name = shop.getName();
        String address = shop.getAddress();
        String city = shop.getCity();
        boolean isSolarium = shop.isSolarium();


        TextView tvname = findViewById(R.id.detail_name);
        TextView tvAddress = findViewById(R.id.detail_address);
        TextView tvCity = findViewById(R.id.detail_city);
        TextView tvSolarium = findViewById(R.id.detail_solarium);


        tvname.setText(name);
        tvAddress.setText(address);
        tvCity.setText(city);
        tvSolarium.setText(isSolarium ? "Sí" : "No");
    }


    @Override
    public void onStyleLoaded(@NonNull Style style) {
        // Obtener la información de la tienda de la base de datos (nombre, latitud, longitud)
        Intent intent = getIntent();
        String shopName = intent.getStringExtra("name");
        AppDatabase db = Room.databaseBuilder(this, AppDatabase.class, DATABASE_NAME).allowMainThreadQueries().build();
        Shop shop = db.shopDao().findByName(shopName);

        // Añadir el marcador y centrar el foco sobre el marker
        addMarker(shop.getLatitude(), shop.getLongitude(), shop.getName());
        point = com.mapbox.geojson.Point.fromLngLat(shop.getLongitude(), shop.getLatitude());
        mapViewShopDetails.getMapboxMap().setCamera(
                new CameraOptions.Builder()
                        .center(point)
                        .zoom(12.0)
                        .build()
        );
    }

    private void initializePointAnnotationManager() {
        AnnotationPlugin annotationPlugin = AnnotationPluginImplKt.getAnnotations(mapViewShopDetails);
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


    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_bar, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.edit){

            return true;
        }
         if (item.getItemId() == R.id.delete) {
         Intent intent = getIntent();
         String shopName = intent.getStringExtra("name");
        AppDatabase db = Room.databaseBuilder(this, AppDatabase.class, DATABASE_NAME).allowMainThreadQueries().build();
        db.shopDao().deleteByName(shopName);
        finish();
        return true;
    }
        return super.onOptionsItemSelected(item);
    }
}