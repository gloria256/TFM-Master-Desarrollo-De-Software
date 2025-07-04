package es.uah.cursosAlumnosEureka.dao;

import es.uah.cursosAlumnosEureka.model.Curso;

import java.util.List;

public interface ICursosDAO {

    List<Curso> buscarTodos();

    Curso buscarCursoPorId(Integer idCurso);

    List<Curso> buscarCursosPorNombre(String nombre);

    List<Curso> buscarCursosPorCategoria(String categoria);

    List<Curso> buscarCursosPorProfesor(String profesor);

    void guardarCurso(Curso curso);

    void eliminarCurso(Integer idCurso);

    void actualizarCurso(Curso curso);

}
