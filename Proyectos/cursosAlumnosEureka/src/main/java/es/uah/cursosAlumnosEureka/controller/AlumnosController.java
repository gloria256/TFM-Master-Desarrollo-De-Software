package es.uah.cursosAlumnosEureka.controller;

import es.uah.cursosAlumnosEureka.model.Alumno;
import es.uah.cursosAlumnosEureka.service.IAlumnosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AlumnosController {

    @Autowired
    IAlumnosService alumnosService;

    @GetMapping("/alumnos")
    public List<Alumno> buscarTodos() {
        return alumnosService.buscarTodos();
    }

    @GetMapping("/alumnos/{id}")
    public Alumno buscarAlumnoPorId(@PathVariable("id") Integer id) {
        return alumnosService.buscarAlumnoPorId(id);
    }

    @GetMapping("/alumnos/correo/{correo}")
    public Alumno buscarAlumnoPorCorreo(@PathVariable("correo") String correo) {
        return alumnosService.buscarAlumnoPorCorreo(correo);
    }

    @PostMapping("/alumnos")
    public void guardarAlumno(@RequestBody Alumno alumno) {
        alumnosService.guardarAlumno(alumno);
    }

    @PutMapping("/alumnos")
    public void actualizarAlumno(@RequestBody Alumno alumno) {
        alumnosService.actualizarAlumno(alumno);
    }

    @DeleteMapping("/alumnos/{id}")
    public void eliminarAlumno(@PathVariable("id") Integer id) {
        alumnosService.eliminarAlumno(id);
    }

    @GetMapping("/alumnos/insc/{ida}/{idc}")
    public void inscribirCurso(@PathVariable("ida") Integer ida, @PathVariable("idc") Integer idc) {
        alumnosService.inscribirCurso(ida, idc);
    }

}
