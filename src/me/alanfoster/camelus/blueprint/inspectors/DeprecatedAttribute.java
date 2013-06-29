package me.alanfoster.camelus.blueprint.inspectors;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Add to a DOM Attribute which has been deprecated.
 *
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 * @se DeprecatedAttribtueChecker
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface DeprecatedAttribute {
    String reason();
    String newName();
}
