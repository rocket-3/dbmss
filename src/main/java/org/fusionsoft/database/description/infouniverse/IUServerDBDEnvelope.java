package org.fusionsoft.database.description.infouniverse;

import java.io.IOException;
import org.cactoos.Scalar;
import org.cactoos.scalar.IoChecked;
import org.cactoos.scalar.Sticky;

public abstract class IUServerDBDEnvelope implements IUServerDBD {
    private final IoChecked<IUServerDBD> scalar;

    public IUServerDBDEnvelope(Scalar<IUServerDBD> scalar) {
        this.scalar = new IoChecked<>(
            new Sticky<>(scalar)
        );
    }

    @Override
    public String key() throws IOException {
        return scalar.value().key();
    }

    @Override
    public String dbType() throws IOException {
        return scalar.value().dbType();
    }

    @Override
    public String url() throws IOException {
        return scalar.value().url();
    }

    @Override
    public String user() throws IOException {
        return scalar.value().user();
    }

    @Override
    public String password() throws IOException {
        return scalar.value().password();
    }

    @Override
    public String description() throws IOException {
        return scalar.value().description();
    }

    @Override
    public String variables() throws IOException {
        return scalar.value().variables();
    }
}
