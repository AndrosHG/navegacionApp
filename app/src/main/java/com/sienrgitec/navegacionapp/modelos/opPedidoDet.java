package com.sienrgitec.navegacionapp.modelos;

import java.io.Serializable;

public class opPedidoDet implements Serializable {
    private Integer iFolioSusp;
    private String cArticulo;
    private String cDescripcion;
    private String cNumParte;
    private String cObservaciones;
    private Double deCantidad;


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

    public String getcNumParte() {
        return cNumParte;
    }

    public void setcNumParte(String cNumParte) {
        this.cNumParte = cNumParte;
    }

    public String getcObservaciones() {
        return cObservaciones;
    }

    public void setcObservaciones(String cObservaciones) {
        this.cObservaciones = cObservaciones;
    }

    public Double getDeCantidad() {
        return deCantidad;
    }

    public void setDeCantidad(Double deCantidad) {
        this.deCantidad = deCantidad;
    }

    public Integer getiFolioSusp() {
        return iFolioSusp;
    }

    public void setiFolioSusp(Integer iFolioSusp) {
        this.iFolioSusp = iFolioSusp;
    }
}
