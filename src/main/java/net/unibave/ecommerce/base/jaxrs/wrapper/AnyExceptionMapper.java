package net.unibave.ecommerce.base.jaxrs.wrapper;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.Providers;
import net.unibave.ecommerce.base.jaxrs.ExceptionMessage;

@Provider
public class AnyExceptionMapper implements ExceptionMapper<Exception> {

    @Context
    private Providers providers;
    
    @Override
    public Response toResponse(Exception e) {
        System.out.println("Entrou no " + getClass().getSimpleName());
        if (e.getCause() == null) {
            return Response.serverError().build();
        }
        
        ExceptionMapper exceptionMapper = providers.getExceptionMapper(e.getCause().getClass());
        
        if (exceptionMapper == null) {
            return Response.serverError()
                    .entity(new ExceptionMessage(e.getCause().getMessage()))
                    .build();            
        }
        
        return exceptionMapper.toResponse(e.getCause());
    }
    
}
