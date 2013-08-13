package me.alanfoster.camelry.common;

/**
 * Represents the interface of a generic ConnectionFactory service which may be
 * exposed via OSGi services.
 */
public interface IConnectionFactory {
    Connection getConnection();
}
