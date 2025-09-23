
package Entidades;

import java.time.LocalDate;

public class Pago {
    
    private String codigoCongreso;
    private LocalDate fecha;
    private double monto;
    private String idUsuario;

    public Pago(String codigoCongreso, LocalDate fecha, double monto, String idUsuario) {
        this.codigoCongreso = codigoCongreso;
        this.fecha = fecha;
        this.monto = monto;
        this.idUsuario = idUsuario;
    }

    public String getCodigoCongreso() {
        return codigoCongreso;
    }

    public void setCodigoCongreso(String codigoCongreso) {
        this.codigoCongreso = codigoCongreso;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    
    
    
}
