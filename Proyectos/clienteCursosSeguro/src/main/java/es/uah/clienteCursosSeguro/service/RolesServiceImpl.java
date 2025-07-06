package es.uah.clienteCursosSeguro.service;

import es.uah.clienteCursosSeguro.model.Rol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class RolesServiceImpl implements IRolesService {

    @Autowired
    RestTemplate restTemplateMicroUsuariosMatricula;

    String url = "http://localhost:8090/api/usuarios/roles";

    @Override
    public List<Rol> buscarTodos() {
        Rol[] roles =restTemplateMicroUsuariosMatricula.getForObject(url, Rol[].class);
        return Arrays.asList(roles);
    }

    @Override
    public Rol buscarRolPorId(Integer idRol) {
        Rol rol = restTemplateMicroUsuariosMatricula.getForObject(url+"/"+idRol, Rol.class);
        return rol;
    }

    @Override
    public void guardarRol(Rol rol) {
        if (rol.getIdRol() != null && rol.getIdRol() > 0) {
        	restTemplateMicroUsuariosMatricula.put(url, rol);
        } else {
            rol.setIdRol(0);
            restTemplateMicroUsuariosMatricula.postForObject(url, rol, String.class);
        }
    }

    @Override
    public void eliminarRol(Integer idRol) {
    	restTemplateMicroUsuariosMatricula.delete(url + "/" + idRol);
    }
}
