package org.fusionsoft.database.description.dbd;

import java.util.Set;
import org.fusionsoft.lib.yaml.artefacts.FirstNotEmptyTextOf;

public class SimpleColumn implements Column {
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

    public SimpleColumn(String iuColumn, String iuJsonColumn, String type, boolean nullable, String description, Set<String> iuIncludeProps, String dbName, String dbType, String dbLocalIdMethod) {
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
    public final String name() {
        return this.name;
    }

    @Override
    public final String iuColumn() {
        return this.iuColumn;
    }

    @Override
    public final String iuJsonColumn() {
        return this.iuJsonColumn;
    }

    @Override
    public final Set<String> iuIncludeProps() {
        return this.iuIncludeProps;
    }

    @Override
    public final String dbType() {
        return this.dbType;
    }

    @Override
    public final String dbName() {
        return this.dbName;
    }

    @Override
    public final String type() {
        return this.type;
    }

    @Override
    public final boolean nullable() {
        return this.nullable;
    }

    @Override
    public final String description() {
        return this.description;
    }

    @Override
    public final String dbLocalIdMethod() {
        return this.dbLocalIdMethod;
    }
}
