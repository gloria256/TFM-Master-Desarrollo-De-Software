package es.uah.clienteCursosSeguro.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

//Es necesario sí o sí volver a generar un token de acceso para 
//cada request a cada micro, pues el audience es distinto
//y no es posible modificarlo en el token una vez generado

@Component
public class TokenProviderOauth2 {

    @Value("${spring.security.oauth2.client.registration.auth0.client-id}")
    private String clientId;

    @Value("${spring.security.oauth2.client.registration.auth0.client-secret}")
    private String clientSecret;

    @Value("${spring.security.oauth2.client.provider.auth0.token-uri}")
    private String tokenUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    public String getTokenForAudience(String audience) {
    	
    	System.out.println("XXXXXXXX-TOKEN ACCESS CLIENT CREDENCIAL");
    	
        Map<String, String> body = new HashMap<>();
        body.put("client_id", clientId);
        body.put("client_secret", clientSecret);
        body.put("audience", audience);
        body.put("grant_type", "client_credentials");
        body.put("scope", "read");

        return this.TokenRequest(body);
    }
    
    private String TokenRequest(Map<String, String> body) {
        
        try {
        	HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<Map<String, String>> entity = new HttpEntity<>(body, headers);
            ResponseEntity<Map> response = restTemplate.postForEntity(tokenUrl, entity, Map.class);
            
            System.out.println(response.getBody().get("access_token").toString());
            
            return response.getBody().get("access_token").toString();

        } catch (RestClientException e) {
            throw new RuntimeException("Error al solicitar token de acceso OAuth2: " + e.getMessage(), e);
        }
    }
}
