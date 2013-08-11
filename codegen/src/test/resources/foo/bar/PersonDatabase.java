package foo.bar;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * Test Lists with @XmlElement and lists with  @XmlRef
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class PersonDatabase {
/*    @XmlElement(name = "people")
    List<Person> people;*/

    @XmlElementRef
    List<Person> peopleRefList;

  /*  @XmlElementRef
    Person peopleRefSingle;

    @XmlElement(name = "names")
    List<String> peopleFirstNames;

    @XmlElements({
            @XmlElement(name = "xxx", type = Person.class),
            @XmlElement(name = "yyy", type = Manager.class)
    })
    List<Person> personListXmlElements;*/
}
