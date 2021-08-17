package org.fusionsoft.database.condition;

import org.cactoos.Scalar;
import org.fusionsoft.database.Condition;

public class ConditionEnvelope implements Condition {
    private final Scalar<Boolean> booleanScalar;

    public ConditionEnvelope(Scalar<Boolean> booleanScalar) {
        this.booleanScalar = booleanScalar;
    }

    @Override
    public Boolean value() throws Exception {
        return this.booleanScalar.value();
    }
}
