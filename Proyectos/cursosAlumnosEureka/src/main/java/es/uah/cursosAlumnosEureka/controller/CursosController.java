package es.uah.cursosAlumnosEureka.controller;

import es.uah.cursosAlumnosEureka.model.Curso;
import es.uah.cursosAlumnosEureka.service.ICursosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CursosController {

    @Autowired
    ICursosService cursosService;

    @GetMapping("/cursos")
    public List<Curso> buscarTodos() {
        return cursosService.buscarTodos();
    }

    @GetMapping("/cursos/{id}")
    public Curso buscarCursoPorId(@PathVariable("id") Integer id) {
        return cursosService.buscarCursoPorId(id);
    }

    @GetMapping("/cursos/nombre/{nombre}")
    public List<Curso> buscarCursosPorNombre(@PathVariable("nombre") String nombre) {
        return cursosService.buscarCursosPorNombre(nombre);
    }

    @GetMapping("/cursos/categoria/{categoria}")
    public List<Curso> buscarCursosPorCategoria(@PathVariable("categoria") String categoria) {
        return cursosService.buscarCursosPorCategoria(categoria);
    }

    @GetMapping("/cursos/profesor/{profesor}")
    public List<Curso> buscarCursosPorProfesor(@PathVariable("profesor") String profesor) {
        return cursosService.buscarCursosPorProfesor(profesor);
    }

    @PostMapping("/cursos")
    public void guardarCurso(@RequestBody Curso curso) {
        cursosService.guardarCurso(curso);
    }

    @PutMapping("/cursos")
    public void actualizarCurso(@RequestBody Curso curso) {
        cursosService.actualizarCurso(curso);
    }

    @DeleteMapping("/cursos/{id}")
    public void eliminarCurso(@PathVariable("id") Integer id) {
        cursosService.eliminarCurso(id);
    }

}
