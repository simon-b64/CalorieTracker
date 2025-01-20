package at.avox.calorietracker.be.exceptions;

import jakarta.ws.rs.core.Response;
import lombok.Getter;

@Getter
public class MappedBackendException extends RuntimeException {
    private final transient Response.StatusType statusType;

    public MappedBackendException(String message, Response.StatusType statusType) {
        this(message, statusType, null);
    }

    public MappedBackendException(String message, Response.StatusType statusType, Exception cause) {
        super(message, cause);
        this.statusType = statusType;
    }
}

