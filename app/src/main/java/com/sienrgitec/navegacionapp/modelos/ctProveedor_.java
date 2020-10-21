package com.sienrgitec.navegacionapp.modelos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ctProveedor_ implements Serializable {

    @SerializedName("iProveedor")
    @Expose
    private Integer iProveedor;

    @SerializedName("iGiro")
    @Expose
    private Integer iGiro;

    @SerializedName("iSubGiro")
    @Expose
    private Integer iSubGiro;


    @SerializedName("iUnidad")
    @Expose
    private Integer iUnidad;

    @SerializedName("iNivelClasifica")
    @Expose
    private Integer iNivelClasifica;

    @SerializedName("cClaveProv")
    @Expose
    private String cClaveProv;

    @SerializedName("cRazonS")
    @Expose
    private String cRazonS;

    @SerializedName("cNegocio")
    @Expose
    private String cNegocio;

    @SerializedName("cRFC")
    @Expose
    private String cRFC;

    @SerializedName("cWhatsApp")
    @Expose
    private String cWhatsApp;

    @SerializedName("cPaginaWeb")
    @Expose
    private String cPaginaWeb;

    @SerializedName("cEMail")
    @Expose
    private String cEMail;

    @SerializedName("cTwitter")
    @Expose
    private String cTwitter;

    @SerializedName("cFacebook")
    @Expose
    private String cFacebook;

    @SerializedName("cSistema")
    @Expose
    private String cSistema;

    @SerializedName("cObs")
    @Expose
    private String cObs;

    @SerializedName("cUsuCrea")
    @Expose
    private String cUsuCrea;

    @SerializedName("cUsuModifica")
    @Expose
    private String cUsuModifica;

    @SerializedName("dtAfiliacion")
    @Expose
    private String dtAfiliacion;

    @SerializedName("dtModificado")
    @Expose
    private String dtModificado;


    @SerializedName("dtCreado")
    @Expose
    private String dtCreado;


    @SerializedName("lSucursales")
    @Expose
    private Boolean lSucursales;

    @SerializedName("lSistema")
    @Expose
    private Boolean lSistema;

    @SerializedName("lPideOtrasUnidades")
    @Expose
    private Boolean lPideOtrasUnidades;

    @SerializedName("deLat")
    @Expose
    private Double deLat;

    @SerializedName("deLon")
    @Expose
    private Double deLon;


    public Double getDeLat() {
        return deLat;
    }

    public void setDeLat(Double deLat) {
        this.deLat = deLat;
    }

    public Double getDeLon() {
        return deLon;
    }

    public void setDeLon(Double deLon) {
        this.deLon = deLon;
    }

    public Integer getiProveedor() {
        return iProveedor;
    }

    public void setiProveedor(Integer iProveedor) {
        this.iProveedor = iProveedor;
    }

    public Integer getiGiro() {
        return iGiro;
    }

    public void setiGiro(Integer iGiro) {
        this.iGiro = iGiro;
    }

    public Integer getiSubGiro() {
        return iSubGiro;
    }

    public void setiSubGiro(Integer iSubGiro) {
        this.iSubGiro = iSubGiro;
    }

    public Integer getiUnidad() {
        return iUnidad;
    }

    public void setiUnidad(Integer iUnidad) {
        this.iUnidad = iUnidad;
    }

    public Integer getiNivelClasifica() {
        return iNivelClasifica;
    }

    public void setiNivelClasifica(Integer iNivelClasifica) {
        this.iNivelClasifica = iNivelClasifica;
    }

    public String getcClaveProv() {
        return cClaveProv;
    }

    public void setcClaveProv(String cClaveProv) {
        this.cClaveProv = cClaveProv;
    }

    public String getcRazonS() {
        return cRazonS;
    }

    public void setcRazonS(String cRazonS) {
        this.cRazonS = cRazonS;
    }

    public String getcNegocio() {
        return cNegocio;
    }

    public void setcNegocio(String cNegocio) {
        this.cNegocio = cNegocio;
    }

    public String getcRFC() {
        return cRFC;
    }

    public void setcRFC(String cRFC) {
        this.cRFC = cRFC;
    }

    public String getcWhatsApp() {
        return cWhatsApp;
    }

    public void setcWhatsApp(String cWhatsApp) {
        this.cWhatsApp = cWhatsApp;
    }

    public String getcPaginaWeb() {
        return cPaginaWeb;
    }

    public void setcPaginaWeb(String cPaginaWeb) {
        this.cPaginaWeb = cPaginaWeb;
    }

    public String getcEMail() {
        return cEMail;
    }

    public void setcEMail(String cEMail) {
        this.cEMail = cEMail;
    }

    public String getcTwitter() {
        return cTwitter;
    }

    public void setcTwitter(String cTwitter) {
        this.cTwitter = cTwitter;
    }

    public String getcFacebook() {
        return cFacebook;
    }

    public void setcFacebook(String cFacebook) {
        this.cFacebook = cFacebook;
    }

    public String getcSistema() {
        return cSistema;
    }

    public void setcSistema(String cSistema) {
        this.cSistema = cSistema;
    }

    public String getcObs() {
        return cObs;
    }

    public void setcObs(String cObs) {
        this.cObs = cObs;
    }

    public String getcUsuCrea() {
        return cUsuCrea;
    }

    public void setcUsuCrea(String cUsuCrea) {
        this.cUsuCrea = cUsuCrea;
    }

    public String getcUsuModifica() {
        return cUsuModifica;
    }

    public void setcUsuModifica(String cUsuModifica) {
        this.cUsuModifica = cUsuModifica;
    }

    public String getDtAfiliacion() {
        return dtAfiliacion;
    }

    public void setDtAfiliacion(String dtAfiliacion) {
        this.dtAfiliacion = dtAfiliacion;
    }

    public String getDtModificado() {
        return dtModificado;
    }

    public void setDtModificado(String dtModificado) {
        this.dtModificado = dtModificado;
    }

    public String getDtCreado() {
        return dtCreado;
    }

    public void setDtCreado(String dtCreado) {
        this.dtCreado = dtCreado;
    }

    public Boolean getlSucursales() {
        return lSucursales;
    }

    public void setlSucursales(Boolean lSucursales) {
        this.lSucursales = lSucursales;
    }

    public Boolean getlSistema() {
        return lSistema;
    }

    public void setlSistema(Boolean lSistema) {
        this.lSistema = lSistema;
    }

    public Boolean getlPideOtrasUnidades() {
        return lPideOtrasUnidades;
    }

    public void setlPideOtrasUnidades(Boolean lPideOtrasUnidades) {
        this.lPideOtrasUnidades = lPideOtrasUnidades;
    }
}
