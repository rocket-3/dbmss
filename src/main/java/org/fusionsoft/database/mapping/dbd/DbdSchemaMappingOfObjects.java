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
package org.fusionsoft.database.mapping.dbd;

import com.amihaiemil.eoyaml.YamlNode;
import org.cactoos.Text;
import org.cactoos.iterable.IterableOf;
import org.cactoos.iterable.Joined;
import org.cactoos.map.MapEntry;
import org.fusionsoft.database.mapping.MappingOfRepresentative;
import org.fusionsoft.database.mapping.fields.DbdSchemaFields;
import org.fusionsoft.database.snapshot.DbObject;
import org.fusionsoft.database.snapshot.Objects;
import org.fusionsoft.lib.yaml.TextEntriesOfYamlMapping;
import org.fusionsoft.lib.yaml.YamlMappingOfEntries;

/**
 * The DBD/schemas/#schema mapping of {@link Objects}.
 * @since 0.1
 */
public class DbdSchemaMappingOfObjects extends DbdSchemaMapping {

    /**
     * Instantiates a new Yaml mapping envelope.
     * @param objects The Objects to be used.
     * @param schema The DbObject<? extends YamlNode> to be encapsulated.
     */
    public DbdSchemaMappingOfObjects(
        final Objects objects,
        final DbObject<? extends YamlNode> schema
    ) {
        super(
            new YamlMappingOfEntries(
                new Joined<MapEntry<Text, YamlNode>>(
                    new TextEntriesOfYamlMapping(
                        new MappingOfRepresentative(schema)
                    ),
                    new IterableOf<>(
                        new MapEntry<>(
                            DbdSchemaFields.TABLES,
                            new DbdTablesMappingOfObjects(objects, schema)
                        )
                    )
                )
            )
        );
    }

}
