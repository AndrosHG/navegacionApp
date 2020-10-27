package com.sienrgitec.navegacionapp.modelos;

import java.io.Serializable;

public class opClienteEvalua implements Serializable {

    private String cObs;
    private String cTipo;
    private String cUsuCrea;
    private String cUsuModifica;
    private String cValor;
    private String dtCreado;
    private String dtFecha;
    private String dtModificado;
    private Integer iPersona;
    public Integer iTipoPersona;
    public Integer iPunto;
    private Integer iEvalua;
    private Integer iPedido;
    private Object id;

    public Integer getiPunto() {
        return iPunto;
    }

    public void setiPunto(Integer iPunto) {
        this.iPunto = iPunto;
    }

    public Integer getiPersona() {
        return iPersona;
    }

    public void setiPersona(Integer iPersona) {
        this.iPersona = iPersona;
    }

    public Integer getiTipoPersona() {
        return iTipoPersona;
    }

    public void setiTipoPersona(Integer iTipoPersona) {
        this.iTipoPersona = iTipoPersona;
    }

    public String getcObs() {
        return cObs;
    }

    public void setcObs(String cObs) {
        this.cObs = cObs;
    }

    public String getcTipo() {
        return cTipo;
    }

    public void setcTipo(String cTipo) {
        this.cTipo = cTipo;
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

    public String getcValor() {
        return cValor;
    }

    public void setcValor(String cValor) {
        this.cValor = cValor;
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



    public Integer getiEvalua() {
        return iEvalua;
    }

    public void setiEvalua(Integer iEvalua) {
        this.iEvalua = iEvalua;
    }

    public Integer getiPedido() {
        return iPedido;
    }

    public void setiPedido(Integer iPedido) {
        this.iPedido = iPedido;
    }

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }



}
