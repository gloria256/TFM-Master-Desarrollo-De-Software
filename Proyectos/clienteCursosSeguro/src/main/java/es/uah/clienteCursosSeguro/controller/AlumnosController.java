package es.uah.clienteCursosSeguro.controller;

import es.uah.clienteCursosSeguro.model.Alumno;
import es.uah.clienteCursosSeguro.paginator.PageRender;
import es.uah.clienteCursosSeguro.service.IAlumnosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.Arrays;

@Controller
@RequestMapping("/calumnos")
public class AlumnosController {
    @Autowired
    IAlumnosService alumnosService;

    @GetMapping("/listado")
    public String listadoAlumnos(Model model, @RequestParam(name="page", defaultValue="0") int page) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<Alumno> listado = alumnosService.buscarTodos(pageable);
        PageRender<Alumno> pageRender = new PageRender<Alumno>("/calumnos/listado", listado);
        model.addAttribute("titulo", "Listado de todos los alumnos");
        model.addAttribute("listadoAlumnos", listado);
        model.addAttribute("page", pageRender);
        return "cursos/listAlumno";
    }

    @GetMapping("/listado/usuarioActual")
    public String listadoAlumnoCursos(Model model, @RequestParam(name="page", defaultValue="0") int page, Principal principal) {
        Alumno alumno = alumnosService.buscarAlumnoPorCorreo(principal.getName());
        model.addAttribute("titulo", "Listado de cursos del alumno");
        Page<Alumno> listado = new PageImpl<>(Arrays.asList(alumno));
        PageRender<Alumno> pageRender = new PageRender<Alumno>("/calumnos/listado", listado);
        model.addAttribute("listadoAlumnos", listado);
        model.addAttribute("page", pageRender);
        return "cursos/listAlumno";
    }

    @PostMapping("/guardar/")
    public String guardarAlumno(Model model, Alumno alumno, RedirectAttributes attributes) {
        alumnosService.guardarAlumno(alumno);
        model.addAttribute("titulo", "Nuevo alumno");
        attributes.addFlashAttribute("msg", "Los datos del  alumno fueron guardados!");
        return "redirect:/calumnos/listado";
    }

    @GetMapping("/editar/{id}")
    public String editarAlumno(Model model, @PathVariable("id") Integer id) {
        Alumno alumno = alumnosService.buscarAlumnoPorId(id);
        model.addAttribute("titulo", "Editar alumno");
        model.addAttribute("alumno", alumno);
        return "cursos/formAlumno";
    }

    @GetMapping("/borrar/{id}")
    public String eliminarMatricula(Model model, @PathVariable("id") Integer id, RedirectAttributes attributes) {
        Alumno alumno = alumnosService.buscarAlumnoPorId(id);
        if (alumno != null) {
            alumnosService.eliminarAlumno(id);
            attributes.addFlashAttribute("msg", "Los datos de la matricula fueron borrados!");
        } else {
            attributes.addFlashAttribute("msg", "Alumno no encontrado!");
        }

        return "redirect:/cmatriculas/listado";
    }


}
