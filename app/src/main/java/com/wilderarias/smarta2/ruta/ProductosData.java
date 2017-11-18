package com.wilderarias.smarta2.ruta;

/**
 * Created by WilderArias on 11/15/2017.
 */

public class ProductosData {
    private String codigoP,nombreP;
    private long cantidadP,codigoAP,idFacturaVenta,idSucursal,numeroP,valorNeto;

    public ProductosData() {
    }

    public ProductosData(String codigoP, long cantidadP, long codigoAP, long idFacturaVenta, long idSucursal, long numeroP) {
        this.codigoP = codigoP;
        this.cantidadP = cantidadP;
        this.codigoAP = codigoAP;
        this.idFacturaVenta = idFacturaVenta;
        this.idSucursal = idSucursal;
        this.numeroP = numeroP;
    }

    public String getNombreP() {
        return nombreP;
    }

    public void setNombreP(String nombreP) {
        this.nombreP = nombreP;
    }

    public long getValorNeto() {
        return valorNeto;
    }

    public void setValorNeto(long valorNeto) {
        this.valorNeto = valorNeto;
    }

    public String getCodigoP() {
        return codigoP;
    }

    public void setCodigoP(String codigoP) {
        this.codigoP = codigoP;
    }

    public long getCantidadP() {
        return cantidadP;
    }

    public void setCantidadP(long cantidadP) {
        this.cantidadP = cantidadP;
    }

    public long getCodigoAP() {
        return codigoAP;
    }

    public void setCodigoAP(long codigoAP) {
        this.codigoAP = codigoAP;
    }

    public long getIdFacturaVenta() {
        return idFacturaVenta;
    }

    public void setIdFacturaVenta(long idFacturaVenta) {
        this.idFacturaVenta = idFacturaVenta;
    }

    public long getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(long idSucursal) {
        this.idSucursal = idSucursal;
    }

    public long getNumeroP() {
        return numeroP;
    }

    public void setNumeroP(long numeroP) {
        this.numeroP = numeroP;
    }
}
