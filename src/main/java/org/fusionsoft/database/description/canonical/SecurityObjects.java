package org.fusionsoft.database.description.canonical;

import org.fusionsoft.database.description.canonical.security.Role;
import org.fusionsoft.database.description.canonical.security.User;

public interface SecurityObjects {
    Iterable<Role> roles();
    Iterable<User> users();
}
