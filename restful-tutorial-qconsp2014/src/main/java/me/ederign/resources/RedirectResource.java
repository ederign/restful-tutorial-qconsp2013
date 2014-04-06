package me.ederign.resources;

import java.net.URI;
import java.net.URISyntaxException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import me.ederign.domain.PaginatedResource;
import me.ederign.domain.Pugs;
import me.ederign.repository.PugsRepository;

@Path("redirect")
public class RedirectResource {

    @GET
    public Response get( ) throws URISyntaxException {
        return Response.seeOther( new URI("/direct/") ).build();
    }

}
