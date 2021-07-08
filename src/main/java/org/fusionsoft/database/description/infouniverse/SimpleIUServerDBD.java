package org.fusionsoft.database.description.infouniverse;

public class SimpleIUServerDBD implements IUServerDBD{
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
    public String key() {
        return this.key;
    }

    @Override
    public String dbType() {
        return this.dbType;
    }

    @Override
    public String url() {
        return this.url;
    }

    @Override
    public String user() {
        return this.user;
    }

    @Override
    public String password() {
        return this.password;
    }

    @Override
    public String description() {
        return this.description;
    }

    @Override
    public String variables() {
        return this.variables;
    }
}
