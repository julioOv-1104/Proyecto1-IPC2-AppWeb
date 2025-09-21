
package Entidades;

public class Salon {
    private String nombreSalon, nombreInstitucion;

    public Salon(String nombreSalon, String nombreInstitucion) {
        this.nombreSalon = nombreSalon;
        this.nombreInstitucion = nombreInstitucion;
    }

    public String getNombreSalon() {
        return nombreSalon;
    }

    public void setNombreSalon(String nombreSalon) {
        this.nombreSalon = nombreSalon;
    }

    public String getNombreInstitucion() {
        return nombreInstitucion;
    }

    public void setNombreInstitucion(String nombreInstitucion) {
        this.nombreInstitucion = nombreInstitucion;
    }
    
    
    
}
