package es.uah.cursosAlumnosEureka.service;

import es.uah.cursosAlumnosEureka.model.Curso;

import java.util.List;

public interface ICursosService {

    List<Curso> buscarTodos();

    Curso buscarCursoPorId(Integer idCurso);

    List<Curso> buscarCursosPorNombre(String nombre);

    List<Curso> buscarCursosPorCategoria(String categoria);

    List<Curso> buscarCursosPorProfesor(String profesor);

    void guardarCurso(Curso curso);

    void actualizarCurso(Curso curso);

    void eliminarCurso(Integer idCurso);

}
