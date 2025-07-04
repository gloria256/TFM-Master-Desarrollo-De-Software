package es.uah.usuariosMatriculasEureka.service;

import es.uah.usuariosMatriculasEureka.dao.IMatriculasDAO;
import es.uah.usuariosMatriculasEureka.model.Matricula;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatriculasServiceImpl implements IMatriculasService {

    @Autowired
    IMatriculasDAO matriculasDAO;

    @Override
    public List<Matricula> buscarTodas() {
        return matriculasDAO.buscarTodas();
    }

    @Override
    public List<Matricula> buscarMatriculasPorIdCurso(Integer idCurso) {
        return matriculasDAO.buscarMatriculasPorIdCurso(idCurso);
    }

    @Override
    public Matricula buscarMatriculaPorId(Integer idMatricula) {
        return matriculasDAO.buscarMatriculaPorId(idMatricula);
    }

    @Override
    public void guardarMatricula(Matricula matricula) {
        matriculasDAO.guardarMatricula(matricula);
    }

    @Override
    public void eliminarMatricula(Integer idMatricula) {
        matriculasDAO.eliminarMatricula(idMatricula);
    }

}
