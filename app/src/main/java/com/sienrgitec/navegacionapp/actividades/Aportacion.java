package com.sienrgitec.navegacionapp.actividades;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sienrgitec.navegacionapp.R;
import com.sienrgitec.navegacionapp.adaptadores.ctComisionAdapter;
import com.sienrgitec.navegacionapp.adaptadores.ctRazonesAdapter;
import com.sienrgitec.navegacionapp.adaptadores.ctVehiculoAdapter;
import com.sienrgitec.navegacionapp.configuracion.Globales;
import com.sienrgitec.navegacionapp.modelos.ctComisiones;
import com.sienrgitec.navegacionapp.modelos.ctRazones;
import com.sienrgitec.navegacionapp.modelos.ctVehiculo;
import com.sienrgitec.navegacionapp.modelos.opDispPainani;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Aportacion extends AppCompatActivity {

    public Globales globales;
    public String   url = globales.URL;
    private static RequestQueue mRequestQueue;

    public static List<ctComisiones> listctComision    = null;
    public static ArrayList<ctComisiones> listctComisionFinal = new ArrayList<>();

    public static List<ctVehiculo> listctVehiculo    = null;
    public static ArrayList<ctVehiculo> listctVehiculoFinal = new ArrayList<>();
    public static ArrayList<opDispPainani>  opDispPainani       = new ArrayList<>();

    public RecyclerView recyclerCom, recyclerVehiculo;
    public Button btnGuardar;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aportacion);

        recyclerCom      = (RecyclerView) findViewById(R.id.listaporta);
        recyclerCom.setLayoutManager(new LinearLayoutManager(Aportacion.this,LinearLayoutManager.VERTICAL,false));

        recyclerVehiculo      = (RecyclerView) findViewById(R.id.listavehiculo);
        recyclerVehiculo.setLayoutManager(new LinearLayoutManager(Aportacion.this,LinearLayoutManager.VERTICAL,false));

        btnGuardar = (Button) findViewById(R.id.btnEmpezar);

        btnGuardar.setOnClickListener(v ->{
           GenerarInicio();
        });

        CargaComisiones();
        CargaVehiculos();
    }
    public void getmRequestQueue()    {
        try{
            if (mRequestQueue == null) {
                mRequestQueue = Volley.newRequestQueue(Aportacion.this);
                //your code
            }
        }catch(Exception e){
            Log.d("Volley",e.toString());
        }
    }
    public void CargaComisiones(){
        listctComisionFinal.clear();
        getmRequestQueue();
        String urlParams = String.format(url + "ctComisiones?ipiPersona=%1$s", globales.g_ctUsuario.getiTipoPersona());
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

                            JSONObject ds_Comision    = respuesta.getJSONObject("tt_ctComisiones");
                            JSONArray tt_ctComisiones = ds_Comision.getJSONArray("tt_ctComisiones");

                            listctComision    = Arrays.asList(new Gson().fromJson(tt_ctComisiones.toString(), ctComisiones[].class));

                            if (Error == true) {
                                Toast.makeText(Aportacion.this, "Error Progress : " + Mensaje, Toast.LENGTH_SHORT).show();
                                return;

                            } else {
                                for(ctComisiones obj: listctComision){
                                    ctComisiones pasaLista = new ctComisiones();
                                    pasaLista.setiComision(obj.getiComision());
                                    pasaLista.setDeValor(obj.getDeValor());
                                    listctComisionFinal.add(pasaLista);
                                }

                                ctComisionAdapter adapDet = new ctComisionAdapter(Aportacion.this,null);
                                adapDet.setList((List<ctComisiones>) listctComisionFinal);
                                recyclerCom.setAdapter(adapDet);

                            }
                        } catch (JSONException e) {
                            Toast.makeText(Aportacion.this, "Error en la conversión de Datos: ", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Aportacion.this, "No se pudo conectar con el servidor: " + error.toString(), Toast.LENGTH_SHORT).show();

                    }
                }) {
            @Override
            public Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("ipiPersona", globales.g_ctUsuario.getiTipoPersona().toString());
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
    public void CargaVehiculos(){

        listctVehiculoFinal.clear();
        getmRequestQueue();
        String urlParams = String.format(url + "ctVehiculo?ipiPainani=%1$s", globales.g_ctUsuario.getiPersona());
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

                            JSONObject ds_Vehiculo    = respuesta.getJSONObject("tt_ctVehiculo");
                            JSONArray tt_ctVehiculo = ds_Vehiculo.getJSONArray("tt_ctVehiculo");

                            listctVehiculo    = Arrays.asList(new Gson().fromJson(tt_ctVehiculo.toString(), ctVehiculo[].class));

                            if (Error == true) {
                                Toast.makeText(Aportacion.this, "Error Progress : " + Mensaje, Toast.LENGTH_SHORT).show();
                                return;

                            } else {
                                for(ctVehiculo obj: listctVehiculo){
                                    ctVehiculo pasaLista = new ctVehiculo();
                                    pasaLista.setiVehiculo(obj.getiVehiculo());
                                    pasaLista.setcVehiculo(obj.getcVehiculo());
                                    listctVehiculoFinal.add(pasaLista);
                                }

                                ctVehiculoAdapter adapter = new ctVehiculoAdapter(Aportacion.this,null);
                                adapter.setList((List<ctVehiculo>) listctVehiculoFinal);
                                recyclerVehiculo.setAdapter(adapter);

                            }
                        } catch (JSONException e) {
                            Toast.makeText(Aportacion.this, "Error en la conversión de Datos: ", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Aportacion.this, "No se pudo conectar con el servidor: " + error.toString(), Toast.LENGTH_SHORT).show();

                    }
                }) {
            @Override
            public Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("ipiPainani", globales.g_ctUsuario.getiPersona().toString());
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
    public void GenerarInicio(){

        if(globales.vgiComision == 0){
            Toast.makeText(Aportacion.this, "Debes seleccionar un pordentaje que deseas aportar." , Toast.LENGTH_SHORT).show();
            return;
        }

        if(globales.vgiVehiculo == 0){
            Toast.makeText(Aportacion.this, "Debes seleccionar el vehículo que deseas utilizar." , Toast.LENGTH_SHORT).show();
            return;
        }

        final ProgressDialog nDialog;
        nDialog = new ProgressDialog(Aportacion.this);
        nDialog.setMessage("Cargando...");
        nDialog.setTitle("Iniciando Operaciones");
        nDialog.setIndeterminate(false);

        opDispPainani objComisionDispP = new opDispPainani();
        objComisionDispP.setiPainani(globales.g_ctUsuario.getiPersona());
        objComisionDispP.setDtFecha(globales.g_opDispPList.get(0).getDtFecha());
        objComisionDispP.setiComision(globales.vgiComision);
        objComisionDispP.setDtCheckIn(globales.g_opDispPList.get(0).getDtCheckIn());
        objComisionDispP.setiCheckIn(globales.g_opDispPList.get(0).getiCheckIn());
        objComisionDispP.setDtCheckOut(globales.g_opDispPList.get(0).getDtCheckOut());
        objComisionDispP.setiCheckOut(globales.g_opDispPList.get(0).getiCheckOut());
        objComisionDispP.setiEstadoProceso(globales.g_opDispPList.get(0).getiEstadoProceso());
        objComisionDispP.setDeUltLat(globales.vg_deLatitud);
        objComisionDispP.setDeUltLong(globales.vg_deLongitud);
        objComisionDispP.setiVehiculo(globales.vgiVehiculo);

        opDispPainani.add(objComisionDispP);

        JSONObject jsonBody = new JSONObject();
        JSONObject jsonParams = new JSONObject();
        JSONObject jsonDataSet = new JSONObject();

        final Gson gson = new Gson();

        String JS_opDispPainani = gson.toJson(
                opDispPainani,
                new TypeToken<ArrayList<opDispPainani>>() {
                }.getType());
        try {
            JSONArray opDispPainaniJS   = new JSONArray(JS_opDispPainani);
            jsonDataSet.put("tt_opDispPainani",  opDispPainaniJS);
            jsonParams.put("ds_opDispPainani", jsonDataSet);
            jsonBody.put("request", jsonParams);
            Log.i("Response", jsonBody.toString());

        } catch (JSONException e) {
            e.printStackTrace();
            nDialog.dismiss();
            Toast.makeText(Aportacion.this, "Error: "  + e.getMessage(), Toast.LENGTH_SHORT).show();
            return;
        }


        getmRequestQueue();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.PUT, url + "opDispPainani/", jsonBody, new Response.Listener<JSONObject>() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject respuesta = response.getJSONObject("response");
                            Log.i("respuesta resrt->", "mensaje: " + respuesta.toString());

                            Boolean Error = respuesta.getBoolean("oplError");
                            String Mensaje = respuesta.getString("opcError");


                            if (Error == true) {
                                nDialog.dismiss();

                                Toast.makeText(Aportacion.this, "Error: "  + Mensaje, Toast.LENGTH_SHORT).show();
                                return;

                            } else {
                                Toast.makeText(Aportacion.this, "Iniciando operaciones"  , Toast.LENGTH_SHORT).show();
//                                startActivity(new Intent(AsignaComision.this, Home.class));
//                                finish();

                                Intent Home = new Intent(Aportacion.this, MainActivity.class);
                                startActivity(Home);
                            }

                        } catch (JSONException e) {
                            Log.i("Error JSONExcepcion", e.getMessage());

                            nDialog.dismiss();
                            Log.i("Error JSONExcepcion", e.getMessage());

                            Toast.makeText(Aportacion.this, "Error de conversion de Datos: "  + e.getMessage(), Toast.LENGTH_SHORT).show();
                            return;

                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        Log.i("Error", error.toString());
                        nDialog.dismiss();

                        Toast.makeText(Aportacion.this, "Error Response: "  + error.toString(), Toast.LENGTH_SHORT).show();
                        return;

                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }
        };
        mRequestQueue.add(jsonObjectRequest);





















    }


}