package org.fusionsoft.database.description.infouniverse;

import java.io.IOException;
import org.cactoos.Scalar;
import org.cactoos.scalar.IoChecked;
import org.cactoos.scalar.Sticky;

public abstract class IUTableDBDEnvelope implements IUTableDBD {
    private final IoChecked<IUTableDBD> scalar;

    public IUTableDBDEnvelope(Scalar<IUTableDBD> scalar) {
        this.scalar = new IoChecked<>(
            new Sticky<>(scalar)
        );
    }

    @Override
    public String key() throws IOException {
        return scalar.value().key();
    }

    @Override
    public Iterable<IUColumnDBD> columns() throws IOException {
        return scalar.value().columns();
    }

    @Override
    public Iterable<IUConstraintDBD> constraints() throws IOException {
        return scalar.value().constraints();
    }

    @Override
    public Iterable<IUIndexDBD> indexes() throws IOException {
        return scalar.value().indexes();
    }
}
