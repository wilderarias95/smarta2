package com.wilderarias.smarta2.inventario;

/**
 * Created by USUARIO on 07/12/2017.
 */

public class ProductoInfo {
    long anoRegistroP,diaRegistroP,idSucursal,mesRegistroP;
    String codigoP, descripcionP,nombreP;

    public ProductoInfo() {
    }

    public ProductoInfo(long anoRegistroP, long diaRegistroP, long idSucursal, long mesRegistroP, String codigoP, String descripcionP, String nombreP) {

        this.anoRegistroP = anoRegistroP;
        this.diaRegistroP = diaRegistroP;
        this.idSucursal = idSucursal;
        this.mesRegistroP = mesRegistroP;
        this.codigoP = codigoP;
        this.descripcionP = descripcionP;
        this.nombreP = nombreP;
    }

    public long getAnoRegistroP() {
        return anoRegistroP;
    }

    public void setAnoRegistroP(long anoRegistroP) {
        this.anoRegistroP = anoRegistroP;
    }

    public long getDiaRegistroP() {
        return diaRegistroP;
    }

    public void setDiaRegistroP(long diaRegistroP) {
        this.diaRegistroP = diaRegistroP;
    }

    public long getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(long idSucursal) {
        this.idSucursal = idSucursal;
    }

    public long getMesRegistroP() {
        return mesRegistroP;
    }

    public void setMesRegistroP(long mesRegistroP) {
        this.mesRegistroP = mesRegistroP;
    }

    public String getCodigoP() {
        return codigoP;
    }

    public void setCodigoP(String codigoP) {
        this.codigoP = codigoP;
    }

    public String getDescripcionP() {
        return descripcionP;
    }

    public void setDescripcionP(String descripcionP) {
        this.descripcionP = descripcionP;
    }

    public String getNombreP() {
        return nombreP;
    }

    public void setNombreP(String nombreP) {
        this.nombreP = nombreP;
    }
}
