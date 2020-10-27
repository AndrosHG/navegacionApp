package com.sienrgitec.navegacionapp.configuracion;

import com.sienrgitec.navegacionapp.modelos.ctDomicilio;
import com.sienrgitec.navegacionapp.modelos.ctPainani;
import com.sienrgitec.navegacionapp.modelos.ctProveedor;
import com.sienrgitec.navegacionapp.modelos.ctUsuario;
import com.sienrgitec.navegacionapp.modelos.opPedPainaniDet;
import com.sienrgitec.navegacionapp.modelos.opPedidoDet;
import com.sienrgitec.navegacionapp.modelos.opUbicaPainani;

import java.util.ArrayList;
import java.util.List;

public class Globales {
    public static String vgcCompania  = "Painani";
    public static String URL          = "http://192.168.1.102:8083/painal/rest/painalService/";
    //public static  String  URL = "http://sinergitecdemo.ddns.net:8083/painal/rest/painalService/";

    public static Integer vgiSucursal = 1;

    public static Boolean vglEjecServ = true;

    public static ctUsuario g_ctUsuario = null;
    public static ctPainani g_ctPainani = null;
    public static ctProveedor g_ctProveedor = null;
    public static ctDomicilio g_ctDomicilio = null;


    public static List<ctUsuario> g_ctUsuarioList = null;
    public static List<ctPainani> g_ctPainaniList = null;
    public static List<ctProveedor> g_ctProvList  = null;
    public static List<ctDomicilio> g_ctDomList   = null;
    public static List<opPedidoDet> g_PedidoListD = null;
    public static List<opPedidoDet>  g_opPedidoDetList     = null;

    public static ArrayList<opUbicaPainani> opUbicacionList  = new ArrayList<>();
    public static List<opPedPainaniDet> g_ctPedPainaniDetList = null;

}
