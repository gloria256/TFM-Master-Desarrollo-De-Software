package es.uah.cursosAlumnosEureka.dao;

import es.uah.cursosAlumnosEureka.model.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAlumnosJPA extends JpaRepository<Alumno, Integer> {

    Alumno findByCorreo(String correo);

}
