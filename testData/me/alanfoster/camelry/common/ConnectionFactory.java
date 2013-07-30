package me.alanfoster.camelry.blueprint.camel.dom.common;

import java.lang.String;

/**
 * Represents an implementation of a 'ConnectionFactory' which would have configurable information.
 */
public class ConnectionFactory implements IConnectionFactory {
    /**
     * Represents a value which would be the concatenation of url/username/password.
     * This would be usable instead of three separate calls to the relevant setters.
     */
    private String connectionString;
    private String url;
    private String username;
    private String password;
    private int maximumConnections;
    private int timeout;

    @Override
    public Connection getConnection() {
        return new Connection();
    }

    public String getConnectionString() {
        return connectionString;
    }

    public void setConnectionString(String connectionString) {
        this.connectionString = connectionString;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getMaximumConnections() {
        return maximumConnections;
    }

    public void setMaximumConnections(int maximumConnections) {
        this.maximumConnections = maximumConnections;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }
}
