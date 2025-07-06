package es.uah.clienteCursosSeguro.controller;

import es.uah.clienteCursosSeguro.model.Alumno;
import es.uah.clienteCursosSeguro.model.Curso;
import es.uah.clienteCursosSeguro.model.Usuario;
import es.uah.clienteCursosSeguro.paginator.PageRender;
import es.uah.clienteCursosSeguro.service.ICursosService;
import es.uah.clienteCursosSeguro.service.IUploadFileService;
import es.uah.clienteCursosSeguro.service.IUsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.List;

import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

@Controller
@RequestMapping("/ccursos")
public class CursosController {

    @Autowired
    ICursosService cursosService;

    @Autowired
    IUsuariosService usuariosService;

    @Autowired
    private IUploadFileService uploadFileService;

    @GetMapping("/uploads/{filename:.+}")
    public ResponseEntity<Resource> verFoto(@PathVariable String filename) {

        Resource recurso = null;

        try {
            recurso = uploadFileService.load(filename);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"")
                .body(recurso);
    }

    @GetMapping(value = {"/", "/home", ""})
    public String home(Model model) {
        return "home";
    }

    @GetMapping(value = "/ver/{id}")
    public String ver(Model model, @PathVariable("id") Integer id, RedirectAttributes attributes) {
        Curso curso = cursosService.buscarCursoPorId(id);
        model.addAttribute("curso", curso);
        model.addAttribute("titulo", "Detalle del curso: " + curso.getNombre());
        return "cursos/verCurso";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        Curso curso = new Curso();
        model.addAttribute("titulo", "Nuevo curso");
        model.addAttribute("curso", curso);
        return "cursos/formCurso";
    }

    @GetMapping("/buscar")
    public String buscar(Model model) {
        return "cursos/searchCurso";
    }

    @GetMapping("/idcurso/{id}")
    public String buscarCursoPorId(Model model, @PathVariable("id") Integer id) {
        Curso curso = cursosService.buscarCursoPorId(id);
        model.addAttribute("curso", curso);
        return "cursos/formCurso";
    }

