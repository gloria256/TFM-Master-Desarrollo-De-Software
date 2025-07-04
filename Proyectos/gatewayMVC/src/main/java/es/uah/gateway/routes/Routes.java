package es.uah.gateway.routes;

import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import static org.springframework.cloud.gateway.server.mvc.filter.BeforeFilterFunctions.rewritePath;
import static org.springframework.cloud.gateway.server.mvc.filter.BeforeFilterFunctions.stripPrefix;

@Configuration
public class Routes {

    @Bean
    public RouterFunction<ServerResponse> cursosServiceRoute() {
        return GatewayRouterFunctions.route("servicio-cursos-alumnos")
                .route(RequestPredicates.path("/api/cursos/**"), HandlerFunctions.http("http://localhost:8001"))
                .before(stripPrefix(2))
                .before(rewritePath("%2520", " "))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> usuariosServiceRoute() {
        return GatewayRouterFunctions.route("servicio-usuarios-matriculas")
                .route(RequestPredicates.path("/api/usuarios/**"), HandlerFunctions.http("http://localhost:8002"))
                .before(stripPrefix(2))
                .build();
    }

}
