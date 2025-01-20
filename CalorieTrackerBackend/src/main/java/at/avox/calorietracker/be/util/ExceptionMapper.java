package at.avox.calorietracker.be.util;

import at.avox.calorietracker.be.exceptions.InternalServerException;
import at.avox.calorietracker.be.exceptions.MappedBackendException;
import at.avox.calorietracker.be.web.dto.ErrorDto;
import io.quarkus.logging.Log;
import org.jboss.resteasy.reactive.RestResponse;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;

public class ExceptionMapper {
    @ServerExceptionMapper
    public RestResponse<ErrorDto> mapBackendException(MappedBackendException ex) {
        Log.warn(ex.getMessage(), ex);
        return mapBackendExceptionInternal(ex);
    }

    @ServerExceptionMapper
    public RestResponse<ErrorDto> mapInternalServerException(InternalServerException ex) {
        if(ex.getCause() != null) {
            Log.error(ex.getMessage(), ex.getCause());
        }
        return mapBackendExceptionInternal(ex);
    }

    private RestResponse<ErrorDto> mapBackendExceptionInternal(MappedBackendException ex) {
        return RestResponse.status(ex.getStatusType(), new ErrorDto(ex.getMessage()));
    }
}
