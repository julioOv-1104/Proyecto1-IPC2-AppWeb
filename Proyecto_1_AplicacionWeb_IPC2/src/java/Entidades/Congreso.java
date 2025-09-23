package Entidades;

import java.time.LocalDate;

public class Congreso {

    private String codigoCongreso;
    private LocalDate fechaInicio;
    private double precio;
    private String institucion, instalacion;

    public Congreso(String codigoCongreso, LocalDate fechaInicio, double precio, String institucion, String instalacion) {
        this.codigoCongreso = codigoCongreso;
        this.fechaInicio = fechaInicio;
        this.precio = precio;
        this.institucion = institucion;
        this.instalacion = instalacion;
    }

    public String getCodigoCongreso() {
        return codigoCongreso;
    }

    public void setCodigoCongreso(String codigoCongreso) {
        this.codigoCongreso = codigoCongreso;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getInstitucion() {
        return institucion;
    }

    public void setInstitucion(String institucion) {
        this.institucion = institucion;
    }

    public String getInstalacion() {
        return instalacion;
    }

    public void setInstalacion(String instalacion) {
        this.instalacion = instalacion;
    }
    
    

}
