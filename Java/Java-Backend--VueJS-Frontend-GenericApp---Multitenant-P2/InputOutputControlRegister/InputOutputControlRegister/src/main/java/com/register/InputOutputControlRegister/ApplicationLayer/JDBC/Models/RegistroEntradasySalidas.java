package com.register.InputOutputControlRegister.ApplicationLayer.JDBC.Models;

import java.util.Date;

public class RegistroEntradasySalidas {


    public String id;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String fkIdUsuario;
    public String tipoRegistro;
    public String fechayHora;
    public String fechaRegistro;
    public String fechaAnalizada;

    public String getFechaAnalizada() {
        return this.fechaAnalizada;
    }

    public void setFechaAnalizada(String fechaAnalizada) {
        this.fechaAnalizada = fechaAnalizada;
    }



    public String getFkIdUsuario() {
        return this.fkIdUsuario;
    }

    public void setFkIdUsuario(String fkIdUsuario) {
        this.fkIdUsuario = fkIdUsuario;
    }

    public String getTipoRegistro() {
        return this.tipoRegistro;
    }

    public void setTipoRegistro(String tipoRegistro) {
        this.tipoRegistro = tipoRegistro;
    }

    public String getFechayHora() {
        return this.fechayHora;
    }

    public void setFechayHora(String fechayHora) {
        this.fechayHora = fechayHora;
    }

    public String getFechaRegistro() {
        return this.fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public RegistroEntradasySalidas(String id,String fkIdUsuario, String tipoRegistro, String fechayHora, String fechaRegistro,String fechaAnalizada) {
        
        this.id = id;
        this.fkIdUsuario = fkIdUsuario;
        this.tipoRegistro = tipoRegistro;
        this.fechayHora = fechayHora;
        this.fechaRegistro = fechaRegistro;
        this.fechaAnalizada = fechaAnalizada;
    }



}