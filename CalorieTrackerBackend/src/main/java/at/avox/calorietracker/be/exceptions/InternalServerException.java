package at.avox.calorietracker.be.exceptions;

import jakarta.ws.rs.core.Response;

public class InternalServerException extends MappedBackendException {
    public InternalServerException() {
        super("Something went wrong...", Response.Status.INTERNAL_SERVER_ERROR);
    }

    public InternalServerException(Exception e) {
        super("Something went wrong...", Response.Status.INTERNAL_SERVER_ERROR, e);
    }

    public InternalServerException(String message) {
        super(message, Response.Status.INTERNAL_SERVER_ERROR);
    }

    public InternalServerException(String message, Exception e) {
        super(message, Response.Status.INTERNAL_SERVER_ERROR, e);
    }
}
