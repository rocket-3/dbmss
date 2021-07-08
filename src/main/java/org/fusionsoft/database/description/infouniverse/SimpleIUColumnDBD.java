package org.fusionsoft.database.description.infouniverse;

import java.util.Set;
import org.fusionsoft.database.ofyaml.FirstNotEmptyTextOf;

public class SimpleIUColumnDBD implements IUColumnDBD {
    private final String name;
    private final String iuColumn;
    private final String iuJsonColumn;
    private final Set<String> iuIncludeProps;
    private final String dbName;
    private final String dbType;
    private final String type;
    private final boolean nullable;
    private final String description;
    private final String dbLocalIdMethod;

    public SimpleIUColumnDBD(String iuColumn, String iuJsonColumn, String type, boolean nullable, String description, Set<String> iuIncludeProps, String dbName, String dbType, String dbLocalIdMethod) {
        try {
            this.name = new FirstNotEmptyTextOf(dbName, iuColumn, iuJsonColumn).asString();
            this.iuJsonColumn = iuJsonColumn;
            this.iuIncludeProps = iuIncludeProps;
            this.dbType = dbType;
            this.iuColumn = iuColumn;
            this.dbName = dbName;
            this.type = type;
            this.nullable = nullable;
            this.description = description;
            this.dbLocalIdMethod = dbLocalIdMethod;
        } catch (Exception e) {
            throw new RuntimeException(
                "Unable to construct an object when all "
                + "'iuColumn', 'iuJsonColumn' and 'dbName' are empty",
                e
            );
        }
    }

    @Override
    public String name() {
        return this.name;
    }

    @Override
    public String iuColumn() {
        return this.iuColumn;
    }

    @Override
    public String iuJsonColumn() {
        return this.iuJsonColumn;
    }

    @Override
    public Set<String> iuIncludeProps() {
        return this.iuIncludeProps;
    }

    @Override
    public String dbType() {
        return this.dbType;
    }

    @Override
    public String dbName() {
        return this.dbName;
    }

    @Override
    public String type() {
        return this.type;
    }

    @Override
    public boolean nullable() {
        return this.nullable;
    }

    @Override
    public String description() {
        return this.description;
    }

    @Override
    public String dbLocalIdMethod() {
        return this.dbLocalIdMethod;
    }
}
