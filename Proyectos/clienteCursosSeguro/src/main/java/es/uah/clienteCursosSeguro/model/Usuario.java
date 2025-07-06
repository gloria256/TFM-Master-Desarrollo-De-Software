package es.uah.clienteCursosSeguro.model;

import java.util.List;

public class Usuario {

    private Integer idUsuario;
    private String nombre;
    private String correo;
    private boolean enable;
    private String sub;
    private List<Rol> roles;
    private List<Matricula> matriculas;
    

    public Usuario(Integer idUsuario, String nombre, String correo, boolean enable, String sub, List<Rol> roles, List<Matricula> matriculas) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.correo = correo;
        this.enable = enable;
        this.sub = sub;
        this.roles = roles;
        this.matriculas = matriculas;
    }

    public Usuario() {
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }
    
    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public List<Rol> getRoles() {
        return roles;
    }

    public void setRoles(List<Rol> roles) {
        this.roles = roles;
    }

    public List<Matricula> getMatriculas() {
        return matriculas;
    }

    public void setMatriculas(List<Matricula> matriculas) {
        this.matriculas = matriculas;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "idUsuario=" + idUsuario +
                ", nombre='" + nombre + '\'' +
                ", correo='" + correo + '\'' +
                ", enable=" + enable +
                ", sub=" + sub +
                ", roles=" + roles +
                ", matriculas=" + matriculas +
                '}';
    }
}
