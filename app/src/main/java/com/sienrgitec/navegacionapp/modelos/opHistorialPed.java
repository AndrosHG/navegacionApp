package com.sienrgitec.navegacionapp.modelos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class opHistorialPed implements Serializable {

    @SerializedName("iPedido")
    @Expose
    private Integer iPedido;

    @SerializedName("iPainani")
    @Expose
    private Integer iPainani;

    @SerializedName("dtFecha")
    @Expose
    private String dtFecha;

    @SerializedName("cNegocio")
    @Expose
    private String cNegocio;

    @SerializedName("cDirProveedor")
    @Expose
    private String cDirProveedor;


    @SerializedName("cCliente")
    @Expose
    private String cCliente;

    @SerializedName("cDirCli")
    @Expose
    private String cDirCli;

    @SerializedName("deTotalPiezas")
    @Expose
    private String deTotalPiezas;

    public Integer getiPedido() {
        return iPedido;
    }

    public void setiPedido(Integer iPedido) {
        this.iPedido = iPedido;
    }

    public Integer getiPainani() {
        return iPainani;
    }

    public void setiPainani(Integer iPainani) {
        this.iPainani = iPainani;
    }

    public String getDtFecha() {
        return dtFecha;
    }

    public void setDtFecha(String dtFecha) {
        this.dtFecha = dtFecha;
    }

    public String getcNegocio() {
        return cNegocio;
    }

    public void setcNegocion(String cNegocion) {
        this.cNegocio = cNegocion;
    }

    public String getcDirProveedor() {
        return cDirProveedor;
    }

    public void setcDirProveedor(String cDirProveedor) {
        this.cDirProveedor = cDirProveedor;
    }

    public String getcCliente() {
        return cCliente;
    }

    public void setcCliente(String cCliente) {
        this.cCliente = cCliente;
    }

    public String getcDirCli() {
        return cDirCli;
    }

    public void setcDirCli(String cDirCli) {
        this.cDirCli = cDirCli;
    }

    public String getDeTotalPiezas() {
        return deTotalPiezas;
    }

    public void setDeTotalPiezas(String deTotalPiezas) {
        this.deTotalPiezas = deTotalPiezas;
    }
}
