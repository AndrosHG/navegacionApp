package com.sienrgitec.navegacionapp.modelos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class opPedPainani_ implements Serializable {

    @SerializedName("cCvePedido")
    @Expose
    private String cCvePedido;

    @SerializedName("cDirCliente")
    @Expose
    private String cDirCliente;

    @SerializedName("cCliente")
    @Expose
    private String cCliente;

    @SerializedName("dtAvisado")
    @Expose
    private String dtAvisado;

    @SerializedName("dtContestado")
    @Expose
    private String dtContestado;

    @SerializedName("dtFecha")
    @Expose
    private String dtFecha;

    @SerializedName("dtReasignado")
    @Expose
    private String dtReasignado;

    @SerializedName("iAsignadoA")
    @Expose
    private Integer iAsignadoA;

    @SerializedName("iCliente")
    @Expose
    private Integer iCliente;

    @SerializedName("iPainani")
    @Expose
    private Integer iPainani;

    @SerializedName("iPedido")
    @Expose
    private Integer iPedido;

    @SerializedName("lAceptado")
    @Expose
    private Boolean lAceptado;

    @SerializedName("lContestado")
    @Expose
    private Boolean lContestado;

    @SerializedName("lReasignado")
    @Expose
    private Boolean lReasignado;


    public String getcCvePedido() {
        return cCvePedido;
    }

    public void setcCvePedido(String cCvePedido) {
        this.cCvePedido = cCvePedido;
    }

    public String getcDirCliente() {
        return cDirCliente;
    }

    public void setcDirCliente(String cDirCliente) {
        this.cDirCliente = cDirCliente;
    }

    public String getcCliente() {
        return cCliente;
    }

    public void setcCliente(String cCliente) {
        this.cCliente = cCliente;
    }

    public String getDtAvisado() {
        return dtAvisado;
    }

    public void setDtAvisado(String dtAvisado) {
        this.dtAvisado = dtAvisado;
    }

    public String getDtContestado() {
        return dtContestado;
    }

    public void setDtContestado(String dtContestado) {
        this.dtContestado = dtContestado;
    }

    public String getDtFecha() {
        return dtFecha;
    }

    public void setDtFecha(String dtFecha) {
        this.dtFecha = dtFecha;
    }

    public String getDtReasignado() {
        return dtReasignado;
    }

    public void setDtReasignado(String dtReasignado) {
        this.dtReasignado = dtReasignado;
    }

    public Integer getiAsignadoA() {
        return iAsignadoA;
    }

    public void setiAsignadoA(Integer iAsignadoA) {
        this.iAsignadoA = iAsignadoA;
    }

    public Integer getiCliente() {
        return iCliente;
    }

    public void setiCliente(Integer iCliente) {
        this.iCliente = iCliente;
    }

    public Integer getiPainani() {
        return iPainani;
    }

    public void setiPainani(Integer iPainani) {
        this.iPainani = iPainani;
    }

    public Integer getiPedido() {
        return iPedido;
    }

    public void setiPedido(Integer iPedido) {
        this.iPedido = iPedido;
    }

    public Boolean getlAceptado() {
        return lAceptado;
    }

    public void setlAceptado(Boolean lAceptado) {
        this.lAceptado = lAceptado;
    }

    public Boolean getlContestado() {
        return lContestado;
    }

    public void setlContestado(Boolean lContestado) {
        this.lContestado = lContestado;
    }

    public Boolean getlReasignado() {
        return lReasignado;
    }

    public void setlReasignado(Boolean lReasignado) {
        this.lReasignado = lReasignado;
    }
}
