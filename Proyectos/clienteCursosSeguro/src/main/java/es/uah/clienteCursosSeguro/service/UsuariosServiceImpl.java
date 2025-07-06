package es.uah.clienteCursosSeguro.service;

import es.uah.clienteCursosSeguro.model.Alumno;
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
import java.util.List;

@Service
public class UsuariosServiceImpl implements IUsuariosService {

    @Autowired
    RestTemplate restTemplateMicroUsuariosMatricula;

    @Autowired
    IAlumnosService alumnosService;

    String url = "http://localhost:8090/api/usuarios/usuarios";

    @Override
    public Page<Usuario> buscarTodos(Pageable pageable) {
        Usuario[] cursos = restTemplateMicroUsuariosMatricula.getForObject(url, Usuario[].class);
        List<Usuario> usuariosList = Arrays.asList(cursos);

        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Usuario> list;

        if (usuariosList.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, usuariosList.size());
            list = usuariosList.subList(startItem, toIndex);
        }

        Page<Usuario> page = new PageImpl<>(list, PageRequest.of(currentPage, pageSize), usuariosList.size());
        return page;
    }

    @Override
    public Usuario buscarUsuarioPorId(Integer idUsuario) {
        Usuario usuario = restTemplateMicroUsuariosMatricula.getForObject(url + "/" + idUsuario, Usuario.class);
        return usuario;
    }

    @Override
    public Usuario buscarUsuarioPorNombre(String nombre) {
        Usuario usuario = restTemplateMicroUsuariosMatricula.getForObject(url+"/nombre/"+nombre, Usuario.class);
        return usuario;
    }

    @Override
    public Usuario buscarUsuarioPorCorreo(String correo) {
        Usuario usuario = restTemplateMicroUsuariosMatricula.getForObject(url+"/correo/"+correo, Usuario.class);
        return usuario;
    }

    @Override
    public Usuario login(String correo, String sub) {
        Usuario usuario = restTemplateMicroUsuariosMatricula.getForObject(url+"/login/"+correo+"/"+sub, Usuario.class);
        return usuario;
    }

    @Override
    public void guardarUsuario(Usuario usuario) {
        if (usuario.getIdUsuario() != null && usuario.getIdUsuario() > 0) {
        	restTemplateMicroUsuariosMatricula.put(url, usuario);
        } else {
            usuario.setIdUsuario(0);
            restTemplateMicroUsuariosMatricula.postForObject(url, usuario, String.class);
            Alumno alumno = new Alumno(usuario.getNombre(), usuario.getCorreo());
            alumnosService.guardarAlumno(alumno);
        }
    }

    @Override
    public void eliminarUsuario(Integer idUsuario) {
    	restTemplateMicroUsuariosMatricula.delete(url+"/"+idUsuario);
    }

 }
