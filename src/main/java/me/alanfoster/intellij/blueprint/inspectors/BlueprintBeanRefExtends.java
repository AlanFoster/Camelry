package me.alanfoster.intellij.blueprint.inspectors;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Apply to a DomElement which references a BlueprintBean with specific class restrictions,
 * such as throwable.
 *
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 * @see BlueprintBeanRefExtendsChecker
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface BlueprintBeanRefExtends {
    /**
     * @return The required class that the BlueprintBean should extend.
     */
    Class<?> value();
}
