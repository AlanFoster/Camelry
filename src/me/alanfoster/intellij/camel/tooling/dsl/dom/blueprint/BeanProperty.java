package me.alanfoster.intellij.camel.tooling.dsl.dom.blueprint;

import com.intellij.util.xml.Attribute;
import com.intellij.util.xml.DomElement;
import com.intellij.util.xml.GenericAttributeValue;
import com.intellij.util.xml.Required;
import org.jetbrains.annotations.NotNull;

/**
 * Represents the DomElement for Bean properties for Blueprint bean definitions.
 * <p/>
 * For example
 * <pre>
 *     {@code
 *     <bean id="jmsConnectionFactory" class="org.apache.activemq.ActiveMQConnectionFactory">
 *         <property name="brokerURL" value="vm://localhost?broker.persistent=false"/
 *         <property name="userName" value="admin"/
 *         <property name="password" value="admin"/>
 *     </bean>
 *      }
 * </pre>
 *
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
// TODO Support more than just key/value pairs, and support map/list etc too
public interface BeanProperty extends DomElement {
    @Attribute("name")
    @NotNull
    @Required(nonEmpty = true, value = true)
    //@Convert(BeanPropertyResolver.class)
    GenericAttributeValue<String> getName();

    @Attribute("value")
    @NotNull
    @Required(nonEmpty = true, value = true)
    GenericAttributeValue<String> getValue();
}
