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
package org.fusionsoft.database.snapshot.dbmssignature;

import org.cactoos.Text;
import org.fusionsoft.database.TextEnumValueOf;
import org.fusionsoft.database.mapping.dbd.DbdServerYamlMapping;
import org.fusionsoft.database.mapping.fields.DbdServerFields;
import org.fusionsoft.database.snapshot.DbmsSignature;
import org.fusionsoft.lib.yaml.artefacts.TextOfMappingValue;

/**
 * The type of {@link DbmsSignature} that can be constructed
 *  of {@link DbdServerYamlMapping}.
 * @since 0.1
 */
public class DbmsSignatureOfServerMapping implements DbmsSignature {

    /**
     * The DbdServerYamlMapping encapsulated.
     */
    private final DbdServerYamlMapping mapping;

    /**
     * Instantiates a new DbmsSignatureOfServerMapping.
     * @param mapping The DbdServerYamlMapping to be used.
     */
    public DbmsSignatureOfServerMapping(final DbdServerYamlMapping mapping) {
        this.mapping = mapping;
    }

    @Override
    public final DbmsSignatureName name() {
        return new TextEnumValueOf<>(
            DbmsSignatureName.class,
            new TextOfMappingValue(this.mapping, DbdServerFields.DBTYPE)
        ).value();
    }

    @Override
    public final Text version() {
        return new TextOfMappingValue(this.mapping, DbdServerFields.DBVERSION);
    }

}
