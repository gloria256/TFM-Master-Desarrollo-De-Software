package es.uah.clienteCursosSeguro.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Alumno {

    private Integer idAlumno;
    private String nombre;
    private String correo;
    private List<Curso> cursos;

    public Alumno(String nombre, String correo) {
        this.idAlumno = 0;
        this.nombre = nombre;
        this.correo = correo;
        this.cursos = new ArrayList<>();
    }

    public Alumno() {
    }

    public Integer getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(Integer idAlumno) {
        this.idAlumno = idAlumno;
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

    public List<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Alumno alumno = (Alumno) o;
        return Objects.equals(idAlumno, alumno.idAlumno);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(idAlumno);
    }

    @Override
    public String toString() {
        return "Alumno{" +
                "idAlumno=" + idAlumno +
                ", nombre='" + nombre + '\'' +
                ", correo='" + correo + '\'' +
                ", cursos=" + cursos +
                '}';
    }
}
