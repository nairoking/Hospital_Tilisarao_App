package com.example.hospital_tilisarao.Modelo;

import android.icu.lang.UProperty;

import java.io.Serializable;

public class TurnoView implements Serializable {
    public String fecha;
    public int medicoId;

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getMedicoId() {
        return medicoId;
    }

    public void setMedicoId(int medicoId) {
        this.medicoId = medicoId;
    }
}
