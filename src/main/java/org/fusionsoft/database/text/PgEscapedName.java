package org.fusionsoft.database.text;

import org.cactoos.Text;

public class PgEscapedName implements Text {
    private final CharSequence originalName;

    public PgEscapedName(CharSequence originalName) {
        this.originalName = originalName;
    }

    @Override
    public String asString()  {
        return "\"" + originalName + "\"";
    }
}
