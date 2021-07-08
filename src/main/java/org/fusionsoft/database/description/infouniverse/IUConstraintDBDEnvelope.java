package org.fusionsoft.database.description.infouniverse;

import java.io.IOException;
import java.util.Set;
import org.cactoos.Scalar;
import org.cactoos.scalar.IoChecked;
import org.cactoos.scalar.Sticky;

public abstract class IUConstraintDBDEnvelope implements IUConstraintDBD {
    private final IoChecked<IUConstraintDBD> scalar;

    public IUConstraintDBDEnvelope(Scalar<IUConstraintDBD> scalar) {
        this.scalar = new IoChecked<>(
            new Sticky<>(scalar)
        );
    }

    @Override
    public String key() throws IOException {
        return scalar.value().key();
    }

    @Override
    public Set<String> dbColumn() throws IOException {
        return scalar.value().dbColumn();
    }

    @Override
    public String dbConstraintType() throws IOException {
        return scalar.value().dbConstraintType();
    }

    @Override
    public Set<String> dbFKColumn() throws IOException {
        return scalar.value().dbFKColumn();
    }

    @Override
    public String dbRefSchema() throws IOException {
        return scalar.value().dbRefSchema();
    }

    @Override
    public String dbRefTable() throws IOException {
        return scalar.value().dbRefTable();
    }

    @Override
    public Set<String> dbRefColumn() throws IOException {
        return scalar.value().dbRefColumn();
    }

    @Override
    public String dbRefUpdate() throws IOException {
        return scalar.value().dbRefUpdate();
    }

    @Override
    public String dbRefDelete() throws IOException {
        return scalar.value().dbRefDelete();
    }
}
