package es.uah.usuariosMatriculasEureka.dao;

import es.uah.usuariosMatriculasEureka.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UsuariosDAOImpl implements IUsuariosDAO {

    @Autowired
    IUsuariosJPA usuariosJPA;

    @Autowired
    IMatriculasJPA matriculasJPA;

    @Override
    public List<Usuario> buscarTodos() {
        return usuariosJPA.findAll();
    }

    @Override
    public Usuario buscarUsuarioPorId(Integer idUsuario) {
        Optional<Usuario> optional = usuariosJPA.findById(idUsuario);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    @Override
    public Usuario buscarUsuarioPorNombre(String nombre) {
        Optional<Usuario> optional = Optional.ofNullable(usuariosJPA.findByNombre(nombre));
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    @Override
    public Usuario buscarUsuarioPorCorreo(String correo) {
        Optional<Usuario> optional = Optional.ofNullable(usuariosJPA.findByCorreo(correo));
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    @Override
    public Usuario buscarUsuarioPorCorreoClave(String correo, String clave) {
        Optional<Usuario> optional = Optional.ofNullable(usuariosJPA.findByCorreoAndClave(correo, clave));
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;

    }

    @Override
    public void guardarUsuario(Usuario usuario) {
        usuariosJPA.save(usuario);
    }

    @Override
    public void eliminarUsuario(Integer idUsuario) {
        usuariosJPA.deleteById(idUsuario);
    }

    @Override
    public void actualizarUsuario(Usuario usuario) {
        usuariosJPA.save(usuario);
    }

    @Override
    public void eliminarMatricula(Integer idUsuario, Integer idMatricula) {
        Usuario usuario = buscarUsuarioPorId(idUsuario);
        if (usuario != null) {
            usuario.removeMatricula(matriculasJPA.findById(idMatricula).orElse(null));
        }
        matriculasJPA.deleteById(idMatricula);
    }
}
