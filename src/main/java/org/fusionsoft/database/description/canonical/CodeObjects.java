package org.fusionsoft.database.description.canonical;

import org.fusionsoft.database.description.canonical.code.Function;
import org.fusionsoft.database.description.canonical.code.Procedure;
import org.fusionsoft.database.description.canonical.code.View;

public interface CodeObjects {
    Iterable<Function> functions();
    Iterable<Procedure> procedures();
    Iterable<View> views();
}
