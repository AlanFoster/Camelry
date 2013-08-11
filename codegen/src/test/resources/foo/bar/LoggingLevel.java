package foo.bar;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Represents a basic enum
 */
@XmlType
@XmlEnum(String.class)
public enum LoggingLevel {
    @XmlEnumValue("trace")
    TRACE,
    @XmlEnumValue("debug")
    DEBUG,
    @XmlEnumValue("info")
    INFO,
    @XmlEnumValue("warn")
    WARN,
    @XmlEnumValue("error")
    ERROR,
    @XmlEnumValue("all")
    ALL;
}
