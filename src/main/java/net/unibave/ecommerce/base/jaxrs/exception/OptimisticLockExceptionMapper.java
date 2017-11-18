package net.unibave.ecommerce.base.jaxrs.exception;

import javax.persistence.OptimisticLockException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import net.unibave.ecommerce.base.jaxrs.ExceptionMessage;

@Provider
public class OptimisticLockExceptionMapper implements ExceptionMapper<OptimisticLockException> {

    @Override
    public Response toResponse(OptimisticLockException exception) {
        System.out.println("Entrou no " + getClass().getSimpleName());
        return Response.status(Response.Status.CONFLICT)
                .entity(new ExceptionMessage("Registro com lock"))
                .build();
    }
    
}
