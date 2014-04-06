package me.ederign.domain;

import java.util.Random;
import javax.xml.bind.annotation.XmlElement;

public class Order {

    @XmlElement
    private String id;

    @XmlElement
    private String client;

    public Order(){};

    public Order( String id, String client) {
        this.id = id;
        this.client = client;
    }
}
