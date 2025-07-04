package es.uah.clienteCursosSeguro.service;

import es.uah.clienteCursosSeguro.model.Curso;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICursosService {

    Page<Curso> buscarTodos(Pageable pageable);

    Curso buscarCursoPorId(Integer idCurso);

    Page<Curso> buscarCursosPorNombre(String nombre, Pageable pageable);

    Page<Curso> buscarCursosPorCategoria(String categoria, Pageable pageable);

    Page<Curso> buscarCursosPorProfesor(String profesor, Pageable pageable);

    void guardarCurso(Curso curso);

    void eliminarCurso(Integer idCurso);

}
