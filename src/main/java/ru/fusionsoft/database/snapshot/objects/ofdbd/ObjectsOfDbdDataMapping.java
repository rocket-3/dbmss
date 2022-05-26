/*
 * Copyright (C) 2018-2022 FusionSoft
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
package ru.fusionsoft.database.snapshot.objects.ofdbd;

import com.amihaiemil.eoyaml.YamlMapping;
import org.cactoos.Text;
import org.cactoos.iterable.IterableEnvelope;
import org.cactoos.iterable.IterableOf;
import ru.fusionsoft.database.mapping.dbd.DbdDataMapping;
import ru.fusionsoft.database.snapshot.DbObject;
import ru.fusionsoft.database.snapshot.data.ObjectOfDbdDataMappingAndTable;
import ru.fusionsoft.database.snapshot.objects.signature.ObjectName;
import ru.fusionsoft.lib.yaml.artefacts.MappingOfMappingIncludeKeys;

/**
 * The objects (one or zero), extracted of single {@link DbdDataMapping}.
 * @since 0.1
 * @checkstyle ClassDataAbstractionCouplingCheck (100 lines)
 */
public class ObjectsOfDbdDataMapping extends IterableEnvelope<DbObject<DbdDataMapping>> {

    /**
     * Instantiates a new Objects of dbd table mapping.
     * @param mapping The {@link YamlMapping} of DBD table to be encapsulated.
     * @param key The key's {@link Text} to be encapsulated.
     * @param table The {@link ObjectName} to be encapsulated.
     */
    public ObjectsOfDbdDataMapping(
        final YamlMapping mapping,
        final Text key,
        final ObjectName table
    ) {
        super(
            new IterableOf<>(
                new ObjectOfDbdDataMappingAndTable(
                    new DbdDataMapping(
                        new MappingOfMappingIncludeKeys(
                            mapping,
                            key
                        )
                    ),
                    table
                )
            )
        );
    }

}
