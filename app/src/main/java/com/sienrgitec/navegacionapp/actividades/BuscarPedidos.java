package com.sienrgitec.navegacionapp.actividades;

import android.app.Activity;
import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;


public class BuscarPedidos extends Activity {


    private static  final int idUnica = 6192523;
    NotificationCompat.Builder notificacion;

    public void BuscarPed(Context vcContext){
        Log.e("BuscarPedidos", "inicio");


        Vibrator vibrator = (Vibrator)vcContext.getSystemService(vcContext.VIBRATOR_SERVICE);
        vibrator.vibrate(3000);



/*        notificacion.setTicker("Nuevo pedido");
        notificacion.setWhen(System.currentTimeMillis());
        notificacion.setContentTitle("Nuevo Pedido");
        notificacion.setContentText("tienes un nuevo pedido ");*/


        Intent intent = new Intent(vcContext,Login.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(vcContext,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        notificacion.setContentIntent(pendingIntent);
        ///sonidos

        NotificationManager nm = (NotificationManager) getSystemService(vcContext.NOTIFICATION_SERVICE);

        nm.notify(idUnica,notificacion.build());


    }

}
