package com.wilderarias.smarta2.ruta;

/**
 * Created by USUARIO on 05/12/2017.
 */

public class ProductosVenta {
    int conf,valorNeto;//1:lista,0:borrado
    long cantidadP,codigoAP,idFacturaVenta,idSucursal,numeroP;
    String codigoP;

    public ProductosVenta(long cantidadP, long codigoAP, long idFacturaVenta, long idSucursal, String codigoP,int conf,int valorNeto) {
        this.cantidadP = cantidadP;
        this.codigoAP = codigoAP;
        this.idFacturaVenta = idFacturaVenta;
        this.idSucursal = idSucursal;
        this.codigoP = codigoP;
        this.conf=conf;
        this.valorNeto=valorNeto;
    }

    public int getValorNeto() {
        return valorNeto;
    }

    public void setValorNeto(int valorNeto) {
        this.valorNeto = valorNeto;
    }

    public int getConf() {
        return conf;
    }

    public void setConf(int conf) {
        this.conf = conf;
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

    public String getCodigoP() {
        return codigoP;
    }

    public void setCodigoP(String codigoP) {
        this.codigoP = codigoP;
    }
}
