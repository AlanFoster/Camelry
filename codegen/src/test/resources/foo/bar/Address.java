package foo.bar;

import javax.xml.bind.annotation.*;

/**
 * Address class to test the XmlValue + XmlAttribute annotations
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Address {
    @XmlAttribute
    private int id;

    @XmlAttribute
    private String country;

    @XmlValue
    private String address;
}
