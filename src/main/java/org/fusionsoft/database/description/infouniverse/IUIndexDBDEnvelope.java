package org.fusionsoft.database.description.infouniverse;

import java.io.IOException;
import org.cactoos.Scalar;
import org.cactoos.scalar.IoChecked;
import org.cactoos.scalar.Sticky;

public abstract class IUIndexDBDEnvelope implements IUIndexDBD {
    private final IoChecked<IUIndexDBD> scalar;

    public IUIndexDBDEnvelope(Scalar<IUIndexDBD> scalar) {
        this.scalar = new IoChecked<>(
            new Sticky<>(scalar)
        );
    }

    @Override
    public String key() throws IOException {
        return scalar.value().key();
    }

    @Override
    public String dbColumn() throws IOException {
        return scalar.value().dbColumn();
    }

    @Override
    public boolean dbUnique() throws IOException {
        return scalar.value().dbUnique();
    }

    @Override
    public String indexType() throws IOException {
        return scalar.value().indexType();
    }
}
