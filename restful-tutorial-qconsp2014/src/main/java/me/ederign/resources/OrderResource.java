package me.ederign.resources;

import java.net.URI;
import java.net.URISyntaxException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import me.ederign.domain.Order;
import me.ederign.domain.Pug;

@Path("order")
public class OrderResource {

    @GET
    @Path("{id}")
    @Produces({ MediaType.APPLICATION_JSON })
    public Response getOrder( @PathParam("id") String id ) throws URISyntaxException {
        Order order = new Order( "1", "Eder Ignatowicz" );
        return Response.ok( order ).link("/ship/1", "ship" ).link("/another/1", "anotherLink" ).build();
    }

}
