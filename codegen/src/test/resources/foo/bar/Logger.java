package foo.bar;

import javax.xml.bind.annotation.*;

/**
 * Enum tests
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Logger {
    @XmlAttribute
    private LoggingLevel loggingLevelAttribute;

/*    @XmlElement
    private LoggingLevel loggingLevelElement;*/

    @XmlValue
    private LoggingLevel loggingLevelValue;
}
