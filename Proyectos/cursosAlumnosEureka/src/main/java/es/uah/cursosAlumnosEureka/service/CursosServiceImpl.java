package es.uah.cursosAlumnosEureka.service;

import es.uah.cursosAlumnosEureka.dao.ICursosDAO;
import es.uah.cursosAlumnosEureka.model.Curso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursosServiceImpl implements ICursosService {

    @Autowired
    ICursosDAO cursosDAO;

    @Override
    public List<Curso> buscarTodos() {
        return cursosDAO.buscarTodos();
    }

    @Override
    public Curso buscarCursoPorId(Integer idCurso) {
        return cursosDAO.buscarCursoPorId(idCurso);
    }

    @Override
    public List<Curso> buscarCursosPorNombre(String nombre) {
        return cursosDAO.buscarCursosPorNombre(nombre);
    }

    @Override
    public List<Curso> buscarCursosPorCategoria(String categoria) {
        return cursosDAO.buscarCursosPorCategoria(categoria);
    }

    @Override
    public List<Curso> buscarCursosPorProfesor(String profesor) {
        return cursosDAO.buscarCursosPorProfesor(profesor);
    }

    @Override
    public void guardarCurso(Curso curso) {
        if (cursosDAO.buscarCursoPorId(curso.getIdCurso())==null) {
            cursosDAO.guardarCurso(curso);
        }
    }

    @Override
    public void actualizarCurso(Curso curso) {
        if (cursosDAO.buscarCursoPorId(curso.getIdCurso())!=null) {
            cursosDAO.actualizarCurso(curso);
        }        
    }

    @Override
    public void eliminarCurso(Integer idCurso) {
        if (cursosDAO.buscarCursoPorId(idCurso)!=null) {
            cursosDAO.eliminarCurso(idCurso);
        }
    }
}
