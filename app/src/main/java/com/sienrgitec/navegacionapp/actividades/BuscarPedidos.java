package com.sienrgitec.navegacionapp.actividades;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Vibrator;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import com.google.gson.Gson;
import com.sienrgitec.navegacionapp.R;
import com.sienrgitec.navegacionapp.configuracion.Globales;
import com.sienrgitec.navegacionapp.modelos.opPedPainani;
import com.sienrgitec.navegacionapp.modelos.opPedPainaniDet;
import com.sienrgitec.navegacionapp.modelos.opPedido;
import com.sienrgitec.navegacionapp.modelos.opPedidoDet;
import com.sienrgitec.navegacionapp.modelos.opPedidoProv;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class BuscarPedidos extends Activity {

    private  static RequestQueue mRequestQueue;
    private Button btnContinuar, btnRechazar;


    public Globales globales;
    public String   url = globales.URL;





    public void BuscarPed(Context vcContext){
        Log.e("BuscarPedidos", "inicio");


        /**Buscando Pedidos**/
        getmRequestQueue(vcContext);

        String urlParams = String.format(url + "opPedPainani?ipiPainani=%1$s&ipcTipo=%2$s", globales.g_ctPainani.getiPainani().toString(), "servicio");
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, urlParams, null, new Response.Listener<JSONObject>() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            JSONObject respuesta = response.getJSONObject("response");
                            Log.i("respuesta--->", respuesta.toString());

                            Boolean vlTienePed = respuesta.getBoolean("oplTienePed");
                            String Mensaje = respuesta.getString("opcError");
                            Boolean Error = respuesta.getBoolean("oplError");




                            if (Error == true) {
                                Log.i("eRROR--->", Mensaje);
                                return;

                            } else {

                                JSONObject ds_opPedido = respuesta.getJSONObject("tt_opPedido");
                                JSONObject ds_opPedidoDet = respuesta.getJSONObject("tt_opPedidoDet");
                                JSONObject ds_opPedidoProv = respuesta.getJSONObject("tt_opPedidoProveedor");
                                JSONObject ds_opPedPainani = respuesta.getJSONObject("tt_opPedPainani");
                                JSONObject ds_opPedPainaniDet = respuesta.getJSONObject("tt_opPedPainaniDet");

                                JSONArray tt_opPedido     = ds_opPedido.getJSONArray("tt_opPedido");
                                JSONArray tt_opPedidoDet  = ds_opPedidoDet.getJSONArray("tt_opPedidoDet");
                                JSONArray tt_opPedidoProv = ds_opPedidoProv.getJSONArray("tt_opPedidoProveedor");
                                JSONArray tt_opPedPainani = ds_opPedPainani.getJSONArray("tt_opPedPainani");
                                JSONArray tt_opPedPainaniDet = ds_opPedPainaniDet.getJSONArray("tt_opPedPainaniDet");

                                globales.g_opPedidoList        = Arrays.asList(new Gson().fromJson(tt_opPedido.toString(), opPedido[].class));
                                globales.g_opPedDetList        = Arrays.asList(new Gson().fromJson(tt_opPedidoDet.toString(), opPedidoDet[].class));
                                globales.g_opPedProvList       = Arrays.asList(new Gson().fromJson(tt_opPedidoProv.toString(), opPedidoProv[].class));
                                globales.g_opPedPainani        = Arrays.asList(new Gson().fromJson(tt_opPedPainani.toString(), opPedPainani[].class));
                                globales.g_opPedPainaniDetList = Arrays.asList(new Gson().fromJson(tt_opPedPainaniDet.toString(), opPedPainaniDet[].class));



                                if(vlTienePed == true){
                                    globales.vglBuscaPed = false;

                                    Vibrator vibrator      = (Vibrator)            vcContext.getSystemService(vcContext.VIBRATOR_SERVICE);
                                    vibrator.vibrate(1000);

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
                                            .setContentTitle("¡Tienes un nuevo pedido!")
                                            .setContentText("Dirigete a la sección de Inicio para continuar")
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


                                }else {
                                    globales.vglBuscaPed = true;

                                }
                            }
                        } catch (JSONException e) {

                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error

                    }
                }) {
            @Override
            public Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("ipiPainani",globales.g_ctPainani.getiPainani().toString());
                params.put("ipcTipo", "servicio");
                return params;
            }
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }
        };
        mRequestQueue.add(jsonObjectRequest);


        /*******************/




    }

    public void getmRequestQueue(Context vcContextR){
        try{
            if (mRequestQueue == null) {
                mRequestQueue = Volley.newRequestQueue(vcContextR);
                //your code
            }
        }catch(Exception e){
            Log.d("Volley",e.toString());
        }
    }

}
