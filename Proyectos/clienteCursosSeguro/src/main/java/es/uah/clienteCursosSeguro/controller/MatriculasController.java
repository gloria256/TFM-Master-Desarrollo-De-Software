package es.uah.clienteCursosSeguro.controller;

import es.uah.clienteCursosSeguro.model.Matricula;
import es.uah.clienteCursosSeguro.model.Usuario;
import es.uah.clienteCursosSeguro.paginator.PageRender;
import es.uah.clienteCursosSeguro.service.IMatriculasService;
import es.uah.clienteCursosSeguro.service.IUsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
@RequestMapping("/cmatriculas")
public class MatriculasController {
    @Autowired
    IMatriculasService matriculasService;

    @Autowired
    IUsuariosService usuariosService;

    @GetMapping("/listado")
    public String listadoMatriculas(Model model, @RequestParam(name="page", defaultValue="0") int page) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<Matricula> listado = matriculasService.buscarTodas(pageable);
        PageRender<Matricula> pageRender = new PageRender<Matricula>("/cmatriculas/listado", listado);
        model.addAttribute("titulo", "Listado de todas las matriculas");
        model.addAttribute("listadoMatriculas", listado);
        model.addAttribute("page", pageRender);
        return "usuarios/listMatricula";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        Matricula matricula = new Matricula();
        model.addAttribute("titulo", "Nueva matricula");
        model.addAttribute("matricula", matricula);
        return "usuarios/formMatricula";
    }

    @PostMapping("/guardar/")
    public String guardarMatricula(Model model, Matricula matricula, RedirectAttributes attributes) {
        String resultado = matriculasService.guardarMatricula(matricula);
        model.addAttribute("titulo", "Nueva matricula");
        attributes.addFlashAttribute("msg", resultado);
        return "redirect:/cmatriculas/listado";
    }

    @GetMapping("/matricular/{idCurso}")
    public String matricular(@PathVariable("idCurso") Integer idCurso, RedirectAttributes attributes, Principal principal) {
        Usuario usuario = usuariosService.buscarUsuarioPorCorreo(principal.getName());
        Matricula matricula = new Matricula(idCurso, usuario);
        String resultado = matriculasService.guardarMatricula(matricula);
        attributes.addFlashAttribute("msg", resultado);
        return "redirect:/ccursos/listado";
    }

    @GetMapping("/editar/{id}")
    public String editarMatricula(Model model, @PathVariable("id") Integer id) {
        Matricula matricula = matriculasService.buscarMatriculaPorId(id);
        model.addAttribute("titulo", "Editar matricula");
        model.addAttribute("matricula", matricula);
        return "usuarios/formMatricula";
    }

    @GetMapping("/borrar/{id}")
    public String eliminarMatricula(Model model, @PathVariable("id") Integer id, RedirectAttributes attributes) {
        Matricula matricula = matriculasService.buscarMatriculaPorId(id);
        if (matricula != null) {
            matriculasService.eliminarMatricula(id);
            attributes.addFlashAttribute("msg", "Los datos de la matricula fueron borrados!");
        } else {
            attributes.addFlashAttribute("msg", "Matricula no encontrada!");
        }

        return "redirect:/cmatriculas/listado";
    }

}
