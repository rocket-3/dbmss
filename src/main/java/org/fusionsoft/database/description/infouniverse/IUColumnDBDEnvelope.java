package org.fusionsoft.database.description.infouniverse;

import java.io.IOException;
import java.util.Set;
import org.cactoos.Scalar;
import org.cactoos.scalar.IoChecked;
import org.cactoos.scalar.Sticky;

public abstract class IUColumnDBDEnvelope implements IUColumnDBD {
    private final IoChecked<IUColumnDBD> scalar;

    public IUColumnDBDEnvelope(Scalar<IUColumnDBD> scalar) {
        this.scalar = new IoChecked<>(
            new Sticky<>(scalar)
        );
    }
    
    @Override
    public String name() throws IOException {
        return this.scalar.value().name();
    }

    @Override
    public String iuJsonColumn() throws IOException {
        return this.scalar.value().iuJsonColumn();
    }

    @Override
    public Set<String> iuIncludeProps() throws IOException {
        return this.scalar.value().iuIncludeProps();
    }

    @Override
    public String dbType() throws IOException {
        return this.scalar.value().dbType();
    }

    @Override
    public String iuColumn() throws IOException {
        return this.scalar.value().iuColumn();
    }

    @Override
    public String dbName() throws IOException {
        return this.scalar.value().dbName();
    }

    @Override
    public String type() throws IOException {
        return this.scalar.value().type();
    }

    @Override
    public boolean nullable() throws IOException {
        return this.scalar.value().nullable();
    }

    @Override
    public String description() throws IOException {
        return this.scalar.value().description();
    }

    @Override
    public String dbLocalIdMethod() throws IOException {
        return this.scalar.value().dbLocalIdMethod();
    }
}
