package at.avox.calorietracker.be.web;

import at.avox.calorietracker.be.service.SecurityService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import lombok.RequiredArgsConstructor;

@Path("/v1/hello")
@RequiredArgsConstructor
public class ExampleResource {
    private final SecurityService securityService;

    @GET
    @RolesAllowed("**")
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello from Quarkus REST subject " + securityService.getCurrentUser().getName();
    }
}
