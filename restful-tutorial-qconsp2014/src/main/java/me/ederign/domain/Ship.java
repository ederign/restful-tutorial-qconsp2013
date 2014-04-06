package me.ederign.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Ship {

    @XmlElement
    private String id;

    @XmlElement
    private String address;

    public Ship(){};
    public Ship( String id,
                 String address ) {
        this.id = id;
        this.address = address;
    }

    public String getAddress() {
        return address;
    }
}
