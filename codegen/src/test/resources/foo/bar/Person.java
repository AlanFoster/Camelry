package foo.bar;

import javax.xml.bind.annotation.*;

/**
 * Represents a basic person POJO
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Person {
    @XmlAttribute
    private int id;

    @XmlAttribute
    private String firstName;

    @XmlAttribute
    private String lastName;

    @XmlElement
    private Address personElement;

    @XmlTransient
    private String ignoreField;
}
