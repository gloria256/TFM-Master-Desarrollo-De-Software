package es.uah.clienteCursosSeguro.service;

import es.uah.clienteCursosSeguro.model.Alumno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class AlumnosServiceImpl implements IAlumnosService {

    @Autowired
    RestTemplate restTemplateMicroCursosAlumnos;

    String url = "http://localhost:8090/api/cursos/alumnos";

    @Override
    public Page<Alumno> buscarTodos(Pageable pageable) {
        Alumno[] alumnos = restTemplateMicroCursosAlumnos.getForObject(url, Alumno[].class);
        List<Alumno> alumnosList = Arrays.asList(alumnos);

        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Alumno> list;

        if(alumnosList.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, alumnosList.size());
            list = alumnosList.subList(startItem, toIndex);
        }
        Page<Alumno> page = new PageImpl<>(list, PageRequest.of(currentPage, pageSize), alumnosList.size());
        return page;
    }

    @Override
    public Alumno buscarAlumnoPorId(Integer idAlumno) {
        Alumno alumno = restTemplateMicroCursosAlumnos.getForObject(url+"/"+idAlumno, Alumno.class);
        return alumno;
    }

    @Override
    public Alumno buscarAlumnoPorCorreo(String correo) {
        Alumno alumno = restTemplateMicroCursosAlumnos.getForObject(url+"/correo/"+correo, Alumno.class);
        return alumno;
    }

    @Override
    public void guardarAlumno(Alumno alumno) {
        if (alumno.getIdAlumno() != null && alumno.getIdAlumno() > 0) {
        	restTemplateMicroCursosAlumnos.put(url, alumno);
        } else {
            alumno.setIdAlumno(0);
            restTemplateMicroCursosAlumnos.postForObject(url, alumno, String.class);
        }
    }

    @Override
    public void eliminarAlumno(Integer idAlumno) {
    	restTemplateMicroCursosAlumnos.delete(url + "/" + idAlumno);
    }

    @Override
    public void inscribirCurso(Integer idAlumno, Integer idCurso) {
    	restTemplateMicroCursosAlumnos.getForObject(url+"/insc/"+idAlumno+"/"+idCurso, String.class);
    }
}
