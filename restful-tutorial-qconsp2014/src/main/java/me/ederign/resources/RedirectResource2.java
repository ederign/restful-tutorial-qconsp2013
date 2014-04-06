package me.ederign.resources;

import java.net.URISyntaxException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("direct")
public class RedirectResource2 {

    @GET
    public Response get( ) throws URISyntaxException {
        return Response.ok( "Direct Resource" ).build();
    }

}
