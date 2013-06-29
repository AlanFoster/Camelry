package me.alanfoster.camelus.blueprint.dom;

import com.intellij.util.xml.*;
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
public interface BeanProperty extends BlueprintInjectionElement {
    @Attribute("name")
    @NotNull
    @Required(nonEmpty = true, value = true)
    @Referencing(BeanPropertyResolver.class)
    GenericAttributeValue<String> getName();
}
