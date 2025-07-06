package es.uah.cursosAlumnosEureka.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimValidator;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtValidators;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

import com.nimbusds.jose.JOSEObjectType;
import com.nimbusds.jose.proc.DefaultJOSEObjectTypeVerifier;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {	
	
	@Value("${spring.security.oauth2.resourceserver.jwt.jwk-set-uri}")
    private String jwks_uri;
	
	@Value("${spring.security.oauth2.resourceserver.jwt.audiences}")
    private String audience;
	
	@Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
    private String issuer_uri;
	
	@Bean
    public JwtDecoder jwtDecoder() {
		/*
		 * Se valida en customizer.setJWSTypeVerifier(...):
		 * 		toma automáticamente el algoritmo que se tenga configurado de Json de la url
		 * 		El algoritmo de firma (implícitamente si no lo configuras),
		 * 		La validez temporal del token (iat, exp, etc.),
		 * 		Y el tipo de token (typ = at+jwt) por medio de tu línea:
		 */
		NimbusJwtDecoder decoder = NimbusJwtDecoder.withJwkSetUri(jwks_uri)
        		.jwtProcessorCustomizer(customizer -> {
                    customizer.setJWSTypeVerifier(new DefaultJOSEObjectTypeVerifier<>(new JOSEObjectType("at+jwt")));
                })
                .build();
		
        
		/*
		 * Se valida que el audience y el issuer_uri corresponda al configurado
		 */
        OAuth2TokenValidator<Jwt> withIssuer = JwtValidators.createDefaultWithIssuer(issuer_uri);
        OAuth2TokenValidator<Jwt> withAudience = new JwtClaimValidator<List<String>>("aud", aud -> aud != null && aud.contains(audience));        
        
        OAuth2TokenValidator<Jwt> validator = new DelegatingOAuth2TokenValidator<>(withIssuer, withAudience);
        decoder.setJwtValidator(validator);

        return decoder;
    }
	
 
	
	@Bean
	public JwtAuthenticationConverter jwtAuthenticationConverter() {
		//Mapea el scope como grantAutorities, para poder usar hasAuthority("SCOPE_read")
	    JwtGrantedAuthoritiesConverter grantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
	    grantedAuthoritiesConverter.setAuthoritiesClaimName("scope");
	    grantedAuthoritiesConverter.setAuthorityPrefix("SCOPE_");

	    JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
	    jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(grantedAuthoritiesConverter);
	    
	    return jwtAuthenticationConverter;
	}
	


	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/alumnos/**","/cursos/**").hasAuthority("SCOPE_read")
                .anyRequest().authenticated()
            )
        	.oauth2ResourceServer(oauth2 -> oauth2
        			.jwt(jwt ->jwt
        					.decoder(jwtDecoder())
        					.jwtAuthenticationConverter(jwtAuthenticationConverter())		
        			)
        	); 
        
        return http.build();
    }

}
