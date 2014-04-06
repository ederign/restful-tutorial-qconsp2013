package me.ederign.resources;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Random;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.EntityTag;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;

import me.ederign.domain.Conta;
import me.ederign.domain.Pug;
import me.ederign.repository.PugsRepository;

@Path("contas")
public class ContaResource {

    @GET
    @Path("{id}")
    @Produces({ MediaType.APPLICATION_JSON})
    public Response getPugBy( @PathParam("id") String id ) {
        Conta conta = new Conta(id, "Eder Ignatowicz");
        return Response.ok(conta).build();
    }
}
