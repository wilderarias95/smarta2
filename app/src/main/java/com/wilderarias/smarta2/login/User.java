package com.wilderarias.smarta2.login;

/**
 * Created by WilderArias on 10/30/2017.
 */

public class User {
    private String nombreU,apellidoU, contraseña;
    private String idSucursal,idUsuario;

    public User() {
    }

    public User(String nombreU, String apellidoU,String contraseña, String idUsuario, String idSucursal) {
        this.nombreU=nombreU;
        this.contraseña=contraseña;
        this.apellidoU=apellidoU;
        this.idUsuario=idUsuario;
        this.idSucursal=idSucursal;
    }

    public void setNombreU(String nombreU) {
        this.nombreU = nombreU;
    }

    public void setApellidoU(String apellidoU) {
        this.apellidoU = apellidoU;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public void setIdSucursal(String idSucursal) {
        this.idSucursal = idSucursal;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreU() {

        return nombreU;
    }

    public String getApellidoU() {
        return apellidoU;
    }

    public String getContraseña() {
        return contraseña;
    }

    public String getIdSucursal() {
        return idSucursal;
    }

    public String getIdUsuario() {
        return idUsuario;
    }
}
