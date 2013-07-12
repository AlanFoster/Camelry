package me.alanfoster.camelus.blueprint.dom;

import com.intellij.util.xml.*;
import me.alanfoster.camelus.blueprint.converters.BeanArgumentIndexConverter;
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
public interface BeanArgument extends BlueprintInjectionElement {
    @Attribute("index")
    @NotNull
    @Referencing(BeanArgumentIndexConverter.class)
    GenericAttributeValue<Integer> getIndex();
}
