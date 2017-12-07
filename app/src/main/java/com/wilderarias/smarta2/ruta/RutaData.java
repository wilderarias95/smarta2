package com.wilderarias.smarta2.ruta;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.stream.Stream;

/**
 * Created by WilderArias on 10/29/2017.
 */

public class RutaData{
   private String estadoVenta,idCliente,nombreC,apellidoC,comentarioC,direccionC;
   private long descuento,diasCredito,idFacturaVenta,idSucursal,anoRegistroV,diaRegistroV,
           mesRegistroV,idUsuario,pos,saldoCredito,valorVenta;
   private long telefonoC;

    public RutaData(){

    }

    public RutaData(long descuento,long diasCredito, String estadoVenta,
                    String  idCliente,long idFacturaVenta,long idSucursal,long idUsuario,long pos,
                    long saldoCredito,long valorVenta,long anoRegistroV,long mesRegistroV,long diaRegistroV) {

        this.anoRegistroV=anoRegistroV;
        this.mesRegistroV=mesRegistroV;
        this.diaRegistroV=diaRegistroV;
        this.descuento=descuento;
        this.diasCredito=diasCredito;
        this.estadoVenta=estadoVenta;
        this.idCliente=idCliente;
        this.idFacturaVenta=idFacturaVenta;
        this.idSucursal=idSucursal;
        this.idUsuario=idUsuario;
        this.pos=pos;
        this.saldoCredito=saldoCredito;
        this.valorVenta=valorVenta;
    }

    public void setDireccionC(String direccionC) {
        this.direccionC = direccionC;
    }

    public void setTelefonoC(long telefonoC) {
        this.telefonoC = telefonoC;
    }

    public void setComentarioC(String comentarioC) {
        this.comentarioC = comentarioC;
    }

    public void setAnoRegistroV(long anoRegistroV) {
        this.anoRegistroV = anoRegistroV;
    }

    public void setDiaRegistroV(long diaRegistroV) {
        this.diaRegistroV = diaRegistroV;
    }

    public void setMesRegistroV(long mesRegistroV) {
        this.mesRegistroV = mesRegistroV;
    }

    public void setNombreC(String nombreC) {
        this.nombreC = nombreC;
    }

    public void setApellidoC(String apellidoC) {
        this.apellidoC = apellidoC;
    }

    public void setEstadoVenta(String estadoVenta) {
        this.estadoVenta = estadoVenta;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public void setDescuento(long descuento) {
        this.descuento = descuento;
    }

    public void setDiasCredito(long diasCredito) {
        this.diasCredito = diasCredito;
    }

    public void setIdFacturaVenta(long idFacturaVenta) {
        this.idFacturaVenta = idFacturaVenta;
    }

    public void setIdSucursal(long idSucursal) {
        this.idSucursal = idSucursal;
    }

    public void setIdUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setPos(long pos) {
        this.pos = pos;
    }

    public void setSaldoCredito(long saldoCredito) {
        this.saldoCredito = saldoCredito;
    }

    public void setValorVenta(long valorVenta) {
        this.valorVenta = valorVenta;
    }

    public String getDireccionC() {
        return direccionC;
    }

    public long getTelefonoC() {
        return telefonoC;
    }

    public String getComentarioC() {

        return comentarioC;
    }
    public long getAnoRegistroV() {
        return anoRegistroV;
    }

    public long getDiaRegistroV() {
        return diaRegistroV;
    }

    public long getMesRegistroV() {
        return mesRegistroV;
    }

    public String getNombreC() {return nombreC;}

    public String getApellidoC() {
        return apellidoC;
    }

    public String getEstadoVenta() {return estadoVenta;}

    public String getIdCliente() {
        return idCliente;
    }

    public long getDescuento() {
        return descuento;
    }

    public long getDiasCredito() {
        return diasCredito;
    }

    public long getIdFacturaVenta() {
        return idFacturaVenta;
    }

    public long getIdSucursal() {
        return idSucursal;
    }

    public long getIdUsuario() {
        return idUsuario;
    }

    public long getPos() {
        return pos;
    }

    public long getSaldoCredito() {
        return saldoCredito;
    }

    public long getValorVenta() {
        return valorVenta;
    }
}
