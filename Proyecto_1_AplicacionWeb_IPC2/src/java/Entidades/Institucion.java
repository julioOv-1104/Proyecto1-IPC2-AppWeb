
package Entidades;


public class Institucion {
    private String codigo_congreso, id_administrador, nombre_institucion;

    public Institucion(String id_administrador, String nombre_institucion) {
        this.id_administrador = id_administrador;
        this.nombre_institucion = nombre_institucion;
    }

    public Institucion() {
    }
    
    

    public String getCodigo_congreso() {
        return codigo_congreso;
    }

    public void setCodigo_congreso(String codigo_congreso) {
        this.codigo_congreso = codigo_congreso;
    }

    public String getId_administrador() {
        return id_administrador;
    }

    public void setId_administrador(String id_administrador) {
        this.id_administrador = id_administrador;
    }

    public String getNombre_institucion() {
        return nombre_institucion;
    }

    public void setNombre_institucion(String nombre_institucion) {
        this.nombre_institucion = nombre_institucion;
    }
     
}
