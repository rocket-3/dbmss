package org.fusionsoft.database.artefacts.sql;

import org.cactoos.Text;
import org.fusionsoft.database.artefacts.Sql;

public class SqlOfText implements Sql {
    private final Text text;

    public SqlOfText(Text text) {
        this.text = text;
    }

    @Override
    public String asString() throws Exception {
        return text.asString();
    }
}
