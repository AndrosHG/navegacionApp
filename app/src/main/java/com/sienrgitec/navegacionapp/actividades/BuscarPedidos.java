package com.sienrgitec.navegacionapp.actividades;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Vibrator;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.sienrgitec.navegacionapp.R;

import java.util.Date;


public class BuscarPedidos extends Activity {





    public void BuscarPed(Context vcContext){
        Log.e("BuscarPedidos", "inicio");
       /* NotificationCompat.Builder notificacion = null;
       int idUnica = 6192523;*/

        //NotificationManager nm = (NotificationManager) vcContext.getSystemService(vcContext.NOTIFICATION_SERVICE);
        //nm.notify(idUnica,notificacion.build());*/
        Vibrator vibrator      = (Vibrator)            vcContext.getSystemService(vcContext.VIBRATOR_SERVICE);
        vibrator.vibrate(3000);

        /*Intent intent = new Intent(vcContext,Login.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(vcContext,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        notificacion.setContentIntent(pendingIntent);
        notificacion.setTicker("Nuevo pedido");
        notificacion.setWhen(System.currentTimeMillis());
        notificacion.setContentTitle("Nuevo Pedido");
        notificacion.setContentText("tienes un nuevo pedido ");*/


        NotificationManager notificationManager =
                (NotificationManager) vcContext.getSystemService(vcContext.NOTIFICATION_SERVICE);
        Intent intent = new Intent(vcContext, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(vcContext, 0, intent, PendingIntent.FLAG_ONE_SHOT);
        Uri soundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            @SuppressLint("WrongConstant")
            NotificationChannel notificationChannel=new NotificationChannel("my_notification","n_channel",NotificationManager.IMPORTANCE_MAX);
            notificationChannel.setDescription("description");
            notificationChannel.setName("Channel Name");
            assert notificationManager != null;
            notificationManager.createNotificationChannel(notificationChannel);
        }


        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(vcContext)
                .setSmallIcon(R.drawable.common_google_signin_btn_icon_dark)
                //.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.common_full_open_on_phone))
                .setContentTitle("messageTitle")
                .setContentText("messageBody")
                .setAutoCancel(true)
                .setSound(soundUri)
                .setContentIntent(pendingIntent)
                .setDefaults(Notification.DEFAULT_ALL)
                .setOnlyAlertOnce(true)
                .setChannelId("my_notification")
                .setColor(Color.parseColor("#3F5996"));

        //.setProgress(100,50,false);
        assert notificationManager != null;
        int m = (int) ((new Date().getTime() / 1000L) % Integer.MAX_VALUE);
        notificationManager.notify(m, notificationBuilder.build());







    }

}
