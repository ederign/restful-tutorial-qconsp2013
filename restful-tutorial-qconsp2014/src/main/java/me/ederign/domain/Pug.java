package me.ederign.domain;

import java.awt.font.ShapeGraphicAttribute;
import java.net.URI;
import java.util.Random;
import javax.ws.rs.core.MediaType;
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

    @XmlElement
    private Meta meta;

    public Pug() {
    }

    public Pug( Integer id,
                String name,
                Integer peso,
                URI href ) {
        this.id = id;
        this.name = name;
        this.peso = peso;
        this.meta = new Meta( href, MediaType.APPLICATION_JSON );
    }

    public String getId() {
        return id.toString();
    }

    public void generateId() {
        id = new Random().nextInt();
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) {
            return true;
        }
        if ( !( o instanceof Pug ) ) {
            return false;
        }

        Pug pug = (Pug) o;

        if ( id != null ? !id.equals( pug.id ) : pug.id != null ) {
            return false;
        }
        if ( name != null ? !name.equals( pug.name ) : pug.name != null ) {
            return false;
        }
        if ( peso != null ? !peso.equals( pug.peso ) : pug.peso != null ) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + ( name != null ? name.hashCode() : 0 );
        result = 31 * result + ( peso != null ? peso.hashCode() : 0 );
        return result;
    }

    public void setPeso( int peso ) {
        this.peso = peso;
    }

    public void setId( Integer id ) {
        this.id = id;
    }
}
