package me.ederign.resources;

import java.net.URI;
import java.net.URISyntaxException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.EntityTag;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import me.ederign.domain.Pug;
import me.ederign.domain.Pugs;
import me.ederign.domain.Ship;
import me.ederign.infra.Main;
import me.ederign.repository.PugsRepository;
import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class OrderResourceTest {

    private HttpServer server;
    private WebTarget target;

    @Before
    public void setUp() throws Exception {
        server = Main.startServer();
        Client c = ClientBuilder.newClient();
        target = c.target( Main.BASE_URI );
    }

    @After
    public void tearDown() throws Exception {
        server.stop();
    }

    @Test
    public void testGetLink() {
        Invocation.Builder request = target.path( "order/1" ).request();
        Response order = request.get();
        if ( order.getLink( "ship" ) != null ) {
            Link ship = order.getLink( "ship" );
            assertEquals( "http://localhost:8080/ship/1", ship.getUri().toString() );
        } else {
            Assert.fail();
        }
    }

}
