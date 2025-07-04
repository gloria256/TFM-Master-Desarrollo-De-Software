package es.uah.usuariosMatriculasEureka.dao;

import es.uah.usuariosMatriculasEureka.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUsuariosJPA extends JpaRepository<Usuario, Integer> {

    Usuario findByNombre(String nombre);

    Usuario findByCorreo(String correo);

    Usuario findByCorreoAndClave(String correo, String clave);

}
