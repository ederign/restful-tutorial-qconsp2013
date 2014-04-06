package me.ederign.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import me.ederign.domain.Ship;

@Path("ship")
public class ShipResource {

    @GET
    @Path("{id}")
    @Produces({ MediaType.APPLICATION_JSON})
    public Response getTitular( @PathParam("id") String id ) {
        Ship ship = new Ship("1", "Campinas" );
        return Response.ok(ship).build();
    }
}
