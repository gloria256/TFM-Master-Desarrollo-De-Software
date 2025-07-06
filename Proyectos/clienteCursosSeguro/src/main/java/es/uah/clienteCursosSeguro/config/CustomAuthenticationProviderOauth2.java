package es.uah.clienteCursosSeguro.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Component;

import es.uah.clienteCursosSeguro.model.Rol;
import es.uah.clienteCursosSeguro.model.Usuario;
import es.uah.clienteCursosSeguro.service.IRolesService;
import es.uah.clienteCursosSeguro.service.IUsuariosService;

//NOTA
/*
 * NOTA
 * implements OAuth2UserService<OAuth2UserRequest, OAuth2User>
 * 	ESTE SE USA CUANDO ES OAUTH2 PURO
 * implements OAuth2UserService<OidcUserRequest, OidcUser>
 * 	ESTE SE USA CUANDO ES POR PROVEEDOR COMO OAUTH0, GOOGLE ..ETC
 *  basicamente, porque ellos usan OpenID Connect (OIDC), que es una extensión sobre OAuth2.
 */
@Component
public class CustomAuthenticationProviderOauth2 implements OAuth2UserService<OidcUserRequest, OidcUser> {
	
	@Autowired
    private IUsuariosService usuariosService;
	
	@Autowired
    private IRolesService rolesService;

    @Override
    public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
        
    	OidcUserService delegate = new OidcUserService();
        OidcUser oidcUser = delegate.loadUser(userRequest);

        
        String correo = oidcUser.getAttribute("email"); // Cambia según el proveedor
        String nombre = oidcUser.getAttribute("name");
        String sub = oidcUser.getAttribute("sub");
        Usuario usuarioLogueado = usuariosService.buscarUsuarioPorCorreo(correo);
    	final List<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>();

        
        System.out.println("EMAIL OAUTH2");
        System.out.println("correo: " + correo);
        System.out.println("nombre: " + nombre);
        System.out.println("sub: " + sub);
        
        
        if (usuarioLogueado == null) {
        	Usuario usuario = new Usuario();
        	List<Rol> list = new ArrayList<>();
        	list.add(rolesService.buscarRolPorId(2));
        	
        	usuario.setCorreo(correo);
        	usuario.setNombre(nombre);
        	usuario.setRoles(list);
        	usuario.setSub(sub);
        	usuariosService.guardarUsuario(usuario);
        	
        	System.out.println("USUARIO GUARDADO CON EXITO");
        }else {
        	if(usuarioLogueado.getSub() == null) {
            	usuarioLogueado.setSub(sub);
            	usuariosService.guardarUsuario(usuarioLogueado);
            }
        	
            for (Rol rol : usuarioLogueado.getRoles()) {
                grantedAuths.add(new SimpleGrantedAuthority(rol.getAuthority()));
            }
            
        }
  
        
        final DefaultOidcUser auth2 = new DefaultOidcUser(
        		grantedAuths,
        		oidcUser.getIdToken(),
                oidcUser.getUserInfo()
        );
        
        System.out.println("OBJETO AUTENTICACION CON ROL");
        System.out.println(auth2);   
        
        
        return auth2;
            
    }
}
