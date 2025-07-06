package es.uah.clienteCursosSeguro.service;

import es.uah.clienteCursosSeguro.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IUsuariosService {

    Page<Usuario> buscarTodos(Pageable pageable);

    Usuario buscarUsuarioPorId(Integer idUsuario);

    Usuario buscarUsuarioPorNombre(String nombre);

    Usuario buscarUsuarioPorCorreo(String correo);

    Usuario login(String correo, String sub);

    void guardarUsuario(Usuario usuario);

    void eliminarUsuario(Integer idUsuario);

}
