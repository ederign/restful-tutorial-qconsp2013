package me.ederign.repository;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.ws.rs.core.MediaType;

import me.ederign.domain.Meta;
import me.ederign.domain.Pug;
import me.ederign.domain.Pugs;
import me.ederign.domain.PaginatedResource;

public class PugsRepository {

    private static Pugs pugs;

    public static Pugs findAll() {
        return pugs;
    }

    public static void setup( Pugs pugs ) {
        PugsRepository.pugs = pugs;
    }

    public static Pug findById( String id ) {
        for ( Pug pug : pugs.getPugs() ) {
            if ( pug.getId().equals( id ) ) {
                return pug;
            }
        }
        return null;
    }

    public static Pug create( Pug pug ) {
        pug.generateId();
        pugs.add( pug );
        return pug;
    }
    public static Pug create(String id, Pug pug ) {
        pug.setId(new Integer(id));
        pugs.add( pug );
        return pug;
    }

    public static void update( Pug pug ) {
        for ( int i = 0; i < pugs.getPugs().size(); i++ ) {
            Pug candidate = pugs.getPugs().get( i );
            if ( pug.getId().equals( pug.getId().toString() ) ) {
                pugs.add( i, pug );
                return;
            }
        }
    }

    public static void delete( Integer id ) {
        for ( int i = 0; i < pugs.getPugs().size(); i++ ) {
            Pug pug = pugs.getPugs().get( i );
            if ( pug.getId().equals( id.toString() ) ) {
                pugs.getPugs().remove( i );
            }
        }
    }

    public static PaginatedResource findByFilter( String offset,
                                                  String limit ) {
        //sample implementation
        try {
            Meta meta = new Meta( new URI( "http://api.puglovers.com/pugs?offset=" + offset + "&limit=" + limit ), MediaType.APPLICATION_JSON );
            URI first = new URI( "http://api.puglovers.com/pugs?offset=0&limit=" + limit );
            Integer nextIndex = new Integer( offset ) + new Integer( limit );
            URI next = new URI( "http://api.puglovers.com/pugs?offset=" + nextIndex + "&limit=" + limit );
            URI last = new URI( "http://api.puglovers.com/pugs/100" );
            List<Pug> items = findAll().getPugs().subList( new Integer( offset ), new Integer( limit ) );
            PaginatedResource paginatedResource = new PaginatedResource( meta, offset, limit, first, next, last, items );
            return paginatedResource;
        } catch ( URISyntaxException e ) {
            throw new RuntimeException( "Fail" );
        }
    }
}
