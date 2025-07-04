package es.uah.cursosAlumnosEureka.dao;

import es.uah.cursosAlumnosEureka.model.Alumno;
import es.uah.cursosAlumnosEureka.model.Curso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AlumnosDAOImpl implements IAlumnosDAO {

    @Autowired
    IAlumnosJPA alumnosJPA;

    @Autowired
    ICursosJPA cursosJPA;

    @Override
    public List<Alumno> buscarTodos() {
        return alumnosJPA.findAll();
    }

    @Override
    public Alumno buscarAlumnoPorId(Integer idAlumno) {
        Optional<Alumno> optional = alumnosJPA.findById(idAlumno);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    @Override
    public Alumno buscarAlumnoPorCorreo(String correo) {
        Optional<Alumno> optional = Optional.ofNullable(alumnosJPA.findByCorreo(correo));
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    @Override
    public void guardarAlumno(Alumno alumno) {
        alumnosJPA.save(alumno);
    }

    @Override
    public void eliminarAlumno(Integer idAlumno) {
        Optional<Alumno> optional = alumnosJPA.findById(idAlumno);
        if (optional.isPresent()) {
            Alumno alumno = optional.get();
            List<Curso> cursos = alumno.getCursos();
            for (Curso curso: cursos) {
                curso.removeAlumno(alumno);
            }
        }
        alumnosJPA.deleteById(idAlumno);
    }

    @Override
    public void actualizarAlumno(Alumno alumno) {
        alumnosJPA.save(alumno);
    }

    @Override
    public void inscribirCurso(Integer idAlumno, Integer idCurso) {
        Optional<Alumno> optionalAlumno = alumnosJPA.findById(idAlumno);
        if (optionalAlumno.isPresent()) {
            Alumno alumno = optionalAlumno.get();
            Optional<Curso> optionalCurso = cursosJPA.findById(idCurso);
            if (optionalCurso.isPresent()) {
                alumno.addCurso(optionalCurso.get());
                alumnosJPA.save(alumno);
            }
        }
    }

}
