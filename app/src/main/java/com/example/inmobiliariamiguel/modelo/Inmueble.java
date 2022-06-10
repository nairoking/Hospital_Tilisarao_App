package com.example.inmobiliariamiguel.modelo;

import java.io.Serializable;
import java.util.Objects;

public class Inmueble implements Serializable {

    private int idInmueble;
    private String direccion;
    private String uso;
    private String tipo;
    private int ambientes;
    private double precio;
    private Propietario duenio;
    //En falso significa que el innmueble no está disponible por alguna falla en el mismo.
    private String estado;
    private String imagen;
    private double latitud;
    private double longitud;

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public Inmueble(int idInmueble, String direccion, String uso, String tipo, int ambientes, double precio, Propietario propietario, String estado, String imagen, double latitud, double longitud) {
        this.idInmueble = idInmueble;
        this.direccion = direccion;
        this.uso = uso;
        this.tipo = tipo;
        this.ambientes = ambientes;
        this.precio = precio;
        this.duenio  = propietario;
        this.estado = estado;
        this.imagen = imagen;
        this.latitud = latitud;
        this.longitud = longitud;
    }
    public Inmueble() {

    }
    public int getIdInmueble() {
        return idInmueble;
    }

    public void setIdInmueble(int idInmueble) {
        this.idInmueble = idInmueble;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getUso() {
        return uso;
    }

    public void setUso(String uso) {
        this.uso = uso;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getAmbientes() {
        return ambientes;
    }

    public void setAmbientes(int ambientes) {
        this.ambientes = ambientes;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Propietario getPropietario() {
        return duenio;
    }

    public void setPropietario(Propietario propietario) {
        this.duenio = propietario;
    }

    public int isEstado() {
        if(estado == "si"){
            return 1;
        }
        else{
            return 0;
        }
    }
    public Boolean getBool( ){
        if(estado.equals("si") ){
            return true;
        }
        else{
            return false;
        }
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    public String getEstado(){return estado;}

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Inmueble inmueble = (Inmueble) o;
        return idInmueble == inmueble.idInmueble;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idInmueble);
    }
}
