package org.fusionsoft.database.migration;

import org.cactoos.Proc;
import org.cactoos.Scalar;
import org.cactoos.Text;
import org.fusionsoft.database.Migration;

public abstract class SqlMigration implements Migration {
    private final Text sql;
    private final Proc<Text> sqlConsumer;
    private final Scalar<Boolean> validation;

    public SqlMigration(Text sql, Proc<Text> sqlConsumer, Scalar<Boolean> validation) {
        this.sql = sql;
        this.sqlConsumer = sqlConsumer;
        this.validation = validation;
    }

    @Override
    public final boolean validate() throws Exception {
        return this.validation.value();
    }

    @Override
    public final void perform() throws Exception {
        sqlConsumer.exec(sql);
    }
}
