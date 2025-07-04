package es.uah.cursosAlumnosEureka.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.Hibernate;

import jakarta.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Alumnos", schema = "cursosalumnosdb")
public class Alumno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idAlumno", nullable = false)
    private Integer idAlumno;

    @Column(name = "nombre", nullable = false, length = 45)
    private String nombre;

    @Column(name = "correo", nullable = false, length = 45)
    private String correo;

    @JoinTable(name = "Cursos_has_Alumnos", joinColumns = {
            @JoinColumn(name = "Alumnos_idAlumno", referencedColumnName = "idAlumno")}, inverseJoinColumns = {
            @JoinColumn(name = "Cursos_idCurso", referencedColumnName = "idCurso")})
    @ManyToMany
    @JsonIgnoreProperties("alumnos")
    private List<Curso> cursos;

    public Alumno() {
    }

    public List<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(Integer idAlumno) {
        this.idAlumno = idAlumno;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Alumno alumno = (Alumno) o;
        return idAlumno != null && Objects.equals(idAlumno, alumno.idAlumno);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public void addCurso(Curso curso) {
        if (curso != null) {
            getCursos().add(curso);
            curso.addAlumno(this);
        }
    }

    public void removeCurso(Curso curso) {
        if (curso != null) {
            curso.removeAlumno(this);
            getCursos().remove(curso);
        }
    }

}