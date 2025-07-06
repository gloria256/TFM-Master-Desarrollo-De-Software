package es.uah.clienteCursosSeguro.service;

import es.uah.clienteCursosSeguro.model.Curso;
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
public class CursosServiceImpl implements ICursosService {

    @Autowired
    RestTemplate restTemplateMicroCursosAlumnos;

    String url = "http://localhost:8090/api/cursos/cursos";

    @Override
    public Page<Curso> buscarTodos(Pageable pageable) {
        Curso[] cursos = restTemplateMicroCursosAlumnos.getForObject(url, Curso[].class);
        List<Curso> cursosList = Arrays.asList(cursos);

        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Curso> list;

        if (cursosList.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, cursosList.size());
            list = cursosList.subList(startItem, toIndex);
        }

        Page<Curso> page = new PageImpl<>(list, PageRequest.of(currentPage, pageSize), cursosList.size());
        return page;
    }

    @Override
    public Curso buscarCursoPorId(Integer idCurso) {
        Curso curso = restTemplateMicroCursosAlumnos.getForObject(url + "/" + idCurso, Curso.class);
        return curso;
    }

    @Override
    public Page<Curso> buscarCursosPorNombre(String nombre, Pageable pageable) {
        Curso[] cursos = restTemplateMicroCursosAlumnos.getForObject(url + "/nombre/" + nombre, Curso[].class);
        List<Curso> cursosList= Arrays.asList(cursos);

        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;

        List<Curso> list;

        if (cursosList.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, cursosList.size());
            list = cursosList.subList(startItem, toIndex);
        }
        Page<Curso> page = new PageImpl<>(list, PageRequest.of(currentPage, pageSize), cursosList.size());
        return page;
    }

    @Override
    public Page<Curso> buscarCursosPorCategoria(String categoria, Pageable pageable) {
        Curso[] cursos = restTemplateMicroCursosAlumnos.getForObject(url + "/categoria/" + categoria, Curso[].class);
        List<Curso> categoriaList = Arrays.asList(cursos);
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;

        List<Curso> list;

        if (categoriaList.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, categoriaList.size());
            list = categoriaList.subList(startItem, toIndex);
        }
        Page<Curso> page = new PageImpl<>(list, PageRequest.of(currentPage, pageSize), categoriaList.size());
        return page;
    }

    @Override
    public Page<Curso> buscarCursosPorProfesor(String profesor, Pageable pageable) {
        Curso[] cursos = restTemplateMicroCursosAlumnos.getForObject(url + "/profesor/" + profesor, Curso[].class);
        List<Curso> profesList = Arrays.asList(cursos);
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;

        List<Curso> list;

        if (profesList.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, profesList.size());
            list = profesList.subList(startItem, toIndex);
        }
        Page<Curso> page = new PageImpl<>(list, PageRequest.of(currentPage, pageSize), profesList.size());
        return page;
    }

    @Override
    public void guardarCurso(Curso curso) {
        if (curso.getIdCurso() != null && curso.getIdCurso() > 0) {
        	restTemplateMicroCursosAlumnos.put(url, curso);
        } else {
            curso.setIdCurso(0);
            restTemplateMicroCursosAlumnos.postForObject(url, curso, String.class);
        }
    }

    @Override
    public void eliminarCurso(Integer idCurso) {
    	restTemplateMicroCursosAlumnos.delete(url + "/" + idCurso);
    }
}
