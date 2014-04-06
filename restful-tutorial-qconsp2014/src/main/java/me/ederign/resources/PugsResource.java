package me.ederign.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import me.ederign.domain.PaginatedResource;
import me.ederign.domain.Pugs;
import me.ederign.repository.PugsRepository;

@Path("pugs")
public class PugsResource {

    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response getPaginated( @QueryParam("offset") String offset,
                                  @QueryParam("limit") String limit ) {
        if ( noParameter( offset, limit ) ) {
            Pugs allPugs = PugsRepository.findAll();
            return Response.ok( allPugs ).build();
        }

        PaginatedResource paginatedResource = PugsRepository.findByFilter( offset, limit );
        return Response.ok( paginatedResource ).build();
    }

    private boolean noParameter( String offset,
                                 String limit ) {
        return offset == null || limit == null;
    }

}
