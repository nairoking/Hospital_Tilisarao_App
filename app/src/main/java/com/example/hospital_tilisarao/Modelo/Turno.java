package com.example.hospital_tilisarao.Modelo;

import android.icu.lang.UProperty;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

public class Turno implements Serializable {

    private int id;
    private String horaInicio;
    private String fecha;
    private int medicoId;
    private int pacienteId;
    private Paciente paciente;
    private  Medico medico;

    public Turno(int id, String horaInicio, String fecha, int medicoId, int pacienteId, Paciente paciente, Medico medico) {
        this.id = id;
        this.horaInicio = horaInicio;
        this.fecha = fecha;
        this.medicoId = medicoId;
        this.pacienteId = pacienteId;
        this.paciente = paciente;
        this.medico = medico;
    }

    public Turno() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

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

    public int getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(int pacienteId) {
        this.pacienteId = pacienteId;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }
}
