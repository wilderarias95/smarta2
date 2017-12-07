package com.wilderarias.smarta2.ruta;

/**
 * Created by USUARIO on 02/12/2017.
 */

public class ProductoData {
    private String codigoP,nombreP;
    private long codigoAP, cantidadDisponible,valorNeto,anoRegistroAP,cantidadInicial,diaRegistroAP
            ,idSucursal,idUsuario,mesRegistroAP,valorCapital;

    public ProductoData() {

    }

    public ProductoData(String codigoP, long codigoAP, long cantidadDisponible, long valorNeto
            , long anoRegistroAP, long cantidadInicial, long diaRegistroAP, long idSucursal
            , long idUsuario, long mesRegistroAP, long valorCapital) {
        this.codigoP = codigoP;
        this.codigoAP = codigoAP;
        this.cantidadDisponible = cantidadDisponible;
        this.valorNeto = valorNeto;
        this.anoRegistroAP = anoRegistroAP;
        this.cantidadInicial = cantidadInicial;
        this.diaRegistroAP = diaRegistroAP;
        this.idSucursal = idSucursal;
        this.idUsuario = idUsuario;
        this.mesRegistroAP = mesRegistroAP;
        this.valorCapital = valorCapital;
    }

    public String getCodigoP() {
        return codigoP;
    }

    public void setCodigoP(String codigoP) {
        this.codigoP = codigoP;
    }

    public String getNombreP() {
        return nombreP;
    }

    public void setNombreP(String nombreP) {
        this.nombreP = nombreP;
    }

    public long getCodigoAP() {
        return codigoAP;
    }

    public void setCodigoAP(long codigoAP) {
        this.codigoAP = codigoAP;
    }

    public long getCantidadDisponible() {
        return cantidadDisponible;
    }

    public void setCantidadDisponible(long cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }

    public long getValorNeto() {
        return valorNeto;
    }

    public void setValorNeto(long valorNeto) {
        this.valorNeto = valorNeto;
    }
}
