package com.sienrgitec.navegacionapp.modelos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class opPedidoDet_ implements Serializable {

    @SerializedName("cArticulo")
    @Expose
    private String cArticulo;

    @SerializedName("cDescripcion")
    @Expose
    private String cDescripcion;

    @SerializedName("cObs")
    @Expose
    private String cObs;

    @SerializedName("cTipoRazon")
    @Expose
    private String cTipoRazon;

    @SerializedName("cUsuCrea")
    @Expose
    private String cUsuCrea;

    @SerializedName("cUsuModificado")
    @Expose
    private String cUsuModificado;

    @SerializedName("dtCancela")
    @Expose
    private String dtCancela;

    @SerializedName("dtCreado")
    @Expose
    private String dtCreado;

    @SerializedName("dtFecha")
    @Expose
    private String dtFecha;

    @SerializedName("dtModificado")
    @Expose
    private String dtModificado;

    @SerializedName("deCantCancela")
    @Expose
    private Double deCantCancela;

    @SerializedName("deCantidad")
    @Expose
    private Double deCantidad;

    @SerializedName("dePrecioVta")
    @Expose
    private Double dePrecioVta;

    @SerializedName("deDescuento")
    @Expose
    private Double deDescuento;

    @SerializedName("deImporte")
    @Expose
    private Double deImporte;

    @SerializedName("deImpuesto")
    @Expose
    private Double deImpuesto;

    @SerializedName("dePorcDescto")
    @Expose
    private Double dePorcDescto;

    @SerializedName("dePorcImp")
    @Expose
    private Double dePorcImp;

    @SerializedName("dePrecio")
    @Expose
    private Double dePrecio;

    @SerializedName("dePrecioUnit")
    @Expose
    private Double dePrecioUnit;

    @SerializedName("iArticulo")
    @Expose
    private Integer iArticulo;

    @SerializedName("iPartida")
    @Expose
    private Integer iPartida;

    @SerializedName("iPedido")
    @Expose
    private Integer iPedido;

    @SerializedName("iPedidoProv")
    @Expose
    private Integer iPedidoProv;

    @SerializedName("iRazon")
    @Expose
    private Integer iRazon;


    public String getcArticulo() {
        return cArticulo;
    }

    public void setcArticulo(String cArticulo) {
        this.cArticulo = cArticulo;
    }

    public String getcDescripcion() {
        return cDescripcion;
    }

    public void setcDescripcion(String cDescripcion) {
        this.cDescripcion = cDescripcion;
    }

    public String getcObs() {
        return cObs;
    }

    public void setcObs(String cObs) {
        this.cObs = cObs;
    }

    public String getcTipoRazon() {
        return cTipoRazon;
    }

    public void setcTipoRazon(String cTipoRazon) {
        this.cTipoRazon = cTipoRazon;
    }

    public String getcUsuCrea() {
        return cUsuCrea;
    }

    public void setcUsuCrea(String cUsuCrea) {
        this.cUsuCrea = cUsuCrea;
    }

    public String getcUsuModificado() {
        return cUsuModificado;
    }

    public void setcUsuModificado(String cUsuModificado) {
        this.cUsuModificado = cUsuModificado;
    }

    public String getDtCancela() {
        return dtCancela;
    }

    public void setDtCancela(String dtCancela) {
        this.dtCancela = dtCancela;
    }

    public String getDtCreado() {
        return dtCreado;
    }

    public void setDtCreado(String dtCreado) {
        this.dtCreado = dtCreado;
    }

    public String getDtFecha() {
        return dtFecha;
    }

    public void setDtFecha(String dtFecha) {
        this.dtFecha = dtFecha;
    }

    public String getDtModificado() {
        return dtModificado;
    }

    public void setDtModificado(String dtModificado) {
        this.dtModificado = dtModificado;
    }

    public Double getDeCantCancela() {
        return deCantCancela;
    }

    public void setDeCantCancela(Double deCantCancela) {
        this.deCantCancela = deCantCancela;
    }

    public Double getDeCantidad() {
        return deCantidad;
    }

    public void setDeCantidad(Double deCantidad) {
        this.deCantidad = deCantidad;
    }

    public Double getDePrecioVta() {
        return dePrecioVta;
    }

    public void setDePrecioVta(Double dePrecioVta) {
        this.dePrecioVta = dePrecioVta;
    }

    public Double getDeDescuento() {
        return deDescuento;
    }

    public void setDeDescuento(Double deDescuento) {
        this.deDescuento = deDescuento;
    }

    public Double getDeImporte() {
        return deImporte;
    }

    public void setDeImporte(Double deImporte) {
        this.deImporte = deImporte;
    }

    public Double getDeImpuesto() {
        return deImpuesto;
    }

    public void setDeImpuesto(Double deImpuesto) {
        this.deImpuesto = deImpuesto;
    }

    public Double getDePorcDescto() {
        return dePorcDescto;
    }

    public void setDePorcDescto(Double dePorcDescto) {
        this.dePorcDescto = dePorcDescto;
    }

    public Double getDePorcImp() {
        return dePorcImp;
    }

    public void setDePorcImp(Double dePorcImp) {
        this.dePorcImp = dePorcImp;
    }

    public Double getDePrecio() {
        return dePrecio;
    }

    public void setDePrecio(Double dePrecio) {
        this.dePrecio = dePrecio;
    }

    public Double getDePrecioUnit() {
        return dePrecioUnit;
    }

    public void setDePrecioUnit(Double dePrecioUnit) {
        this.dePrecioUnit = dePrecioUnit;
    }

    public Integer getiArticulo() {
        return iArticulo;
    }

    public void setiArticulo(Integer iArticulo) {
        this.iArticulo = iArticulo;
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

    public Integer getiPedidoProv() {
        return iPedidoProv;
    }

    public void setiPedidoProv(Integer iPedidoProv) {
        this.iPedidoProv = iPedidoProv;
    }

    public Integer getiRazon() {
        return iRazon;
    }

    public void setiRazon(Integer iRazon) {
        this.iRazon = iRazon;
    }
}
