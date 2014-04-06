package me.ederign.domain;

import java.util.Random;
import javax.xml.bind.annotation.XmlElement;

public class Conta {

    @XmlElement
    private String id;

    @XmlElement
    private String titular;

    @XmlElement
    private Integer saldo;

    public Conta(){};

    public Conta( String id, String titular ) {
        this.id = id;
        this.titular = titular;
        this.saldo=new Random(  ).nextInt();
    }
}
