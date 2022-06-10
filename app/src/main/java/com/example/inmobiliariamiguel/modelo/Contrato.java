package com.example.inmobiliariamiguel.modelo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

public class Contrato implements Serializable {

    private int idContrato;
    private String fechaDesde;
    private String fechaHasta;
    private double precio;
    private Inquilino inquilino;
    private Inmueble inmueble;

    public Contrato() {}
    public Contrato(int idContrato, String fechaInicio, String fechaFin, double montoAlquiler, Inquilino inquilino, Inmueble inmueble) {
        this.idContrato = idContrato;
        this.fechaDesde = fechaInicio;
        this.fechaHasta = fechaFin;
        this.precio = montoAlquiler;
        this.inquilino = inquilino;
        this.inmueble = inmueble;
    }

    public int getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(int idContrato) {
        this.idContrato = idContrato;
    }

    public String getFechaInicio() {
        return fechaDesde;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaDesde = fechaInicio;
    }

    public String getFechaFin() {
        return fechaHasta;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaHasta = fechaFin;
    }

    public double getMontoAlquiler() {
        return precio;
    }

    public void setMontoAlquiler(double montoAlquiler) {
        this.precio = montoAlquiler;
    }


    public Inquilino getInquilino() {
        return inquilino;
    }

    public void setInquilino(Inquilino inquilino) {
        this.inquilino = inquilino;
    }

    public Inmueble getInmueble() {
        return inmueble;
    }

    public void setInmueble(Inmueble inmueble) {
        this.inmueble = inmueble;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contrato contrato = (Contrato) o;
        return idContrato == contrato.idContrato;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idContrato);
    }
}
