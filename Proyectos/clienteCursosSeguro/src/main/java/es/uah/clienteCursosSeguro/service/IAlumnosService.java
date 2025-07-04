package es.uah.clienteCursosSeguro.service;

import es.uah.clienteCursosSeguro.model.Alumno;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IAlumnosService {
    Page<Alumno> buscarTodos(Pageable pageable);

    Alumno buscarAlumnoPorId(Integer idAlumno);

    Alumno buscarAlumnoPorCorreo(String correo);

    void guardarAlumno(Alumno alumno);

    void eliminarAlumno(Integer idAlumno);

    void inscribirCurso(Integer idAlumno, Integer idCurso);
}
