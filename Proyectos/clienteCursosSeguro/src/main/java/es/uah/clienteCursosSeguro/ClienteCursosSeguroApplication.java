package es.uah.clienteCursosSeguro;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import es.uah.clienteCursosSeguro.config.TokenProviderOauth2;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class ClienteCursosSeguroApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClienteCursosSeguroApplication.class, args);
	}

	@Bean
	public RestTemplate template() {
		RestTemplate template = new RestTemplate();
		return template;
	}
	
	@Bean
	public RestTemplate restTemplateMicroUsuariosMatricula(TokenProviderOauth2 tokenProvider) {
	    RestTemplate template = new RestTemplate();
	    template.setInterceptors(List.of((request, body, execution) -> {
	        String token = tokenProvider.getTokenForAudience("api-micro-usuarios-matricula");
	        request.getHeaders().setBearerAuth(token);
	        
	     // === IMPRIMIR CABECERAS ===
	        System.out.println(">>> RestTemplate - MicroUsuariosMatricula");
	        System.out.println("Request URI: " + request.getURI());
	        System.out.println("Method: " + request.getMethod());
	        request.getHeaders().forEach((key, values) -> {
	            for (String value : values) {
	                System.out.println("Header: " + key + " = " + value);
	            }
	        });
	        System.out.println("=========================");
	        
	        return execution.execute(request, body);
	    }));        
	    return template;
	}
	
	@Bean
	public RestTemplate restTemplateMicroCursosAlumnos(TokenProviderOauth2 tokenProvider) {
	    RestTemplate template = new RestTemplate();
	    template.setInterceptors(List.of((request, body, execution) -> {
	        String token = tokenProvider.getTokenForAudience("api-micro-cursos-alumnos");
	        request.getHeaders().setBearerAuth(token);
	        
	     // === IMPRIMIR CABECERAS ===
	        System.out.println(">>> RestTemplate - MicroCursosAlumnos");
	        System.out.println("Request URI: " + request.getURI());
	        System.out.println("Method: " + request.getMethod());
	        request.getHeaders().forEach((key, values) -> {
	            for (String value : values) {
	                System.out.println("Header: " + key + " = " + value);
	            }
	        });
	        System.out.println("=========================");
	        
	        return execution.execute(request, body);
	    }));        
	    return template;
	}
}
