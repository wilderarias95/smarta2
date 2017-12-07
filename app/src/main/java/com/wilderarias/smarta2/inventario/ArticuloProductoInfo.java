package com.wilderarias.smarta2.inventario;

/**
 * Created by USUARIO on 07/12/2017.
 */

public class ArticuloProductoInfo {
    long anoRegistroAP,cantidadDisponible,cantidadInicial,codigoAP,diaRegistroAP,idSucursal,
    idUsuario,mesRegistroAP,valorCapital,valorNeto;
    String codigoP;

    public ArticuloProductoInfo() {
    }

    public ArticuloProductoInfo(long anoRegistroAP, long cantidadDisponible, long cantidadInicial,
                                long codigoAP, long diaRegistroAP, long idSucursal, long idUsuario,
                                long mesRegistroAP, long valorCapital, long valorNeto, String codigoP) {
        this.anoRegistroAP = anoRegistroAP;
        this.cantidadDisponible = cantidadDisponible;
        this.cantidadInicial = cantidadInicial;
        this.codigoAP = codigoAP;
        this.diaRegistroAP = diaRegistroAP;
        this.idSucursal = idSucursal;
        this.idUsuario = idUsuario;
        this.mesRegistroAP = mesRegistroAP;
        this.valorCapital = valorCapital;
        this.valorNeto = valorNeto;
        this.codigoP = codigoP;
    }

    public long getAnoRegistroAP() {
        return anoRegistroAP;
    }

    public void setAnoRegistroAP(long anoRegistroAP) {
        this.anoRegistroAP = anoRegistroAP;
    }

    public long getCantidadDisponible() {
        return cantidadDisponible;
    }

    public void setCantidadDisponible(long cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }

    public long getCantidadInicial() {
        return cantidadInicial;
    }

    public void setCantidadInicial(long cantidadInicial) {
        this.cantidadInicial = cantidadInicial;
    }

    public long getCodigoAP() {
        return codigoAP;
    }

    public void setCodigoAP(long codigoAP) {
        this.codigoAP = codigoAP;
    }

    public long getDiaRegistroAP() {
        return diaRegistroAP;
    }

    public void setDiaRegistroAP(long diaRegistroAP) {
        this.diaRegistroAP = diaRegistroAP;
    }

    public long getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(long idSucursal) {
        this.idSucursal = idSucursal;
    }

    public long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public long getMesRegistroAP() {
        return mesRegistroAP;
    }

    public void setMesRegistroAP(long mesRegistroAP) {
        this.mesRegistroAP = mesRegistroAP;
    }

    public long getValorCapital() {
        return valorCapital;
    }

    public void setValorCapital(long valorCapital) {
        this.valorCapital = valorCapital;
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
}
