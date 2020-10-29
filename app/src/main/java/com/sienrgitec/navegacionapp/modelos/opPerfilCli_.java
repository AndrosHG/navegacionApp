package com.sienrgitec.navegacionapp.modelos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class opPerfilCli_ implements Serializable {
    @SerializedName("iCliente")
    @Expose
    private Integer iCliente;

    @SerializedName("cCliente")
    @Expose
    private String cCliente;

    @SerializedName("cDomicilio")
    @Expose
    private String cDomicilio;

    @SerializedName("iTotPed")
    @Expose
    private Integer iTotPed;

    @SerializedName("deEvalua")
    @Expose
    private Double deEvalua;


    public Integer getiCliente() {
        return iCliente;
    }

    public void setiCliente(Integer iCliente) {
        this.iCliente = iCliente;
    }

    public String getcCliente() {
        return cCliente;
    }

    public void setcCliente(String cCliente) {
        this.cCliente = cCliente;
    }

    public String getcDomicilio() {
        return cDomicilio;
    }

    public void setcDomicilio(String cDomicilio) {
        this.cDomicilio = cDomicilio;
    }

    public Integer getiTotPed() {
        return iTotPed;
    }

    public void setiTotPed(Integer iTotPed) {
        this.iTotPed = iTotPed;
    }

    public Double getDeEvalua() {
        return deEvalua;
    }

    public void setDeEvalua(Double deEvalua) {
        this.deEvalua = deEvalua;
    }
}
