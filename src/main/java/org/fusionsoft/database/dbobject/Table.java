/*
 * Copyright (C) 2018-2021 FusionSoft
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * You may not use this file except in compliance with the License.
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied.
 *
 * See the License for the specific language governing permissions
 * and limitations under the License.
 */
package org.fusionsoft.database.dbobject;

import org.fusionsoft.database.dbobject.signature.TableDbObjectSignature;
import org.fusionsoft.database.stringproperty.TableProps;

/**
 * A DbObject that represents a table.
 * @since 0.1
 */
public class Table extends SimpleDbObject {

    /**
     * Instantiates a new DbObject with Table' DbObjectType.
     * Constraints the input parameters.
     * @param signature The table signature.
     * @param props The table props.
     */
    public Table(
        final TableDbObjectSignature signature,
        final TableProps props
    ) {
        super(
            signature,
            props
        );
    }

}
