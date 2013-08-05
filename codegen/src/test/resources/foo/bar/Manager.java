package foo.bar;


import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Testing inheritance with JAXB generation.
 */
@XmlRootElement
public class Manager extends Person {
    @XmlAttribute
    private int extraAttribute;

    @XmlElement
    private Address extraHouse;


    // @XmlElement
    // private boolean isEmployed;

    // TODO primitive XmlElement
    // TODO @XmlRef element
    // TODO Enum test
    // TODO Collection support
}

