package me.ederign.resources;

import java.net.URI;
import java.net.URISyntaxException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.EntityTag;
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

    @Test
    public void createPugByPut() throws URISyntaxException {
        Pug pug = new Pug( 1, "Dorinha", 10, new URI( "http://api.puglovers.com/pugs/1" ) );
        Pug pugs = target.path( "pugs/1" ).request().put( Entity.json( pug ), Pug.class );
    }

    @Test
    public void deletePug() throws URISyntaxException {
        Pug pug = new Pug( 1, "Dorinha", 10, new URI( "http://api.puglovers.com/pugs/1" ) );
        Response get = target.path( "pugs/1" ).request().get();
        assertEquals( 200, get.getStatus() );
        Response delete = target.path( "pugs/1" ).request().delete();
        assertEquals( 200, delete.getStatus() );
        get = target.path( "pugs/1" ).request().get();
        assertEquals( 404, get.getStatus() );
    }

    @Test
    public void testCacheEtags() {
        Response response200 = target.path( "pugs/1" ).request().accept( MediaType.APPLICATION_JSON ).get();
        EntityTag entityTag = response200.getEntityTag();
        assertEquals( 200, response200.getStatus() );

        //Allows a 304. Not Modified to be returned if content is unchanged
        Response response304 = target.path( "pugs/1" ).request().header( "If-None-Match", entityTag ).get();
        assertEquals( 304, response304.getStatus() );

        response200 = target.path( "pugs/1" ).request().header( "If-None-Match", new EntityTag( "-1" ) ).get();
        assertEquals( 200, response200.getStatus() );
    }

    @Test
    public void conditionalPut() throws URISyntaxException {
        Pug originalPug = new Pug( 1, "Dorinha", 10, new URI( "http://api.puglovers.com/pugs/1" ) );
        Response responsePut = target.path( "pugs/1" ).request().put( Entity.json( originalPug ) );
        assertEquals( 201, responsePut.getStatus() );

        Response response200 = target.path( "pugs/1" ).request().accept( MediaType.APPLICATION_JSON ).get();
        EntityTag originalTag = response200.getEntityTag();
        assertEquals( 200, response200.getStatus() );

        //another concurrent request with right tag
        Pug concurrentPug = new Pug( 1, "Slim Dorinha", 11, new URI( "http://api.puglovers.com/pugs/1" ) );
        Response responsePutSlim = target.path( "pugs/1" ).request().header( "If-Match", originalTag ).put( Entity.json( concurrentPug ) );
        assertEquals( 201, responsePutSlim.getStatus() );

        Response responseNewPug = target.path( "pugs/1" ).request().accept( MediaType.APPLICATION_JSON ).get();
        EntityTag newTag = responseNewPug.getEntityTag();
        assertEquals( 200, responseNewPug.getStatus() );

        //update with the first version
        //Only perform the action if the client supplied entity matches the same entity on server
        originalPug.setPeso( 1 );
        Response response412 = target.path( "pugs/1" ).request().header( "If-Match", originalTag ).put( Entity.json( originalPug ) );
        assertEquals( 412 ,response412.getStatus() );
    }

}
