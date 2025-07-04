package es.uah.usuariosMatriculasEureka.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.Hibernate;

import jakarta.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "Matriculas", schema = "usuariosdbsec")
public class Matricula {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer idMatricula;

    @Column(name = "idCurso", nullable = false)
    private Integer idCurso;

    @Column(name = "precio", nullable = false)
    private Double precio;

    @Column(name = "fecha", nullable = false)
    private Date fecha;

    @ManyToOne
    @JoinColumn(name = "Users_idUsuario", referencedColumnName = "idUsuario", nullable = false)
    @JsonIgnoreProperties(value = "matriculas", allowSetters = true)
    private Usuario usuario;

    public Integer getIdMatricula() {
        return idMatricula;
    }

    public void setIdMatricula(Integer idMatricula) {
        this.idMatricula = idMatricula;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Integer getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(Integer idCurso) {
        this.idCurso = idCurso;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Matricula matricula = (Matricula) o;
        return idMatricula != null && Objects.equals(idMatricula, matricula.idMatricula);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}