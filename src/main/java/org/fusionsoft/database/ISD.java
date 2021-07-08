package org.fusionsoft.database;

import org.fusionsoft.database.description.canonical.CodeObjects;
import org.fusionsoft.database.description.canonical.Containers;
import org.fusionsoft.database.description.canonical.SecurityObjects;
import org.fusionsoft.database.description.canonical.TableObjects;
import org.fusionsoft.database.description.canonical.UDTObjects;

public interface ISD {
    SecurityObjects securityObjects();
    Containers containerObjects();
    TableObjects tableObjects();
    UDTObjects userDefinedTypes();
    CodeObjects codeObjects();
}
