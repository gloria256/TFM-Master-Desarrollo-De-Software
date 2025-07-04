package es.uah.usuariosMatriculasEureka.service;

import es.uah.usuariosMatriculasEureka.model.Matricula;

import java.util.List;

public interface IMatriculasService {

    List<Matricula> buscarTodas();

    List<Matricula> buscarMatriculasPorIdCurso(Integer idCurso);

    Matricula buscarMatriculaPorId(Integer idMatricula);

    void guardarMatricula(Matricula matricula);

    void eliminarMatricula(Integer idMatricula);

}
