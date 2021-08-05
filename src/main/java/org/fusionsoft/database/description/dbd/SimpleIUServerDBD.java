package org.fusionsoft.database.description.dbd;

public class SimpleIUServerDBD implements Server {
    private final String key;
    private final String dbType;
    private final String url;
    private final String user;
    private final String password;
    private final String description;
    private final String variables;

    public SimpleIUServerDBD(String key, String dbType, String url, String user, String password, String description, String variables) {
        this.key = key;
        this.dbType = dbType;
        this.url = url;
        this.user = user;
        this.password = password;
        this.description = description;
        this.variables = variables;
    }

    
    
    @Override
    public final String key() {
        return this.key;
    }

    @Override
    public final String dbType() {
        return this.dbType;
    }

    @Override
    public final String url() {
        return this.url;
    }

    @Override
    public final String user() {
        return this.user;
    }

    @Override
    public final String password() {
        return this.password;
    }

    @Override
    public final String description() {
        return this.description;
    }

    @Override
    public final String variables() {
        return this.variables;
    }
}
