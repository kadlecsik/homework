package csk.mobilewebshop.exception;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ValidationExceptionMapper implements ExceptionMapper<ValidationException> {

    @Inject
    private Logger logger;

    @Override
    public Response toResponse(ValidationException exception) {

        logger.log(Level.FINER, "Bean validation exception ", exception);
        return Response.status(Response.Status.BAD_REQUEST).entity(
                new ExceptionDTO(exception.getMessage())
        ).type(MediaType.APPLICATION_JSON).build();

    }
}
