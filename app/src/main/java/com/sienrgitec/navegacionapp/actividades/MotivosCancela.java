package com.sienrgitec.navegacionapp.actividades;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
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
import com.sienrgitec.navegacionapp.adaptadores.ctEvaluacionAdapter;
import com.sienrgitec.navegacionapp.adaptadores.ctRazonesAdapter;
import com.sienrgitec.navegacionapp.configuracion.Globales;
import com.sienrgitec.navegacionapp.modelos.ctEvaluacion;
import com.sienrgitec.navegacionapp.modelos.ctRazones;
import com.sienrgitec.navegacionapp.modelos.opPausaPainani;
import com.sienrgitec.navegacionapp.ui.home.HomeFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MotivosCancela extends AppCompatActivity {

    public Context c;
    public Globales globales;
    public String   url = globales.URL;
    private static RequestQueue mRequestQueue;

    public static List<ctRazones> listctRazones    = null;
    public static ArrayList<ctRazones> listctRazonesFinal = new ArrayList<>();
    public RecyclerView recycler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motivos_cancela);
        c = getBaseContext();

        recycler      = (RecyclerView) findViewById(R.id.listCancela);
        recycler.setLayoutManager(new LinearLayoutManager(MotivosCancela.this,LinearLayoutManager.VERTICAL,false));

        CargaRazones();
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

    public void CargaRazones(){
        listctRazonesFinal.clear();
        getmRequestQueue(c);
        String urlParams = String.format(url + "ctRazones?ipcTipoR=%1$s", "PAINANI" );
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

                            JSONObject ds_Razones = respuesta.getJSONObject("tt_ctRazones");
                            JSONArray tt_ctRazones  = ds_Razones.getJSONArray("tt_ctRazones");

                            listctRazones    = Arrays.asList(new Gson().fromJson(tt_ctRazones.toString(), ctRazones[].class));


                            if (Error == true) {
                                Toast.makeText(MotivosCancela.this, "Error Progress : " + Mensaje, Toast.LENGTH_SHORT).show();
                                return;

                            } else {
                                for(ctRazones obj: listctRazones){
                                    ctRazones pasaLista = new ctRazones();
                                    pasaLista.setiRazon(obj.getiRazon());
                                    pasaLista.setcRazon(obj.getcRazon());
                                    listctRazonesFinal.add(pasaLista);
                                }

                                ctRazonesAdapter adapDet = new ctRazonesAdapter(MotivosCancela.this,null);
                                adapDet.setList((List<ctRazones>) listctRazonesFinal);
                                recycler.setAdapter(adapDet);

                            }
                        } catch (JSONException e) {

                            Toast.makeText(MotivosCancela.this, "Error en la conversión de Datos: ", Toast.LENGTH_SHORT).show();


                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MotivosCancela.this, "No se pudo conectar con el servidor: " + error.toString(), Toast.LENGTH_SHORT).show();

                    }
                }) {
            @Override
            public Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("ipcTipoR", "PAINANI");
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

    public void GenerarRechazo(Integer viRazonID, String vcRazon, Context vcContext){

        Log.e("Generando _Rechazon", viRazonID.toString());

        final ProgressDialog nDialog;
        nDialog = new ProgressDialog(vcContext);
        nDialog.setMessage("Cargando...");
        nDialog.setTitle("CAncelando Entrega");
        nDialog.setIndeterminate(false);

        opPausaPainani ObjopPausaP = new opPausaPainani();
        ObjopPausaP.setiPainani(globales.g_ctUsuario.getiPersona());
        ObjopPausaP.setiRazon(viRazonID);
        ObjopPausaP.setcTipoRazon(vcRazon);
        ObjopPausaP.setiPartida(0);
        ObjopPausaP.setDtPausa(null);
        ObjopPausaP.setcObs("RECHAZO PEDIDO: " + vcRazon);
        ObjopPausaP.setDtCreado(null);
        ObjopPausaP.setDtModifca(null);
        ObjopPausaP.setcUsuCrea(globales.g_ctUsuario.getcUsuario());
        ObjopPausaP.setcUsumodifca(globales.g_ctUsuario.getcUsuario());
        globales.opPausaPainaniList.add(ObjopPausaP);

        JSONObject jsonBody = new JSONObject();
        JSONObject jsonParams = new JSONObject();
        JSONObject jsonDataSet = new JSONObject();

        final Gson gson = new Gson();
        String JS_opPausaP = gson.toJson(
                globales.opPausaPainaniList,
                new TypeToken<ArrayList<opPausaPainani>>() {
                }.getType());
        try {
            JSONArray opPausaPainaniJS   = new JSONArray(JS_opPausaP);
            jsonDataSet.put("tt_opPausaPainani",  opPausaPainaniJS);
            jsonParams.put("ds_PausaP", jsonDataSet);
            jsonBody.put("request", jsonParams);

            Log.i("Response", jsonBody.toString());

        } catch (JSONException e) {
            e.printStackTrace();
            //nDialog.dismiss();
            Toast.makeText(vcContext, "Error: " + e.getMessage() , Toast.LENGTH_SHORT).show();
        }

        getmRequestQueue(vcContext);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.POST, url + "opPausaP/", jsonBody, new Response.Listener<JSONObject>() {
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
                                Toast.makeText(vcContext, "Error: " + Mensaje , Toast.LENGTH_SHORT).show();
                            } else {
                                Intent Home = new Intent(vcContext, MainActivity.class);
                                vcContext.startActivity(Home);

                            }

                        } catch (JSONException e) {
                            Log.i("Error JSONExcepcion", e.getMessage());

                            nDialog.dismiss();
                            Log.i("Error JSONExcepcion", e.getMessage());
                            Toast.makeText(vcContext, "Error Conversión de Datos. " +  e.getMessage() , Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        Log.i("Error", error.toString());
                        nDialog.dismiss();
                        Toast.makeText(vcContext, "Error Conversión de Datos. " + error.toString() , Toast.LENGTH_SHORT).show();

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