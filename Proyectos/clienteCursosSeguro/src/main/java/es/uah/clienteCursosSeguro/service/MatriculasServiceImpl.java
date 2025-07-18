package es.uah.clienteCursosSeguro.service;

import es.uah.clienteCursosSeguro.model.Alumno;
import es.uah.clienteCursosSeguro.model.Curso;
import es.uah.clienteCursosSeguro.model.Matricula;
import es.uah.clienteCursosSeguro.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class MatriculasServiceImpl implements IMatriculasService {

    @Autowired
    RestTemplate restTemplateMicroUsuariosMatricula;

    @Autowired
    IAlumnosService alumnosService;

    @Autowired
    IUsuariosService usuariosService;

    @Autowired
    ICursosService cursosService;

    String url = "http://localhost:8090/api/usuarios/matriculas";

    @Override
    public Page<Matricula> buscarTodas(Pageable pageable) {
        Matricula[] matriculas = restTemplateMicroUsuariosMatricula.getForObject(url, Matricula[].class);
        List<Matricula> matriculasList = Arrays.asList(matriculas);

        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Matricula> list;

        if(matriculasList.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, matriculasList.size());
            list = matriculasList.subList(startItem, toIndex);
        }
        Page<Matricula> page = new PageImpl<>(list, PageRequest.of(currentPage, pageSize), matriculasList.size());
        return page;
    }

    @Override
    public Page<Matricula> buscarMatriculasPorIdCurso(Integer idCurso, Pageable pageable) {
        Matricula[] matriculas = restTemplateMicroUsuariosMatricula.getForObject(url+"/curso/"+idCurso, Matricula[].class);
        List<Matricula> matriculasList = Arrays.asList(matriculas);

        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Matricula>list;

        if(matriculasList.size() <startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, matriculasList.size());
            list = matriculasList.subList(startItem, toIndex);
        }
        Page<Matricula> page = new PageImpl<>(list, PageRequest.of(currentPage, pageSize), matriculasList.size());
        return page;
    }

    @Override
    public Matricula buscarMatriculaPorId(Integer idMatricula) {
        Matricula matricula = restTemplateMicroUsuariosMatricula.getForObject(url+"/"+idMatricula, Matricula.class);
        return matricula;
    }

    @Override
    public String guardarMatricula(Matricula matricula) {
        if (matricula.getIdMatricula() != null && matricula.getIdMatricula() > 0) {
            //template.put(url, matricula); //peligroso, habría que comprobar la inscripción anterior
            return "No se puede modificar una matrícula.";
        } else {
            //Inscribimos al alumno en el curso
            Usuario usuario = usuariosService.buscarUsuarioPorId(matricula.getUsuario().getIdUsuario());
            Alumno alumno = alumnosService.buscarAlumnoPorCorreo(usuario.getCorreo());
            Curso curso = cursosService.buscarCursoPorId(matricula.getIdCurso());
            String resultado="";
            //si no existe el alumno, lo creamos
            if(alumno == null) {
                alumno = new Alumno(usuario.getNombre(), usuario.getCorreo());
                alumnosService.guardarAlumno(alumno);
                resultado = "Alumno creado. ";
            } else { //si existe, comprobamos que no se matricula dos veces en el mismo curso
                resultado = "Alumno encontrado. ";
                List<Curso> cursos = alumno.getCursos();
                if (cursos.contains(curso)) {
                    return "El alumno ya existe en el curso!";
                }
            }
            alumnosService.inscribirCurso(alumno.getIdAlumno(), matricula.getIdCurso());
            //Guardamos la matrícula
            matricula.setPrecio(curso.getPrecio());
            matricula.setIdMatricula(0);
            matricula.setFecha(new Date());
            //template.postForLocation(url, matricula);
            restTemplateMicroUsuariosMatricula.postForObject(url, matricula, String.class);
            return resultado + "Los datos de la matricula fueron guardados!";
        }
    }

    @Override
    public void eliminarMatricula(Integer idMatricula) {
    	restTemplateMicroUsuariosMatricula.delete(url+ "/" +  idMatricula);
    }

}
