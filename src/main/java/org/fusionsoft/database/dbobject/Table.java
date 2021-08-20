package org.fusionsoft.database.dbobject;

import org.fusionsoft.database.dbobject.signature.TableDbObjectSignature;
import org.fusionsoft.database.stringproperty.TableProps;

public class Table extends SimpleDbObject {
    public Table(TableDbObjectSignature tableSignature, TableProps tableProps) {
        super(
            tableSignature,
            tableProps
        );
    }
}
