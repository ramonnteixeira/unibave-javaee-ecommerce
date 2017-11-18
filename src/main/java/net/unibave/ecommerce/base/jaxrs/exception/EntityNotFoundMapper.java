package net.unibave.ecommerce.base.jaxrs.exception;

import javax.persistence.EntityNotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import net.unibave.ecommerce.base.jaxrs.ExceptionMessage;

@Provider
public class EntityNotFoundMapper implements ExceptionMapper<EntityNotFoundException> {

    @Override
    public Response toResponse(EntityNotFoundException exception) {
        System.out.println("Entrou no " + getClass().getSimpleName());
        return Response.status(Response.Status.NOT_FOUND)
                .entity(new ExceptionMessage(exception.getMessage()))
                .build();
    }
    
}
