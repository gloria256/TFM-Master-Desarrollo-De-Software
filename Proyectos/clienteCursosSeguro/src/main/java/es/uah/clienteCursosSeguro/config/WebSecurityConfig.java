package es.uah.clienteCursosSeguro.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    
    //definición roles y usuarios autenticacion oauth2
    @Autowired
    private CustomAuthenticationProviderOauth2 authProviderOauth2;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http

		        .authorizeHttpRequests((authz) -> authz
		                .requestMatchers("/js/**", "/css/**", "/login/").permitAll()
		                .anyRequest().authenticated()
		        )
        		.oauth2Login(oauth -> oauth
        				.loginPage("/oauth2/authorization/auth0")
        				.defaultSuccessUrl("/ccursos", true)// <- Aquí rediriges después del login
        				.userInfoEndpoint(userInfo -> userInfo
        						.oidcUserService(authProviderOauth2)
        	            )
        				.failureHandler((request, response, exception) -> {
        			        //exception.printStackTrace(); // log interno
        			        String errorMessage = exception.getMessage();
        			        
        			        System.out.println("EXCEPTION");
        		            System.out.println(errorMessage);        		            
        		            
        		            //si el acceso es denegado se redirige al login
                            if (errorMessage != null && errorMessage.contains("access_denied")) {   
                                response.sendRedirect("https://dev-6y8z2xiaa8p2o4u6.us.auth0.com/v2/logout?client_id=IdPhAzCcvsG6YbdiaPKhXFIgSoY1GOUZ&returnTo=http://localhost:9000");
                            }
        			    })
        		)
        		.logout(logout -> logout
        			.logoutSuccessUrl("https://dev-6y8z2xiaa8p2o4u6.us.auth0.com/v2/logout?client_id=IdPhAzCcvsG6YbdiaPKhXFIgSoY1GOUZ&returnTo=http://localhost:9000")   // Redirige a la página de login local después de un logout exitoso
        			.invalidateHttpSession(true)  // Invalida la sesión si está presente
	        		.clearAuthentication(true)    // Limpia la autenticación si está presente
	        		.deleteCookies("JSESSIONID")  // Borra la cookie si está presente (aunque no siempre estará)
        		);
 
                
        return http.build();
    }

}

