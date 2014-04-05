package me.ederign.infra;

import me.ederign.domain.Pug;
import me.ederign.domain.Pugs;
import me.ederign.repository.PugsRepository;

public class DataStore {

    public static void createSampleData() {

        Pugs pugs = new Pugs();

        pugs.add( new Pug(1, "Dorinha", 10 ) );
        pugs.add( new Pug(2, "Bentao", 12 ) );

        PugsRepository.setup( pugs );
    }
}
