package me.ederign.domain;

import java.net.URI;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Meta {

    @XmlElement
    private URI href;

    @XmlElement
    private String mediaType;

    public Meta(){};
    public Meta( URI href,
                 String mediaType ) {
        this.href = href;
        this.mediaType = mediaType;
    }
}
