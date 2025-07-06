package es.uah.usuariosMatriculasEureka.dao;

import es.uah.usuariosMatriculasEureka.model.Usuario;

import java.util.List;

public interface IUsuariosDAO {

    List<Usuario> buscarTodos();

    Usuario buscarUsuarioPorId(Integer idUsuario);

    Usuario buscarUsuarioPorNombre(String nombre);

    Usuario buscarUsuarioPorCorreo(String correo);

    Usuario buscarUsuarioPorCorreoSub(String correo, String sub);

    void guardarUsuario(Usuario usuario);

    void eliminarUsuario(Integer idUsuario);

    void actualizarUsuario(Usuario usuario);

    void eliminarMatricula(Integer idUsuario, Integer idMatricula);

}
