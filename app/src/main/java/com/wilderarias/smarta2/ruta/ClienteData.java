package com.wilderarias.smarta2.ruta;

/**
 * Created by USUARIO on 06/12/2017.
 */

public class ClienteData {
    private String idCliente,nombreC,apellidoC,comentarioC,direccionC,tipoIdentificacion;
    private long idSucursal,anoRegistroC,diaRegistroC, mesRegistroC,idUsuario,telefonoC;

    public ClienteData() {
    }

    public ClienteData(String idCliente, String nombreC, String apellidoC, String comentarioC,
                       String direccionC, String tipoIdentificacion, long idSucursal,
                       long anoRegistroC, long diaRegistroC, long mesRegistroC, long idUsuario,
                       long telefonoC) {
        this.idCliente = idCliente;
        this.nombreC = nombreC;
        this.apellidoC = apellidoC;
        this.comentarioC = comentarioC;
        this.direccionC = direccionC;
        this.tipoIdentificacion = tipoIdentificacion;
        this.idSucursal = idSucursal;
        this.anoRegistroC = anoRegistroC;
        this.diaRegistroC = diaRegistroC;
        this.mesRegistroC = mesRegistroC;
        this.idUsuario = idUsuario;
        this.telefonoC = telefonoC;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombreC() {
        return nombreC;
    }

    public void setNombreC(String nombreC) {
        this.nombreC = nombreC;
    }

    public String getApellidoC() {
        return apellidoC;
    }

    public void setApellidoC(String apellidoC) {
        this.apellidoC = apellidoC;
    }

    public String getComentarioC() {
        return comentarioC;
    }

    public void setComentarioC(String comentarioC) {
        this.comentarioC = comentarioC;
    }

    public String getDireccionC() {
        return direccionC;
    }

    public void setDireccionC(String direccionC) {
        this.direccionC = direccionC;
    }

    public String getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(String tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    public long getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(long idSucursal) {
        this.idSucursal = idSucursal;
    }

    public long getAnoRegistroC() {
        return anoRegistroC;
    }

    public void setAnoRegistroC(long anoRegistroC) {
        this.anoRegistroC = anoRegistroC;
    }

    public long getDiaRegistroC() {
        return diaRegistroC;
    }

    public void setDiaRegistroC(long diaRegistroC) {
        this.diaRegistroC = diaRegistroC;
    }

    public long getMesRegistroC() {
        return mesRegistroC;
    }

    public void setMesRegistroC(long mesRegistroC) {
        this.mesRegistroC = mesRegistroC;
    }

    public long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public long getTelefonoC() {
        return telefonoC;
    }

    public void setTelefonoC(long telefonoC) {
        this.telefonoC = telefonoC;
    }
}
