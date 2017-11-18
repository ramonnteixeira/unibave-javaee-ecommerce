package net.unibave.ecommerce.base.jaxrs.exception;

import java.util.HashMap;
import java.util.Map;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ConstraintViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

    @Override
    public Response toResponse(ConstraintViolationException exception) {
        System.out.println("Entrou no " + getClass().getSimpleName());
        Map<String, String> map = new HashMap<>();
        exception.getConstraintViolations()
                .stream()
                .forEach(e -> map.put(e.getRootBeanClass().getSimpleName() + "." + e.getPropertyPath().toString(), e.getMessage()));
        
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(map)
                .build();
    }
    
}
