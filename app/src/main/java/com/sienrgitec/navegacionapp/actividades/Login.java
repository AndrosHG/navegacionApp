package com.sienrgitec.navegacionapp.actividades;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
import com.sienrgitec.navegacionapp.modelos.ctPainani;
import com.sienrgitec.navegacionapp.modelos.ctUsuario;
import com.sienrgitec.navegacionapp.servicios.BuscaUbicacionServ;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class Login extends AppCompatActivity {

    private Globales globales;
    private String   url = globales.URL;
    private EditText etUsuario, etPassword;
    private Button   btnEntrar;

    private static RequestQueue mRequestQueue;

    private static final int idNotifica = 10;
    NotificationCompat.Builder notificacion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsuario  = (EditText) findViewById(R.id.etUsuario);
        etPassword = (EditText) findViewById(R.id.etPassw);
        btnEntrar  = (Button)   findViewById(R.id.loginBtn);

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Entrar();
            }
        });



        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        BuscaUbicacionServ.enqueueWork(Login.this, new Intent());
                        //finish();
                    }
                });
            }
        }, 2000, 20000);
    }

    public void Entrar(){
        btnEntrar.setEnabled(false);

        final String vcUsuLog = etUsuario.getText().toString();
        final String password = etPassword.getText().toString();

        if (etUsuario.getText().toString().isEmpty()) {
            AlertDialog.Builder myBuild = new AlertDialog.Builder(Login.this);
            myBuild.setMessage("No se capturo el nombre de usuario");
            myBuild.setTitle(Html.fromHtml("<font color ='#FF0000'> ERROR </font>"));
            myBuild.setPositiveButton("ACEPTAR", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                    btnEntrar.setEnabled(true);

                }
            });
            AlertDialog dialog = myBuild.create();
            dialog.show();
            return;
        }

        if (etPassword.getText().toString().isEmpty()) {
            AlertDialog.Builder myBuild = new AlertDialog.Builder(Login.this);
            myBuild.setMessage("No se capturo el password del usuario");
            myBuild.setTitle(Html.fromHtml("<font color ='#FF0000'> ERROR </font>"));
            myBuild.setPositiveButton("ACEPTAR", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                    btnEntrar.setEnabled(true);

                }
            });
            AlertDialog dialog = myBuild.create();
            dialog.show();
            return;
        }

        getmRequestQueue();

        String urlParams = String.format(url + "CargaTitlani?ipcUsuario=%1$s&ipcPassword=%2$s", vcUsuLog, password);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, urlParams, null, new Response.Listener<JSONObject>() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            JSONObject respuesta = response.getJSONObject("response");
                            Log.i("respuesta--->", respuesta.toString());

                            String Mensaje = respuesta.getString("opcError");
                            Boolean Error = respuesta.getBoolean("oplError");
                            JSONObject ds_ctPainani = respuesta.getJSONObject("tt_ctPainani");
                            JSONObject ds_ctUsuario = respuesta.getJSONObject("tt_ctUsuario");


                            JSONArray tt_ctUsuario = ds_ctUsuario.getJSONArray("tt_ctUsuario");
                            JSONArray tt_ctPainani = ds_ctPainani.getJSONArray("tt_ctPainani");


                            globales.g_ctUsuarioList = Arrays.asList(new Gson().fromJson(tt_ctUsuario.toString(), ctUsuario[].class));
                            globales.g_ctPainaniList = Arrays.asList(new Gson().fromJson(tt_ctPainani.toString(), ctPainani[].class));

                            if (Error == true) {
                                btnEntrar.setEnabled(true);
                                Toast.makeText(Login.this, "Error: " + Mensaje, Toast.LENGTH_SHORT).show();
                                return;

                            } else {
                                globales.g_ctUsuario = globales.g_ctUsuarioList.get(0);
                                globales.g_ctPainani = globales.g_ctPainaniList.get(0);

                                    Intent Home = new Intent(Login.this, MainActivity.class);
                                    startActivity(Home);
                                    finish();

                            }
                        } catch (JSONException e) {
                            btnEntrar.setEnabled(true);
                            AlertDialog.Builder myBuild = new AlertDialog.Builder(Login.this);
                            myBuild.setMessage("Error en la conversión de Datos. Vuelva a Intentar. " + e);
                            myBuild.setTitle(Html.fromHtml("<font color ='#FF0000'> ERROR CONVERSION </font>"));
                            myBuild.setPositiveButton("ACEPTAR", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();

                                }
                            });
                            AlertDialog dialog = myBuild.create();
                            dialog.show();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        btnEntrar.setEnabled(true);
                        // TODO: Handle error
                        Log.i("Error Respuesta", error.toString());
                        AlertDialog.Builder myBuild = new AlertDialog.Builder(Login.this);
                        myBuild.setMessage("No se pudo conectar con el servidor. Vuelva a Intentar. " + error.toString());
                        myBuild.setTitle(Html.fromHtml("<font color ='#FF0000'> ERROR RESPUESTA </font>"));
                        myBuild.setPositiveButton("ACEPTAR", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });

                        AlertDialog dialog = myBuild.create();
                        dialog.show();
                    }
                }) {
            @Override
            public Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("ipcUsuario", vcUsuLog);
                params.put("ipcPassword", password);

                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }
        };
        // Access the RequestQueue through your singleton class.
        mRequestQueue.add(jsonObjectRequest);
    }

    public void getmRequestQueue(){
        try{
            if (mRequestQueue == null) {
                mRequestQueue = Volley.newRequestQueue(getApplicationContext());
                //your code
            }
        }catch(Exception e){
            Log.d("Volley",e.toString());
        }
    }




}