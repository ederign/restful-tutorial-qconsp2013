package me.ederign.repository;

import java.util.List;

import me.ederign.domain.Pug;
import me.ederign.domain.Pugs;

public class PugsRepository {

    private static Pugs pugs;

    public static Pugs findAll() {
        return pugs;
    }

    public static void setup( Pugs pugs ) {
        PugsRepository.pugs = pugs;
    }

    public static Pug findById( String id ) {
        for(Pug pug: pugs.getPugs()){
            if(pug.getId().equals(id))
                return pug;
        }
        return null;
    }
}
