package es.uah.cursosAlumnosEureka.dao;

import es.uah.cursosAlumnosEureka.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICursosJPA extends JpaRepository<Curso, Integer> {

    List<Curso> findByNombreContainingIgnoreCase(String nombre);

    List<Curso> findByCategoriaContainingIgnoreCase(String categoria);

    List<Curso> findByProfesor(String profesor);

}
