package com.wilderarias.smarta2.inventario;

/**
 * Created by WilderArias on 10/29/2017.
 */

public class InventarioData {
    private long anoRegistroAP, cantidadDisponible,cantidadInicial,codigoAP,diaRegistroAP
            ,idSucursal,idUsuario,mesRegistroAP,valorCapital,valorNeto,pos;
    private String codigoP,nombreP,descripcionP;

    public InventarioData(long anoRegistroAP, long cantidadDisponible, long cantidadInicial, long codigoAP, long diaRegistroAP, long idSucursal, long idUsuario, long mesRegistroAP, long valorCapital, long valorNeto, String codigoP) {
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

    public InventarioData() {
    }

    //Getter


    public long getPos() {
        return pos;
    }

    public String getNombreP() {
        return nombreP;
    }

    public String getDescripcionP() {
        return descripcionP;
    }

    public long getAnoRegistroAP() {
        return anoRegistroAP;
    }

    public long getCantidadDisponible() {
        return cantidadDisponible;
    }

    public long getCantidadInicial() {
        return cantidadInicial;
    }

    public long getCodigoAP() {
        return codigoAP;
    }

    public long getDiaRegistroAP() {
        return diaRegistroAP;
    }

    public long getIdSucursal() {
        return idSucursal;
    }

    public long getIdUsuario() {
        return idUsuario;
    }

    public long getMesRegistroAP() {
        return mesRegistroAP;
    }

    public long getValorCapital() {
        return valorCapital;
    }

    public long getValorNeto() {
        return valorNeto;
    }

    public String getCodigoP() {
        return codigoP;
    }

    //Setter


    public void setPos(long pos) {
        this.pos = pos;
    }

    public void setNombreP(String nombreP) {
        this.nombreP = nombreP;
    }

    public void setDescripcionP(String descripcionP) {
        this.descripcionP = descripcionP;
    }

    public void setAnoRegistroAP(long anoRegistroAP) {
        this.anoRegistroAP = anoRegistroAP;
    }

    public void setCantidadDisponible(long cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }

    public void setCantidadInicial(long cantidadInicial) {
        this.cantidadInicial = cantidadInicial;
    }

    public void setCodigoAP(long codigoAP) {
        this.codigoAP = codigoAP;
    }

    public void setDiaRegistroAP(long diaRegistroAP) {
        this.diaRegistroAP = diaRegistroAP;
    }

    public void setIdSucursal(long idSucursal) {
        this.idSucursal = idSucursal;
    }

    public void setIdUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setMesRegistroAP(long mesRegistroAP) {
        this.mesRegistroAP = mesRegistroAP;
    }

    public void setValorCapital(long valorCapital) {
        this.valorCapital = valorCapital;
    }

    public void setValorNeto(long valorNeto) {
        this.valorNeto = valorNeto;
    }

    public void setCodigoP(String codigoP) {
        this.codigoP = codigoP;
    }
}
