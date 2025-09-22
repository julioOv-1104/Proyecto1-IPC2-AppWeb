
package Entidades;

public class Salon {
    private String nombreSalon, nombreInstalacion, codigoActividad;

    public Salon(String nombreSalon, String nombreInstitucion, String codigoActividad) {
        this.nombreSalon = nombreSalon;
        this.nombreInstalacion = nombreInstitucion;
        this.codigoActividad = codigoActividad;
    }

    public String getNombreSalon() {
        return nombreSalon;
    }

    public void setNombreSalon(String nombreSalon) {
        this.nombreSalon = nombreSalon;
    }

    public String getNombreInstalacion() {
        return nombreInstalacion;
    }

    public void setNombreInstalacion(String nombreInstalacion) {
        this.nombreInstalacion = nombreInstalacion;
    }

    public String getCodigoActividad() {
        return codigoActividad;
    }

    public void setCodigoActividad(String codigoActividad) {
        this.codigoActividad = codigoActividad;
    }
    
    
    
}
