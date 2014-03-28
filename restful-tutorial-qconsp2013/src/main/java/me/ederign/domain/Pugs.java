package me.ederign.domain;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Pugs {

    @XmlElement(name = "pug")
    private List<Pug> pugs = new ArrayList<Pug>();

    public List<Pug> getPugs() {
        return pugs;
    }

    public void add(Pug pug) {
        pugs.add(pug);
    }

    public Integer numberOfPugs(){
        return pugs.size();
    }

}
