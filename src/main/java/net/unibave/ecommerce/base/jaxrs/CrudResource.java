package net.unibave.ecommerce.base.jaxrs;

import net.unibave.ecommerce.EntityId;
import net.unibave.ecommerce.base.AbstractRepository;
import net.unibave.ecommerce.base.AbstractService;

import javax.inject.Inject;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
public abstract class CrudResource<T extends EntityId<PK>, PK> {

    @Inject
    private AbstractRepository<T, PK> repository;

    @Inject
    private AbstractService<T, PK> service;

    @GET
    public Response findAll() {
        List<T> entities = repository.findAll();
        return Response.ok(entities).build();
    }

    @GET
    @Path("{id}")
    public Response find(@PathParam("id") PK id) {
        T entity = repository.find(id);
        return Response.ok(entity).build();
    }

    @POST
    public Response create(T entity, @Context UriInfo info) {
        T entity_ = service.save(entity);
        UriBuilder builder = info.getAbsolutePathBuilder();
        builder.path(entity_.getId().toString());
        return Response.created(builder.build())
                .entity(entity_)
                .build();
    }

    @PUT
    public Response update(T entity) {
        service.save(entity);
        return Response.noContent().build();
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") PK id) {
        service.delete(id);
        return Response.noContent().build();
    }

}
