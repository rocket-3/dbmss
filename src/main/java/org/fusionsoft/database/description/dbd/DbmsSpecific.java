package org.fusionsoft.database.description.dbd;

import java.util.Collection;
import org.fusionsoft.database.StringProperty;

public interface DbmsSpecific {
    Collection<StringProperty> dmbsProps();
}
