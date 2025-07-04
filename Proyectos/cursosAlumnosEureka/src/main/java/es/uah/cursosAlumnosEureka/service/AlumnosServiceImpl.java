package es.uah.cursosAlumnosEureka.service;

import es.uah.cursosAlumnosEureka.dao.IAlumnosDAO;
import es.uah.cursosAlumnosEureka.model.Alumno;
import es.uah.cursosAlumnosEureka.model.Curso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlumnosServiceImpl implements IAlumnosService {

    @Autowired
    IAlumnosDAO alumnosDAO;

    @Override
    public List<Alumno> buscarTodos() {
        return alumnosDAO.buscarTodos();
    }

    @Override
    public Alumno buscarAlumnoPorId(Integer idAlumno) {
        return alumnosDAO.buscarAlumnoPorId(idAlumno);
    }

    @Override
    public Alumno buscarAlumnoPorCorreo(String correo) {
        return alumnosDAO.buscarAlumnoPorCorreo(correo);
    }

    @Override
    public void guardarAlumno(Alumno alumno) {
        alumnosDAO.guardarAlumno(alumno);
    }

    @Override
    public void eliminarAlumno(Integer idAlumno) {
        alumnosDAO.eliminarAlumno(idAlumno);
    }

    @Override
    public void actualizarAlumno(Alumno alumno) {
        alumnosDAO.actualizarAlumno(alumno);
    }

    @Override
    public void inscribirCurso(Integer idAlumno, Integer idCurso) {
        alumnosDAO.inscribirCurso(idAlumno, idCurso);
    }

}
