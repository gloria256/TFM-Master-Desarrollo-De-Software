package es.uah.usuariosMatriculasEureka.dao;

import es.uah.usuariosMatriculasEureka.model.Matricula;

import java.util.List;

public interface IMatriculasDAO {

    List<Matricula> buscarTodas();

    List<Matricula> buscarMatriculasPorIdCurso(Integer idCurso);

    Matricula buscarMatriculaPorId(Integer idMatricula);

    void guardarMatricula(Matricula matricula);

    void eliminarMatricula(Integer idMatricula);

}
