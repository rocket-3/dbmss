package org.fusionsoft.database.description.dbd;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import org.fusionsoft.database.artefacts.StringProperty;

public class SimpleIUTableDBD implements Table {
    private final String key;
    private final Iterable<Column> columns;
    private final Iterable<Constraint> constraints;
    private final Iterable<Index> indexes;

    public SimpleIUTableDBD(String key, Iterable<Column> columns, Iterable<Constraint> constraints, Iterable<Index> indexes) {
        this.key = key;
        this.columns = columns;
        this.constraints = constraints;
        this.indexes = indexes;
    }

    @Override
    public final String key() throws IOException {
        return this.key;
    }

    @Override
    public final Iterable<Column> columns() throws IOException {
        return this.columns;
    }

    @Override
    public final Iterable<Constraint> constraints() throws IOException {
        return this.constraints;
    }

    @Override
    public final Iterable<Index> indexes() throws IOException {
        return this.indexes;
    }

    @Override
    public Collection<StringProperty> dmbsProps() {
        return Collections.emptySet();
    }
}
