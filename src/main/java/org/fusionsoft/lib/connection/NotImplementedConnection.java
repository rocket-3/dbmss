package org.fusionsoft.lib.connection;


import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class NotImplementedConnection extends ConnectionOfScalar {
    public NotImplementedConnection() {
        super( () -> { throw new NotImplementedException(); } );
    }
}
