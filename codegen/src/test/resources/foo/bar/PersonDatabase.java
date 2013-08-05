package foo.bar;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Test @XmlRef
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class PersonDatabase {
    @XmlElement(name = "people")
    List<Person> people;
}
