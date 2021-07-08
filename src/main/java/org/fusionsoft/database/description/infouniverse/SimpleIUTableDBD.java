package org.fusionsoft.database.description.infouniverse;

import java.io.IOException;

public class SimpleIUTableDBD implements IUTableDBD {
    private final String key;
    private final Iterable<IUColumnDBD> columns;
    private final Iterable<IUConstraintDBD> constraints;
    private final Iterable<IUIndexDBD> indexes;

    public SimpleIUTableDBD(String key, Iterable<IUColumnDBD> columns, Iterable<IUConstraintDBD> constraints, Iterable<IUIndexDBD> indexes) {
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
    public final Iterable<IUColumnDBD> columns() throws IOException {
        return this.columns;
    }

    @Override
    public final Iterable<IUConstraintDBD> constraints() throws IOException {
        return this.constraints;
    }

    @Override
    public final Iterable<IUIndexDBD> indexes() throws IOException {
        return this.indexes;
    }
}
