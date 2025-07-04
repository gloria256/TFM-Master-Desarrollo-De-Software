package es.uah.usuariosMatriculasEureka.dao;

import es.uah.usuariosMatriculasEureka.model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRolesJPA extends JpaRepository<Rol, Integer> {
}
