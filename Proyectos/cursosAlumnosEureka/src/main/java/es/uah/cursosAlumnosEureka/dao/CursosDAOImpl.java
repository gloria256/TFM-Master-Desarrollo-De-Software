package es.uah.cursosAlumnosEureka.dao;

import es.uah.cursosAlumnosEureka.model.Curso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CursosDAOImpl implements ICursosDAO {

    @Autowired
    ICursosJPA cursosJPA;

    @Override
    public List<Curso> buscarTodos() {
        return cursosJPA.findAll();
    }

    @Override
    public Curso buscarCursoPorId(Integer idCurso) {
        Optional<Curso> optional = cursosJPA.findById(idCurso);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    @Override
    public List<Curso> buscarCursosPorNombre(String nombre) {
        return cursosJPA.findByNombreContainingIgnoreCase(nombre);
    }

    @Override
    public List<Curso> buscarCursosPorCategoria(String categoria) {
        return cursosJPA.findByCategoriaContainingIgnoreCase(categoria);
    }

    @Override
    public List<Curso> buscarCursosPorProfesor(String profesor) {
        return cursosJPA.findByProfesor(profesor);
    }

    @Override
    public void guardarCurso(Curso curso) {
        cursosJPA.save(curso);
    }

    @Override
    public void eliminarCurso(Integer idCurso) {
        cursosJPA.deleteById(idCurso);
    }

    @Override
    public void actualizarCurso(Curso curso) {
        cursosJPA.save(curso);
    }
}
