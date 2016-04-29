
package csk.amusementpark.exception;

import csk.amusementpark.dto.ErrorDTO;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ValidationExceptionMapper implements ExceptionMapper<ValidationException>  {

    @Override
    public Response toResponse(ValidationException exception) {
        return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorDTO(exception.getLocalizedMessage())).type(MediaType.APPLICATION_JSON).build();
    }
    
}
