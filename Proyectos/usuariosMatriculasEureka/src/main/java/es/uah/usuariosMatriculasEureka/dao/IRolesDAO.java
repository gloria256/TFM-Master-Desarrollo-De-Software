package es.uah.usuariosMatriculasEureka.dao;

import es.uah.usuariosMatriculasEureka.model.Rol;

import java.util.List;

public interface IRolesDAO {

    List<Rol> buscarTodos();

    Rol buscarRolPorId(Integer idRol);

    void guardarRol(Rol rol);

    void eliminarRol(Integer idRol);

}
