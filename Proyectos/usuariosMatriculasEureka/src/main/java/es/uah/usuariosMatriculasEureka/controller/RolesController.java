package es.uah.usuariosMatriculasEureka.controller;

import es.uah.usuariosMatriculasEureka.model.Rol;
import es.uah.usuariosMatriculasEureka.service.IRolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RolesController {

    @Autowired
    IRolesService rolesService;

    @GetMapping("/roles")
    public List<Rol> buscarTodos() {
        return rolesService.buscarTodos();
    }

    @GetMapping("/roles/{id}")
    public Rol buscarRolPorId(@PathVariable("id") Integer id) {
        return rolesService.buscarRolPorId(id);
    }

    @PostMapping("/roles")
    public void guardarRol(@RequestBody Rol rol) {
        rolesService.guardarRol(rol);
    }

    @DeleteMapping("/roles/{id}")
    public void eliminarRol(@PathVariable("id") Integer id) {
        rolesService.eliminarRol(id);
    }

}
