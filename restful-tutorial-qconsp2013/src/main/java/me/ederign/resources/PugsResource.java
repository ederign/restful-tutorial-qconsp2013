package me.ederign.resources;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import me.ederign.domain.Pug;
import me.ederign.domain.Pugs;
import me.ederign.repository.PugsRepository;

@Path("pugs")
public class PugsResource {

    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response getAll() {
        Pugs allPugs = PugsRepository.findAll();
        return Response.ok( allPugs ).build();
    }

    @GET
    @Path("{id}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response getPugBy(@PathParam("id") String id) {
        Pug pug = PugsRepository.findById(id);
        return Response.ok( pug ).build();
    }

    @PUT
    @Path("{id}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response createPug(@PathParam("id") String id, Pug pug) {
       // Pug pug = PugsRepository.create(id);
        return Response.ok( pug ).build();
    }

}
