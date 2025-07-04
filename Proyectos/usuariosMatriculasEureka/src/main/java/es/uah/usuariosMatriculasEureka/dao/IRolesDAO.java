package es.uah.usuariosMatriculasEureka.dao;

import es.uah.usuariosMatriculasEureka.model.Matricula;
import es.uah.usuariosMatriculasEureka.model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IRolesDAO {

    List<Rol> buscarTodos();

    Rol buscarRolPorId(Integer idRol);

    void guardarRol(Rol rol);

    void eliminarRol(Integer idRol);

}
