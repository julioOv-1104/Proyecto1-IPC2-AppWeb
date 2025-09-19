
package Entidades;

public class Usuario {
    
    private String id,institucion, nombre,numero_telefonico,correo,foto, password;
    private double monedero;
    private TipoUsuarios tipo;

    public Usuario(String id, String nombre, String numero_telefonico, String correo,  double monedero,  TipoUsuarios tipo, String password) {
        this.id = id;
        this.nombre = nombre;
        this.numero_telefonico = numero_telefonico;
        this.correo = correo;
        this.password = password;
        this.monedero = monedero;
        this.tipo = tipo;
    }

    public Usuario(String id, String institucion, String nombre, String correo) {
        this.id = id;
        this.institucion = institucion;
        this.nombre = nombre;
        this.correo = correo;
    }
    
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInstitucion() {
        return institucion;
    }

    public void setInstitucion(String institucion) {
        this.institucion = institucion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNumero_telefonico() {
        return numero_telefonico;
    }

    public void setNumero_telefonico(String numero_telefonico) {
        this.numero_telefonico = numero_telefonico;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getMonedero() {
        return monedero;
    }

    public void setMonedero(double monedero) {
        this.monedero = monedero;
    }

    public TipoUsuarios getTipo() {
        return tipo;
    }

    public void setTipo(TipoUsuarios tipo) {
        this.tipo = tipo;
    }
    
    
    
    
}
