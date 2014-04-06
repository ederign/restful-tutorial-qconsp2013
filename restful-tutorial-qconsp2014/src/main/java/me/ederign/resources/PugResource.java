package me.ederign.resources;

import java.net.URI;
import java.net.URISyntaxException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.EntityTag;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;

import me.ederign.domain.Pug;
import me.ederign.domain.Pugs;
import me.ederign.repository.PugsRepository;

@Path("pugs")
public class PugResource {

    @GET
    @Path("{id}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response getPugBy( @PathParam("id") String id,
                              @Context Request request ) {
        Pug pug = PugsRepository.findById( id );
        if ( pug == null ) {
            return Response.status( 404 ).build();
        }

        //etag for this resource
        EntityTag etag = new EntityTag( Integer.toString( pug.hashCode() ) );

        //evaluate cache
        Response.ResponseBuilder builder = request.evaluatePreconditions( etag );

        // cached resource did change -> serve updated content
        if ( builder == null ) {
            builder = Response.ok( pug );
            builder.tag( etag );
        }
        return builder.build();
    }

    @PUT
    @Path("{id}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createOrUpdatePug( @PathParam("id") String id,
                                       Pug pug,
                                       @Context Request request ) throws URISyntaxException {

        Pug actualPug = PugsRepository.findById( id );
        Response.ResponseBuilder builder = null;
        if ( putIsAUpdate( actualPug ) ) {
            EntityTag etag = new EntityTag( Integer.toString( actualPug.hashCode() ) );
            builder = request.evaluatePreconditions( etag );
            // client is not up to date (send back 412)
            if ( builder != null ) {
                return builder.build();
            }
            PugsRepository.update( pug );
        } else {
            PugsRepository.create(id, pug );
        }
        builder = Response.created( new URI( "/pugs/" + id ) );
        return builder.build();
    }

    private boolean putIsAUpdate( Pug actualPug ) {
        return actualPug != null;
    }

    @POST
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create( Pug pug ) throws URISyntaxException {
        Pug newPug = PugsRepository.create( pug );
        //Assync? return 202 (Accepted)
        return Response.created( new URI( "/pugs/" + newPug.getId() ) ).build();
    }

    @DELETE
    @Path("{id}")
    public Response delete( @PathParam("id") Integer id ) throws URISyntaxException {
        PugsRepository.delete( id );
        //HTTP 204 No Content
        return Response.ok().build();
    }

}
