package me.ederign.domain;

import java.awt.font.ShapeGraphicAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Pug {

    @XmlElement
    private Integer id;

    @XmlElement
    private String name;

    @XmlElement
    private Integer peso;

    public Pug() {
    }

    public Pug( Integer id,
                String name,
                Integer peso ) {
        this.id=id;
        this.name = name;
        this.peso = peso;
    }

    public String getId() {
        return id.toString();
    }
}
