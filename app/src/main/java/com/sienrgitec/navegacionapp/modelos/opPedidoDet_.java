package com.sienrgitec.navegacionapp.modelos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class opPedidoDet_ implements Serializable {

    @SerializedName("iFolioSusp")
    @Expose
    private Integer iFolioSusp;

    @SerializedName("iProveedor")
    @Expose
    private Integer iProveedor;


    public Integer getiFolioSusp() {
        return iFolioSusp;
    }

    public void setiFolioSusp(Integer iFolioSusp) {
        this.iFolioSusp = iFolioSusp;
    }
}
