package com.sienrgitec.navegacionapp.actividades;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

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
import com.sienrgitec.navegacionapp.adaptadores.ProveedorAdapter;
import com.sienrgitec.navegacionapp.adaptadores.ctEvaluacionAdapter;
import com.sienrgitec.navegacionapp.configuracion.Globales;
import com.sienrgitec.navegacionapp.modelos.ctEvaluacion;
import com.sienrgitec.navegacionapp.modelos.ctProveedor;
import com.sienrgitec.navegacionapp.modelos.ctProveedor_;
import com.sienrgitec.navegacionapp.modelos.opClienteEvalua;
import com.sienrgitec.navegacionapp.ui.home.HomeViewModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class opEvaluaciones extends AppCompatActivity {

    public Globales globales;
    private static RequestQueue mRequestQueue;
    private String url = globales.URL,  vcTipo , vcPersona, vcTipoPersona;
    private Integer viPersona, viPedido;

    private Button btnEntregaCli;
    private TextView tvEvaluado;
    public RecyclerView recycler;

    public static List<ctEvaluacion> listaEvaluacion    = null;
    public static ArrayList<ctEvaluacion> listfinEvalua = new ArrayList<>();
    public static ArrayList<opClienteEvalua> opClienteEvaluaList = new ArrayList<opClienteEvalua>();
    public static ArrayList<opClienteEvalua> opClienteEvaluaFinal = new ArrayList<opClienteEvalua>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_op_evaluaciones);



        Intent i = getIntent();
        vcTipo   = i.getStringExtra("ipcTipo");
        viPedido = i.getIntExtra("ipiPedido", 0);
        viPersona = i.getIntExtra("ipiPersona", 0);
        vcPersona =  i.getStringExtra("ipcPersona");
        vcTipoPersona = i.getStringExtra("ipcTipoPersona");



        Log.e("misparametros---> " , vcTipo + " " + viPedido + " " + viPersona + " " + vcPersona + " " + vcTipoPersona);



        btnEntregaCli = (Button)   findViewById(R.id.btnFinEvalua);
        tvEvaluado    = (TextView) findViewById(R.id.tvEvaluaA);

        tvEvaluado.setText("Evaluando a: " + vcPersona);

        recycler      = (RecyclerView) findViewById(R.id.lista);
        recycler.setLayoutManager(new LinearLayoutManager(opEvaluaciones.this,LinearLayoutManager.VERTICAL,false));

        btnEntregaCli.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FinalizarEvaluacion();
            }
        });

        CargactEvalua();
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

    public void MuestraMensaje(String vcTitulo, String vcMensaje){
        AlertDialog.Builder myBuild = new AlertDialog.Builder(opEvaluaciones.this);
        myBuild.setMessage(vcMensaje);
        myBuild.setTitle(Html.fromHtml("<font color ='#FF0000'>" + vcTitulo +"</font>"));
        myBuild.setPositiveButton("ACEPTAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();

            }
        });
        AlertDialog dialog = myBuild.create();
        dialog.show();
        return;
    }

    public void CargactEvalua(){
        getmRequestQueue();
        String urlParams = String.format(url + "ctEvaluacion?iplActivo=%1$s&ipcTipo=%2$s", true, vcTipo );
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
                            JSONObject ds_Evaluacion = respuesta.getJSONObject("tt_ctEvaluacion");

                            JSONArray tt_ctEvaluacion  = ds_Evaluacion.getJSONArray("tt_ctEvaluacion");

                            listaEvaluacion    = Arrays.asList(new Gson().fromJson(tt_ctEvaluacion.toString(), ctEvaluacion[].class));


                            if (Error == true) {
                                MuestraMensaje("Error", Mensaje);
                                return;

                            } else {
                                for(ctEvaluacion obj: listaEvaluacion){
                                    ctEvaluacion pasaLista = new ctEvaluacion();
                                    pasaLista.setiEvalua(obj.getiEvalua());
                                    pasaLista.setcEvalua(obj.getcEvalua());
                                    pasaLista.setiPunto(obj.getiPunto());
                                    listfinEvalua.add(pasaLista);
                                }

                                ctEvaluacionAdapter adapDet = new ctEvaluacionAdapter(opEvaluaciones.this,null);
                                adapDet.setList((List<ctEvaluacion>) listfinEvalua);
                                recycler.setAdapter(adapDet);

                            }
                        } catch (JSONException e) {
                            AlertDialog.Builder myBuild = new AlertDialog.Builder(opEvaluaciones.this);
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
                        // TODO: Handle error
                        Log.i("Error Respuesta", error.toString());
                        AlertDialog.Builder myBuild = new AlertDialog.Builder(opEvaluaciones.this);
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
                params.put("iplActivo", "true");
                params.put("ipcTipo", vcTipo);

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

    /*Va creando un arraylist de los puntos evaluados*/
    public void CreaParamEvalua(Float vdeEvalua, Integer viPunto, Integer viEvalua){




        opClienteEvalua objNvaEvaluacion = new opClienteEvalua();
        objNvaEvaluacion.setiPedido(viPedido);
        objNvaEvaluacion.setiPersona(viPersona);
        objNvaEvaluacion.setiPunto(viPunto);
        objNvaEvaluacion.setiEvalua(viEvalua);
        objNvaEvaluacion.setiTipoPersona(0);
        objNvaEvaluacion.setcTipo(vcTipo);
        objNvaEvaluacion.setcValor(vdeEvalua.toString() + "0");
        objNvaEvaluacion.setcObs("");
        objNvaEvaluacion.setcUsuCrea(globales.g_ctUsuario.getcUsuario());
        objNvaEvaluacion.setDtCreado("");
        objNvaEvaluacion.setcUsuModifica(globales.g_ctUsuario.getcUsuario());
        objNvaEvaluacion.setDtModificado(null);
        objNvaEvaluacion.setDtFecha(null);
        opClienteEvaluaList.add(objNvaEvaluacion);
    }

    /*Finaliza la Evaluación */
    public void FinalizarEvaluacion(){


        for(opClienteEvalua obj: opClienteEvaluaList){

            opClienteEvalua objNvaEvaluacion = new opClienteEvalua();
            objNvaEvaluacion.setiPedido(viPedido);
            objNvaEvaluacion.setiPersona(viPersona);
            objNvaEvaluacion.setiPunto(obj.iPunto);
            objNvaEvaluacion.setiEvalua(obj.getiEvalua());
            objNvaEvaluacion.setiTipoPersona(0);
            objNvaEvaluacion.setcTipo(vcTipo);
            objNvaEvaluacion.setcValor(obj.getcValor());
            objNvaEvaluacion.setcObs("");
            objNvaEvaluacion.setcUsuCrea(globales.g_ctUsuario.getcUsuario());
            objNvaEvaluacion.setDtCreado("");
            objNvaEvaluacion.setcUsuModifica(globales.g_ctUsuario.getcUsuario());
            objNvaEvaluacion.setDtModificado(null);
            objNvaEvaluacion.setDtFecha(null);
            opClienteEvaluaFinal.add(objNvaEvaluacion);


        }

        final ProgressDialog nDialog;
        nDialog = new ProgressDialog(getApplicationContext());
        nDialog.setMessage("Cargando...");
        nDialog.setTitle("Guarda Ubicaion");
        nDialog.setIndeterminate(false);

        JSONObject jsonBody = new JSONObject();
        JSONObject jsonParams = new JSONObject();
        JSONObject jsonDataSet = new JSONObject();

        final Gson gson = new Gson();
        String JS_opClienteEvalua = gson.toJson(
                opClienteEvaluaFinal,
                new TypeToken<ArrayList<opClienteEvalua>>() {
                }.getType());

        try {
            JSONArray opEvaluaJS   = new JSONArray(JS_opClienteEvalua);
            jsonDataSet.put("tt_opClienteEvalua",  opEvaluaJS);
            jsonParams.put("ds_NvaEvaluacion", jsonDataSet);
            jsonParams.put("ipcPersona", vcTipoPersona);
            jsonBody.put("request", jsonParams);

            Log.i("Response", jsonBody.toString());

        } catch (JSONException e) {
            e.printStackTrace();
            nDialog.dismiss();
            MuestraMensaje("Error", e.getMessage());
            //btnCreaRegistro.setEnabled(true);
        }

        getmRequestQueue();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.POST, url + "opClienteEvalua/", jsonBody, new Response.Listener<JSONObject>() {
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
                                MuestraMensaje("Error" , Mensaje);


                            } else {
                                MuestraMensaje("Aviso", "La evaluación fue exitosa");


                                /*if(vcPersona.equals("Proveedor")) {
                                    Intent Home = new Intent(EvaluaCli.this, Home.class);
                                    Home.putExtra("ipcEvaluado", "proveedor");
                                    startActivity(Home);
                                }else{
                                    Intent Home = new Intent(EvaluaCli.this, Home.class);
                                    Home.putExtra("ipcEvaluado", "cliente");
                                    startActivity(Home);
                                }*/

                                opClienteEvaluaList.clear();

                            }
                        } catch (JSONException e) {
                            Log.i("Error JSONExcepcion", e.getMessage());

                            nDialog.dismiss();
                            Log.i("Error JSONExcepcion", e.getMessage());
                            MuestraMensaje("Error", "Error Conversión de Datos." + "\n " + e.getMessage());
                            //btnCreaRegistro.setEnabled(true);
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        Log.i("Error", error.toString());
                        nDialog.dismiss();
                        MuestraMensaje("Error", error.toString());
                        //btnCreaRegistro.setEnabled(true);
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