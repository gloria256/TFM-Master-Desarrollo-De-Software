package es.uah.usuariosMatriculasEureka.dao;

import es.uah.usuariosMatriculasEureka.model.Matricula;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MatriculasDAOImpl implements IMatriculasDAO {

    @Autowired
    IMatriculasJPA matriculasJPA;

    @Override
    public List<Matricula> buscarTodas() {
        return matriculasJPA.findAll();
    }

    @Override
    public List<Matricula> buscarMatriculasPorIdCurso(Integer idCurso) {
        return matriculasJPA.findByIdCurso(idCurso);
    }

    @Override
    public Matricula buscarMatriculaPorId(Integer idMatricula) {
        Optional<Matricula> optional = matriculasJPA.findById(idMatricula);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    @Override
    public void guardarMatricula(Matricula matricula) {
        matriculasJPA.save(matricula);
    }

    @Override
    public void eliminarMatricula(Integer idMatricula) {
        matriculasJPA.deleteById(idMatricula);
    }

}
