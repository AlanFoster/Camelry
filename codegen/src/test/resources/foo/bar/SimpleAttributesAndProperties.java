package foo.bar;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "SimpleAttributesAndProperties")
@XmlAccessorType(XmlAccessType.FIELD)
public class SimpleAttributesAndProperties {
    @XmlAttribute
    private String id;

    //@XmlAttribute(name = "class")
    //private String clazz;

}
