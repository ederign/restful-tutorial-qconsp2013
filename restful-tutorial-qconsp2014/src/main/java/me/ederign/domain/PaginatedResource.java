package me.ederign.domain;

import java.net.URI;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessOrder;
import javax.xml.bind.annotation.XmlAccessorOrder;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorOrder(XmlAccessOrder.UNDEFINED)
public class PaginatedResource {

    @XmlElement
    private Meta meta;

    @XmlElement
    private String offset;

    @XmlElement
    private String limit;

    @XmlElement
    private URI first;

    @XmlElement
    private URI next;

    @XmlElement
    private URI last;

    @XmlElement
    private List<Pug> items;

    public PaginatedResource() {

    }

    public PaginatedResource( Meta meta,
                              String offset,
                              String limit,
                              URI first,
                              URI next,
                              URI last,
                              List<Pug> items ) {
        this.meta = meta;
        this.offset = offset;
        this.limit = limit;
        this.first = first;
        this.next = next;
        this.last = last;
        this.items = items;
    }
}
