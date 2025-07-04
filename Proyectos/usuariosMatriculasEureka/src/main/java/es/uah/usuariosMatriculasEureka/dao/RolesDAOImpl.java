package es.uah.usuariosMatriculasEureka.dao;

import es.uah.usuariosMatriculasEureka.model.Rol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class RolesDAOImpl implements IRolesDAO {

    @Autowired
    IRolesJPA rolJPA;

    @Override
    public List<Rol> buscarTodos() {
        return rolJPA.findAll();
    }

    @Override
    public Rol buscarRolPorId(Integer idRol) {
        Optional<Rol> optional = rolJPA.findById(idRol);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;

    }

    @Override
    public void guardarRol(Rol rol) {
        rolJPA.save(rol);

    }

    @Override
    public void eliminarRol(Integer idRol) {
        rolJPA.deleteById(idRol);
    }

}
