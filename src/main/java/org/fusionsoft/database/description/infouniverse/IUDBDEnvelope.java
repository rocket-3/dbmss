package org.fusionsoft.database.description.infouniverse;

import java.io.IOException;
import org.cactoos.Scalar;
import org.cactoos.scalar.IoChecked;
import org.cactoos.scalar.Sticky;

public class IUDBDEnvelope implements IUDBD {
    private final IoChecked<IUDBD> scalar;

    public IUDBDEnvelope(Scalar<IUDBD> scalar) {
        this.scalar = new IoChecked<>(new Sticky<>(scalar));
    }

    @Override
    public Iterable<IUServerDBD> serverDescriptions() throws IOException {
        return scalar.value().serverDescriptions();
    }

    @Override
    public Iterable<IUSchemaDBD> schemaDescriptions() throws IOException {
        return scalar.value().schemaDescriptions();
    }
}
