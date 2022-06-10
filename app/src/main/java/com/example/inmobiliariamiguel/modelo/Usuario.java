package com.example.inmobiliariamiguel.modelo;

public class Usuario {
    private String Usuario;
    private String Clave;

    public Usuario(String user, String pass) {
        this.Usuario = user;
        this.Clave = pass;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String user) {
        this.Usuario = user;
    }

    public String getPass() {
        return Clave;
    }

    public void setPass(String pass) {
        this.Clave = pass;
    }
}