    @GetMapping("/listado")
    public String listadoCursos(Model model, @RequestParam(name="page", defaultValue="0") int page) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<Curso> listado = cursosService.buscarTodos(pageable);
        PageRender<Curso> pageRender = new PageRender<Curso>("/ccursos/listado", listado);
        model.addAttribute("titulo", "Listado de todos los cursos");
        model.addAttribute("listadoCursos", listado);
        model.addAttribute("page", pageRender);
        return "cursos/listCurso";
    }

    @GetMapping("/listado/usuarioActual")
    public String listadoAlumnoCursos(Model model, @RequestParam(name="page", defaultValue="0") int page, @AuthenticationPrincipal OidcUser oidcUser) {
        Pageable pageable = PageRequest.of(page, 5);
        Usuario usuario = usuariosService.buscarUsuarioPorCorreo(oidcUser.getAttribute("email"));
        Page<Curso> listado = cursosService.buscarCursosPorProfesor(usuario.getNombre(), pageable);
        PageRender<Curso> pageRender = new PageRender<Curso>("/ccursos/listado/usuarioActual", listado);
        model.addAttribute("titulo", "Listado de los cursos del profesor");
        model.addAttribute("listadoCursos", listado);
        model.addAttribute("page", pageRender);
        return "cursos/listCurso";
    }

    @GetMapping("/nombre")
    public String buscarCursosPorNombre(Model model, @RequestParam(name="page", defaultValue="0") int page, @RequestParam("nombre") String nombre) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<Curso> listado;
        if (nombre.isEmpty()) {
            listado = cursosService.buscarTodos(pageable);
        } else {
            listado = cursosService.buscarCursosPorNombre(nombre, pageable);
        }

        PageRender<Curso> pageRender = new PageRender<Curso>("/ccursos/nombre?nombre=%s".formatted(nombre), listado);
        model.addAttribute("titulo", "Listado de cursos por nombre");
        model.addAttribute("listadoCursos", listado);
        model.addAttribute("page", pageRender);
        return "cursos/listCurso";
    }

    @GetMapping("/categoria")
    public String buscarCursosPorCategoria(Model model, @RequestParam(name="page", defaultValue="0") int page, @RequestParam("categoria") String categoria) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<Curso> listado;
        if (categoria.isEmpty()) {
            listado = cursosService.buscarTodos(pageable);
        } else {
            listado = cursosService.buscarCursosPorCategoria(categoria, pageable);
        }

        PageRender<Curso> pageRender = new PageRender<Curso>("/ccursos/categoria?categoria=%s".formatted(categoria), listado);
        model.addAttribute("titulo", "Listado de cursos por categoria");
        model.addAttribute("listadoCursos", listado);
        model.addAttribute("page", pageRender);
        return "cursos/listCurso";
    }

    @GetMapping("/profesor")
    public String buscarCursosPorProfesor(Model model, @RequestParam(name="page", defaultValue="0") int page, @RequestParam("profesor") String profesor) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<Curso> listado;
        if (profesor.isEmpty()) {
            listado = cursosService.buscarTodos(pageable);
        } else {
            listado = cursosService.buscarCursosPorProfesor(profesor, pageable);
        }

        PageRender<Curso> pageRender = new PageRender<Curso>("/ccursos/profesor?profesor=%s".formatted(profesor), listado);
        model.addAttribute("titulo", "Listado de cursos por profesor");
        model.addAttribute("listadoCursos", listado);
        model.addAttribute("page", pageRender);
        return "cursos/listCurso";
    }

    @GetMapping("/listadoAlumnos/{id}")
    public String buscarAlumnosPorCurso(Model model, @RequestParam(name="page", defaultValue="0") int page, @PathVariable("id") Integer id) {
        Pageable pageable = PageRequest.of(page, 5);
        Curso curso = cursosService.buscarCursoPorId(id);
        List<Alumno> listadoA = curso.getAlumnos();
        Page<Alumno> listado = new PageImpl<>(listadoA);
        PageRender<Alumno> pageRender = new PageRender<Alumno>("/listado", listado);
        model.addAttribute("titulo", "Listado de alumnos por curso");
        model.addAttribute("listadoAlumnos", listado);
        model.addAttribute("page", pageRender);
        return "cursos/listAlumno";
    }

    @PostMapping("/guardar/")
    public String guardarCurso(Model model, Curso curso, @RequestParam("file") MultipartFile foto, RedirectAttributes attributes) {
        if (!foto.isEmpty()) {

            if (curso.getIdCurso() != null && curso.getIdCurso() > 0 && curso.getImagen() != null
                    && curso.getImagen().length() > 0) {

                uploadFileService.delete(curso.getImagen());
            }

            String uniqueFilename = null;
            try {
                uniqueFilename = uploadFileService.copy(foto);
            } catch (IOException e) {
                e.printStackTrace();
            }

            attributes.addFlashAttribute("msg", "Has subido correctamente '" + uniqueFilename + "'");

            curso.setImagen(uniqueFilename);
        }

        cursosService.guardarCurso(curso);
        model.addAttribute("titulo", "Nuevo curso");
        attributes.addFlashAttribute("msg", "Los datos del curso fueron guardados!");
        return "redirect:/ccursos/listado";
    }

    @GetMapping("/editar/{id}")
    public String editarCurso(Model model, @PathVariable("id") Integer id) {
        Curso curso = cursosService.buscarCursoPorId(id);
        model.addAttribute("titulo", "Editar curso");
        model.addAttribute("curso", curso);
        return "cursos/formCurso";
    }

    @GetMapping("/borrar/{id}")
    public String eliminarCurso(Model model, @PathVariable("id") Integer id, RedirectAttributes attributes) {
        Curso curso = cursosService.buscarCursoPorId(id);
        if (uploadFileService.delete(curso.getImagen())) {
            attributes.addFlashAttribute("msg", "Imagen " + curso.getImagen() + " eliminada con exito!");
        }

        cursosService.eliminarCurso(id);
        attributes.addFlashAttribute("msg", "Los datos del curso fueron borrados!");

        return "redirect:/ccursos/listado";
    }

}
