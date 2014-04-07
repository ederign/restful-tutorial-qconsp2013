package me.ederign.infra;

import java.net.URI;
import java.net.URISyntaxException;

import me.ederign.domain.Pug;
import me.ederign.domain.Pugs;
import me.ederign.repository.PugsRepository;

public class DataStore {

    public static void createSampleData() {

        Pugs pugs = new Pugs();

        try {
            pugs.add( new Pug( 1, "Dorinha", 10, new URI( "http://api.puglovers.com/pugs/1" ), new URI( "http://api.puglovers.com/pics/1" ), new URI( "http://api.puglovers.com/pugs/2" ), new URI( "http://api.puglovers.com/pugs/3" ) ) );
            pugs.add( new Pug( 2, "Bentao", 12, new URI( "http://api.puglovers.com/pugs/2" ), new URI( "http://s3.amazonaws.com/ads32fsdaj/1" ), new URI( "http://api.puglovers.com/pugs/2" ), new URI( "http://api.puglovers.com/pugs/3" ) ) );
        } catch ( URISyntaxException e ) {
            e.printStackTrace();
        }

        PugsRepository.setup( pugs );
    }
}
