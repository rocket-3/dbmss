package org.fusionsoft.database.dbobject;

import java.util.Collection;
import org.cactoos.Scalar;
import org.cactoos.scalar.Unchecked;
import org.fusionsoft.database.DbObject;
import org.fusionsoft.database.DbObjectSignature;
import org.fusionsoft.database.StringProperty;

public class DbObjectOfScalar implements DbObject {
    private final Unchecked<DbObject> scalar;

    public DbObjectOfScalar(Scalar<DbObject> scalar) {
        this.scalar = new Unchecked<>(scalar);
    }

    @Override
    public DbObjectSignature signature() {
        return this.scalar.value().signature();
    }

    @Override
    public Collection<StringProperty> props() {
        return this.scalar.value().props();
    }
}
