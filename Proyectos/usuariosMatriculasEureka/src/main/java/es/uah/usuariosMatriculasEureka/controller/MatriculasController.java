package es.uah.usuariosMatriculasEureka.controller;

import es.uah.usuariosMatriculasEureka.model.Matricula;
import es.uah.usuariosMatriculasEureka.service.IMatriculasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MatriculasController {

    @Autowired
    IMatriculasService matriculasService;

    @GetMapping("/matriculas")
    public List<Matricula> buscarTodas() {
        return matriculasService.buscarTodas();
    }

    @GetMapping("/matriculas/curso/{idCurso}")
    public List<Matricula> buscarMatriculasPorIdCurso(@PathVariable("idCurso") Integer idCurso) {
        return matriculasService.buscarMatriculasPorIdCurso(idCurso);
    }

    @GetMapping("/matriculas/{id}")
    public Matricula buscarMatriculaPorId(@PathVariable("id") Integer id) {
        return matriculasService.buscarMatriculaPorId(id);
    }

    @PostMapping("/matriculas")
    public void guardarMatricula(@RequestBody Matricula matricula) {
        matriculasService.guardarMatricula(matricula);
    }

    @DeleteMapping("/matriculas/{id}")
    public void eliminarMatricula(@PathVariable("id") Integer id) {
        matriculasService.eliminarMatricula(id);
    }

}
