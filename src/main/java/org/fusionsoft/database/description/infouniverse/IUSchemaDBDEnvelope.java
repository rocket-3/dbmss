package org.fusionsoft.database.description.infouniverse;

import java.io.IOException;
import org.cactoos.Scalar;
import org.cactoos.scalar.IoChecked;
import org.cactoos.scalar.Sticky;

public abstract class IUSchemaDBDEnvelope implements IUSchemaDBD {
    private final IoChecked<IUSchemaDBD> scalar;

    public IUSchemaDBDEnvelope(Scalar<IUSchemaDBD> scalar) {
        this.scalar = new IoChecked<>(
            new Sticky<>(scalar)
        );
    }

    @Override
    public String key() throws IOException {
        return scalar.value().key();
    }

    @Override
    public String owner() throws IOException {
        return scalar.value().owner();
    }

    @Override
    public Iterable<IUTableDBD> tableDescriptions() throws IOException {
        return scalar.value().tableDescriptions();
    }
}
