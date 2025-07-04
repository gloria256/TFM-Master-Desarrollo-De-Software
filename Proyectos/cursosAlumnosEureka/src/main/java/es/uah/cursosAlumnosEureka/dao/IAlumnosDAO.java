package es.uah.cursosAlumnosEureka.dao;

import es.uah.cursosAlumnosEureka.model.Alumno;

import java.util.List;

public interface IAlumnosDAO {
    List<Alumno> buscarTodos();

    Alumno buscarAlumnoPorId(Integer idAlumno);

    Alumno buscarAlumnoPorCorreo(String correo);

    void guardarAlumno(Alumno alumno);

    void eliminarAlumno(Integer idAlumno);

    void actualizarAlumno(Alumno alumno);

    void inscribirCurso(Integer idAlumno, Integer idCurso);

}
