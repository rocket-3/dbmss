package org.fusionsoft.database;

import org.cactoos.Text;

public interface DbmsVersion extends Text {
    public DbmsVersion Dummy = () -> "0.0.0.0";
}
