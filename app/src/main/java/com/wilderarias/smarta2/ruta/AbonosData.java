package com.wilderarias.smarta2.ruta;

/**
 * Created by WilderArias on 11/15/2017.
 */

public class AbonosData {
    private long anoRegistroAC,diaRegistroAC,idFacturaVenta,idSucursal,mesRegistroAC,numeroA,valorAbono;

    public AbonosData() {
    }

    public AbonosData(long anoRegistroAC, long diaRegistroAC, long idFacturaVenta, long idSucursal, long mesRegistroAC, long numeroA, long valorAbono) {
        this.anoRegistroAC = anoRegistroAC;
        this.diaRegistroAC = diaRegistroAC;
        this.idFacturaVenta = idFacturaVenta;
        this.idSucursal = idSucursal;
        this.mesRegistroAC = mesRegistroAC;
        this.numeroA = numeroA;
        this.valorAbono = valorAbono;
    }

    public long getAnoRegistroAC() {
        return anoRegistroAC;
    }

    public void setAnoRegistroAC(long anoRegistroAC) {
        this.anoRegistroAC = anoRegistroAC;
    }

    public long getDiaRegistroAC() {
        return diaRegistroAC;
    }

    public void setDiaRegistroAC(long diaRegistroAC) {
        this.diaRegistroAC = diaRegistroAC;
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

    public long getMesRegistroAC() {
        return mesRegistroAC;
    }

    public void setMesRegistroAC(long mesRegistroAC) {
        this.mesRegistroAC = mesRegistroAC;
    }

    public long getNumeroA() {
        return numeroA;
    }

    public void setNumeroA(long numeroA) {
        this.numeroA = numeroA;
    }

    public long getValorAbono() {
        return valorAbono;
    }

    public void setValorAbono(long valorAbono) {
        this.valorAbono = valorAbono;
    }
}
