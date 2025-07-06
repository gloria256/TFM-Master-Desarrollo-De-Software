package es.uah.clienteCursosSeguro.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.security.Principal;

@Controller
public class LoginController {
	
	@Value("${app.base-url}")
    private String baseUrl;
	
	@Value("${app.logout-url}")
	private String logOutUrl;
	
	@Value("${spring.security.oauth2.client.registration.auth0.client-id}")
    private String clientId;


	@GetMapping(value = {"/", "/login", ""})
    public String login(@RequestParam(value = "error", required = false) String error, Model model, Principal principal) {
    	
        if (principal != null) {
            return "redirect:/ccursos";
        }

        if (error != null) {
            model.addAttribute("msg",
                    "Error al iniciar sesión: Nombre de usuario o contraseña incorrecta, por favor vuelva a intentarlo!");
        }

        return "redirect:/logoutClient";
    }


    //@GetMapping("/logoutClient")
	@RequestMapping("/logoutClient")
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) throws ServletException {       
        System.out.println("XXXXXXXX-logouthClient");
        String logoutUrl = ""; 
        
    	request.logout();
        logoutUrl = logOutUrl + "?client_id=" + clientId + "&returnTo=" + baseUrl;
        return "redirect:" + logoutUrl;
    }
}
