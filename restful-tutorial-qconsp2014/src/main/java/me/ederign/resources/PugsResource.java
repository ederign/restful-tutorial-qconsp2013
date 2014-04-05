package me.ederign.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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

}
