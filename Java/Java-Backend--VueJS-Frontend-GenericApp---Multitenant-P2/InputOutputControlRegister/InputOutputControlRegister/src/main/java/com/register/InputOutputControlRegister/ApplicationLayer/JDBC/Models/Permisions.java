package com.register.InputOutputControlRegister.ApplicationLayer.JDBC.Models;

public class Permisions{

    public String fk_id_usuario;
    public String fk_id_personal_que_autoriza_permisos;
    public String fk_id_permiso;
    public String fecha_hora_solicitud_permiso;
    public String fecha_solicitud_permiso;

    public Permisions(String fk_id_usuario, String fk_id_personal_que_autoriza_permisos, String fk_id_permiso, String fecha_hora_solicitud_permiso, String fecha_solicitud_permiso) {
        this.fk_id_usuario = fk_id_usuario;
        this.fk_id_personal_que_autoriza_permisos = fk_id_personal_que_autoriza_permisos;
        this.fk_id_permiso = fk_id_permiso;
        this.fecha_hora_solicitud_permiso = fecha_hora_solicitud_permiso;
        this.fecha_solicitud_permiso = fecha_solicitud_permiso;
    }

    public String getFk_id_usuario() {
        return this.fk_id_usuario;
    }

    public void setFk_id_usuario(String fk_id_usuario) {
        this.fk_id_usuario = fk_id_usuario;
    }

    public String getFk_id_personal_que_autoriza_permisos() {
        return this.fk_id_personal_que_autoriza_permisos;
    }

    public void setFk_id_personal_que_autoriza_permisos(String fk_id_personal_que_autoriza_permisos) {
        this.fk_id_personal_que_autoriza_permisos = fk_id_personal_que_autoriza_permisos;
    }

    public String getFk_id_permiso() {
        return this.fk_id_permiso;
    }

    public void setFk_id_permiso(String fk_id_permiso) {
        this.fk_id_permiso = fk_id_permiso;
    }

    public String getFecha_hora_solicitud_permiso() {
        return this.fecha_hora_solicitud_permiso;
    }

    public void setFecha_hora_solicitud_permiso(String fecha_hora_solicitud_permiso) {
        this.fecha_hora_solicitud_permiso = fecha_hora_solicitud_permiso;
    }

    public String getFecha_solicitud_permiso() {
        return this.fecha_solicitud_permiso;
    }

    public void setFecha_solicitud_permiso(String fecha_solicitud_permiso) {
        this.fecha_solicitud_permiso = fecha_solicitud_permiso;
    }



}