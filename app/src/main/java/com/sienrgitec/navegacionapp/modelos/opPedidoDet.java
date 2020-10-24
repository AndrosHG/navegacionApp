package com.sienrgitec.navegacionapp.modelos;

import java.io.Serializable;

public class opPedidoDet implements Serializable {
    private Integer iFolioSusp;
    private String cArticulo;
    private String cDescripcion;
    private String cNumParte;
    private String cObservaciones;
    private Double deCantidad;
    private  Integer iPedido;
    private Integer iProveedor;
    private Integer iPartida;
    private  Integer iPedidoProv;

    public Integer getiPedidoProv() {
        return iPedidoProv;
    }

    public void setiPedidoProv(Integer iPedidoProv) {
        this.iPedidoProv = iPedidoProv;
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
