package com.sienrgitec.navegacionapp.servicios;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.JobIntentService;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import com.sienrgitec.navegacionapp.actividades.CreaUbicacion;
import com.sienrgitec.navegacionapp.actividades.Login;
import com.sienrgitec.navegacionapp.actividades.MainActivity;
import com.sienrgitec.navegacionapp.configuracion.Globales;

import static com.sienrgitec.navegacionapp.configuracion.Globales.vglEjecServ;

public class BuscaUbicacionServ extends JobIntentService {
    //private Globales globales;

    public static final int JOB_ID = 1;
    public static void enqueueWork(final Context context, Intent work) {
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        LocationListener locationListener= new LocationListener(){
            public void onLocationChanged(Location location){
                if(vglEjecServ == true) {
                    CreaUbicacion ubicame = new CreaUbicacion();
                    ubicame.CreaRegistro(location.getLatitude(), location.getLongitude(), context);

                }
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {}
            @Override
            public void onProviderEnabled(String provider){}
            @Override
            public void onProviderDisabled(String provider){}


        };
        int permissionChecks = ContextCompat.checkSelfPermission(context,
                Manifest.permission.ACCESS_FINE_LOCATION);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,1000,10,locationListener);

        enqueueWork(context, BuscaUbicacionServ.class, JOB_ID, work);
    }

    @Override
    protected void onHandleWork(@NonNull Intent intent) {
        // your code
    }
}
