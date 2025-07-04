package es.uah.clienteCursosSeguro.service;


import es.uah.clienteCursosSeguro.model.Matricula;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IMatriculasService {

    Page<Matricula> buscarTodas(Pageable pageable);

    Page<Matricula> buscarMatriculasPorIdCurso(Integer idCurso, Pageable pageable);

    Matricula buscarMatriculaPorId(Integer idMatricula);

    String guardarMatricula(Matricula matricula);

    void eliminarMatricula(Integer idMatricula);

}
