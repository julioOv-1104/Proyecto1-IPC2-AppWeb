package Entidades;

import java.time.LocalTime;

public class Actividad {

    private String idEncargado, descripcion;
    private LocalTime horaInicio, horaFin;
    private String nombreActividad, codigoActividad, codigoCongreso;
    private EnumTipoActvidad tipo;
    private String salon;

    public Actividad(String idEncargado, String descripcion, LocalTime horaInicio, LocalTime horaFin, String nombreActividad, String codigoActividad, String codigoCongreso, EnumTipoActvidad tipo, String salon) {
        this.idEncargado = idEncargado;
        this.descripcion = descripcion;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.nombreActividad = nombreActividad;
        this.codigoActividad = codigoActividad;
        this.codigoCongreso = codigoCongreso;
        this.tipo = tipo;
        this.salon = salon;
    }

    public Actividad() {
    }

    public String getIdEncargado() {
        return idEncargado;
    }

    public void setIdEncargado(String idEncargado) {
        this.idEncargado = idEncargado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalTime getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(LocalTime horaFin) {
        this.horaFin = horaFin;
    }

    public String getNombreActividad() {
        return nombreActividad;
    }

    public void setNombreActividad(String nombreActividad) {
        this.nombreActividad = nombreActividad;
    }

    public String getCodigoActividad() {
        return codigoActividad;
    }

    public void setCodigoActividad(String codigoActividad) {
        this.codigoActividad = codigoActividad;
    }

    public String getCodigoCongreso() {
        return codigoCongreso;
    }

    public void setCodigoCongreso(String codigoCongreso) {
        this.codigoCongreso = codigoCongreso;
    }

    public EnumTipoActvidad getTipo() {
        return tipo;
    }

    public void setTipo(EnumTipoActvidad tipo) {
        this.tipo = tipo;
    }

    public String getSalon() {
        return salon;
    }

    public void setSalon(String salon) {
        this.salon = salon;
    }

}
