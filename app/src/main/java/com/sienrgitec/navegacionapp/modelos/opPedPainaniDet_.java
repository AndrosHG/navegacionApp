package com.sienrgitec.navegacionapp.modelos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class opPedPainaniDet_ implements Serializable {

    @SerializedName("cDirProveedor")
    @Expose
    private String cDirProveedor;

    @SerializedName("cNegocion")
    @Expose
    private String cNegocion;

    @SerializedName("deImporte")
    @Expose
    private String deImporte;

    @SerializedName("deTotalPiezas")
    @Expose
    private String deTotalPiezas;

    @SerializedName("dtLlega")
    @Expose
    private String dtLlega;

    @SerializedName("dtSale")
    @Expose
    private String dtSale;

    @SerializedName("dtTTraslado")
    @Expose
    private String dtTTraslado;

    @SerializedName("iPainani")
    @Expose
    private Integer iPainani;

    @SerializedName("iPartida")
    @Expose
    private Integer iPartida;

    @SerializedName("iPedido")
    @Expose
    private Integer iPedido;

    @SerializedName("iProveedor")
    @Expose
    private Integer iProveedor;

    @SerializedName("iPedidoProv")
    @Expose
    private Integer iPedidoProv;

    public String getcDirProveedor() {
        return cDirProveedor;
    }

    public void setcDirProveedor(String cDirProveedor) {
        this.cDirProveedor = cDirProveedor;
    }

    public String getcNegocion() {
        return cNegocion;
    }

    public void setcNegocion(String cNegocion) {
        this.cNegocion = cNegocion;
    }

    public String getDeImporte() {
        return deImporte;
    }

    public void setDeImporte(String deImporte) {
        this.deImporte = deImporte;
    }

    public String getDeTotalPiezas() {
        return deTotalPiezas;
    }

    public void setDeTotalPiezas(String deTotalPiezas) {
        this.deTotalPiezas = deTotalPiezas;
    }

    public String getDtLlega() {
        return dtLlega;
    }

    public void setDtLlega(String dtLlega) {
        this.dtLlega = dtLlega;
    }

    public String getDtSale() {
        return dtSale;
    }

    public void setDtSale(String dtSale) {
        this.dtSale = dtSale;
    }

    public String getDtTTraslado() {
        return dtTTraslado;
    }

    public void setDtTTraslado(String dtTTraslado) {
        this.dtTTraslado = dtTTraslado;
    }

    public Integer getiPainani() {
        return iPainani;
    }

    public void setiPainani(Integer iPainani) {
        this.iPainani = iPainani;
    }

    public Integer getiPartida() {
        return iPartida;
    }

    public void setiPartida(Integer iPartida) {
        this.iPartida = iPartida;
    }

    public Integer getiPedido() {
        return iPedido;
    }

    public void setiPedido(Integer iPedido) {
        this.iPedido = iPedido;
    }

    public Integer getiProveedor() {
        return iProveedor;
    }

    public void setiProveedor(Integer iProveedor) {
        this.iProveedor = iProveedor;
    }

    public Integer getiPedidoProv() {
        return iPedidoProv;
    }

    public void setiPedidoProv(Integer iPedidoProv) {
        this.iPedidoProv = iPedidoProv;
    }
}
