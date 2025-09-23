
package Entidades;

public class Participante {
    
    private String nombre, idUsuario;
    private EnumTipoParticipante tipo;

    public Participante(String nombre, String idUsuario, EnumTipoParticipante tipo) {
        this.nombre = nombre;
        this.idUsuario = idUsuario;
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public EnumTipoParticipante getTipo() {
        return tipo;
    }

    public void setTipo(EnumTipoParticipante tipo) {
        this.tipo = tipo;
    }
    
    
}
