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

        NotificationManager nm = (NotificationManager) vcContext.getSystemService(vcContext.NOTIFICATION_SERVICE);
        Vibrator vibrator      = (Vibrator)            vcContext.getSystemService(vcContext.VIBRATOR_SERVICE);
        nm.notify(idUnica,notificacion.build());
        vibrator.vibrate(3000);

        Intent intent = new Intent(vcContext,Login.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(vcContext,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        notificacion.setContentIntent(pendingIntent);
        notificacion.setTicker("Nuevo pedido");
        notificacion.setWhen(System.currentTimeMillis());
        notificacion.setContentTitle("Nuevo Pedido");
        notificacion.setContentText("tienes un nuevo pedido ");
    }

}
