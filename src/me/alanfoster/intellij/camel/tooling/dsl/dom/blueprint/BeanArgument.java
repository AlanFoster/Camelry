package me.alanfoster.intellij.camel.tooling.dsl.dom.blueprint;

import com.intellij.util.xml.*;
import org.jetbrains.annotations.NotNull;

/**
 * Represents the Blueprint DomElement for Bean Arguments definitions.
 * <p/>
 * For example
 * <pre>
 *     {@code
 *     <bean id="myBean" class="com.foo.bar.Baz">
 *         <argument value="Hello World"/>
 *     </bean>
 *      }
 * </pre>
 *
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
// TODO This element will have similar methods to the BlueprintProperty class
public interface BeanArgument extends DomElement {
    @Attribute("value")
    @NotNull
    @Required(nonEmpty = true, value = true)
    GenericAttributeValue<String> getValue();

    @Attribute("index")
    @NotNull
    @Required(nonEmpty = true, value = true)
    @Referencing(BeanArgumentIndexConverter.class)
    GenericAttributeValue<Integer> getIndex();
}
