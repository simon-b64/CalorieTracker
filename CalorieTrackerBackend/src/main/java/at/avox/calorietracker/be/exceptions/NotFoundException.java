package at.avox.calorietracker.be.exceptions;

import jakarta.ws.rs.core.Response;

public class NotFoundException extends MappedBackendException {
    public NotFoundException(String message) {
        super(message, Response.Status.NOT_FOUND);
    }

    public NotFoundException(String message, Exception e) {
        super(message, Response.Status.NOT_FOUND, e);
    }
}
