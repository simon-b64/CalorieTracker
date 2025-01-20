package at.avox.calorietracker.be.web;

import at.avox.calorietracker.be.service.CalorieEntryService;
import at.avox.calorietracker.be.web.dto.CalorieEntryDto;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import lombok.RequiredArgsConstructor;
import org.eclipse.microprofile.openapi.annotations.Operation;

import java.util.List;
import java.util.UUID;

@Path("/v1/calorie-entry")
@RequiredArgsConstructor
public class CalorieEntryResource {
    private final CalorieEntryService calorieEntryService;

    @GET
    @RolesAllowed("**")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(operationId = "getAllCalorieEntries")
    public List<CalorieEntryDto> getAll() {
        return calorieEntryService.findAll();
    }

    @PUT
    @RolesAllowed("**")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(operationId = "createOrUpdateCalorieEntry")
    public CalorieEntryDto createOrUpdate(
        @Valid @NotNull CalorieEntryDto calorieEntryDto
    ) {
        // TODO: Maybe change to return different response code dependent if created or updated
        return calorieEntryService.createOrUpdate(calorieEntryDto);
    }

    @DELETE
    @RolesAllowed("**")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(operationId = "deleteCalorieEntry")
    public CalorieEntryDto delete(
        @NotNull @QueryParam("id") UUID id
    ) {
        return calorieEntryService.delete(id);
    }

}
