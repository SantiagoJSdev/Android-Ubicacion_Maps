package com.example.mapsnuevo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.Toast;

import com.example.mapsnuevo.databinding.ActivityMainBinding;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

public class MainActivity extends AppCompatActivity {
    private FusedLocationProviderClient fusedLocationClient;
    private ActivityMainBinding main;
    String longitud;
    String latitud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        main = ActivityMainBinding.inflate(getLayoutInflater());
        View viewNew = main.getRoot();
        setContentView(viewNew);

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 120);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        //Obtener mi localizacion
        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {

                        longitud = String.valueOf(location.getLongitude());
                        Log.e("Location", String.valueOf(longitud));
                        latitud = String.valueOf(location.getLatitude());
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {
                            //mesagge = "entre";
                            // Logic to handle location object
                        }
                    }
                });
    }

    public void onClick(View view) {

        String latitudCliente = "10.9444685";
        String longitudCliente = "-63.9477055";
        //Ubicacion destino final
        Uri gmmIntentUri = Uri.parse("google.navigation:q="+latitudCliente+","+longitudCliente+"&mode=d");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);

    /*
        String ubicacion;
        ubicacion = "https://www.google.com/maps/@" + latitud  + "," + longitud +  ",15z";
        Uri uri = Uri.parse(ubicacion);
        if (URLUtil.isValidUrl(uri.toString())) {
        startActivity( new Intent(Intent.ACTION_VIEW, uri));
        }
        */
        //main.text1.setText(altitud);
        // main.text1.setText(mesagge);
        // Toast toast = Toast.makeText(this, ubicacion, Toast.LENGTH_SHORT);
        // toast.show();



    }


}