package me.ederign.resources;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import me.ederign.domain.Pug;
import me.ederign.domain.Pugs;
import me.ederign.infra.Main;
import me.ederign.repository.PugsRepository;
import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class PugsResourceTest {

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
    public void testGetPugs() {
        Pugs actual = target.path( "pugs" ).request().get( Pugs.class );
        Pugs expected = PugsRepository.findAll();
        assertEquals( expected.numberOfPugs(), actual.numberOfPugs() );
    }

    @Test
    public void testGetXMLPugs() {
        Response pugs = target.path( "pugs" ).request().accept( MediaType.APPLICATION_XML ).get();
        assertEquals( MediaType.APPLICATION_XML, pugs.getMediaType().toString() );
    }

    @Test
    public void testGetJsonPugs() {
        Response pugs = target.path( "pugs" ).request().accept( MediaType.APPLICATION_JSON ).get();
        assertEquals( MediaType.APPLICATION_JSON, pugs.getMediaType().toString() );
    }

    @Test
    public void testGetPugById() {
        Pug actual = target.path( "pugs/1" ).request().get( Pug.class );
        assertEquals( "1", actual.getId() );
    }

}
