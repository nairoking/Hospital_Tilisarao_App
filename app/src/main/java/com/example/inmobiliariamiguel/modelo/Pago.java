package com.example.inmobiliariamiguel.modelo;

import java.io.Serializable;
import java.time.LocalDate;

public class Pago implements Serializable {

    private int id;
    private int numeroPago;
    private Contrato contrato;
    private double monto;
    private String fechaPago;

    public Pago() {}

    public Pago(int idPago, int numero, Contrato contrato, double importe, String fechaDePago) {
        this.id = idPago;
        this.numeroPago = numero;
        this.contrato = contrato;
        this.monto = importe;
        this.fechaPago = fechaDePago;
    }

    public int getIdPago() {
        return id;
    }

    public void setIdPago(int idPago) {
        this.id = idPago;
    }

    public int getNumero() {
        return numeroPago;
    }

    public void setNumero(int numero) {
        this.numeroPago = numero;
    }

    public Contrato getContrato() {
        return contrato;
    }

    public void setContrato(Contrato contrato) {
        this.contrato = contrato;
    }

    public double getImporte() {
        return monto;
    }

    public void setImporte(double importe) {
        this.monto = importe;
    }

    public String getFechaDePago() {
        return fechaPago;
    }

    public void setFechaDePago(String fechaDePago) {
        this.fechaPago = fechaDePago;
    }
}
