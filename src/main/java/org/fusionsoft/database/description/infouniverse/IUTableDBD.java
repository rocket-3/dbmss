package org.fusionsoft.database.description.infouniverse;

import java.io.IOException;

public interface IUTableDBD {
    String key() throws IOException;
    Iterable<IUColumnDBD> columns() throws IOException;
    Iterable<IUConstraintDBD> constraints() throws IOException;
    Iterable<IUIndexDBD> indexes() throws IOException;
}
