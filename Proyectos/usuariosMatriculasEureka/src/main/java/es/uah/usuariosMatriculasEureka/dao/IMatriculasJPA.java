package es.uah.usuariosMatriculasEureka.dao;

import es.uah.usuariosMatriculasEureka.model.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IMatriculasJPA extends JpaRepository<Matricula, Integer> {

    List<Matricula> findByIdCurso(int idCurso);

}
