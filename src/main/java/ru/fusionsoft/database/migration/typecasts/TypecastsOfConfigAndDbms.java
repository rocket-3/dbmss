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
package ru.fusionsoft.database.migration.typecasts;

import org.cactoos.iterable.IterableEnvelope;
import org.cactoos.iterable.Joined;
import org.cactoos.iterable.Mapped;
import org.cactoos.map.MapEntry;
import org.cactoos.map.MapOf;
import org.cactoos.scalar.Unchecked;
import org.cactoos.text.TextOfString;
import ru.fusionsoft.database.mapping.config.MigrationConfigFields;
import ru.fusionsoft.database.mapping.config.MigrationConfigMapping;
import ru.fusionsoft.database.mapping.config.TypecastDetailsFields;
import ru.fusionsoft.database.snapshot.Dbms;
import ru.fusionsoft.database.snapshot.dbms.DbmsOfText;
import ru.fusionsoft.lib.yaml.YamlMappingOfPath;
import ru.fusionsoft.lib.yaml.artefacts.KeysFromYamlNode;
import ru.fusionsoft.lib.yaml.artefacts.TextOfYamlMappingKeyValue;

/**
 * {@link Typecast} iterable, extracted of {@link MigrationConfigMapping} and given {@link Dbms}.
 * @since 0.1
 */
public class TypecastsOfConfigAndDbms extends IterableEnvelope<Typecast> {

    /**
     * Instantiates a new Typecasts of config and dbms.
     * @param config The {@link MigrationConfigMapping} to be encapsulated.
     * @param dbms The {@link Dbms} to be encapsulated.
     */
    public TypecastsOfConfigAndDbms(final MigrationConfigMapping config, final Dbms dbms) {
        super(
            new Unchecked<Iterable<Typecast>>(
                () -> {
                    final YamlMappingOfPath bydbms = new YamlMappingOfPath(
                        config,
                        MigrationConfigFields.DBMS_TYPES_CASTS
                    );
                    return new MapOf<Dbms, Iterable<Typecast>>(
                        new Mapped<>(
                            dbmskey -> new MapEntry<Dbms, Iterable<Typecast>>(
                                new DbmsOfText(new TextOfString(dbmskey)),
                                new Joined<>(
                                    new Mapped<>(
                                        from -> new Mapped<Typecast>(
                                            to -> new SimpleTypecast(
                                                new TextOfString(from),
                                                new TextOfString(to),
                                                Boolean.valueOf(
                                                    new TextOfYamlMappingKeyValue(
                                                        new YamlMappingOfPath(
                                                            bydbms,
                                                            dbmskey,
                                                            from,
                                                            to
                                                        ),
                                                        TypecastDetailsFields.IMPLICIT
                                                    ).asString()
                                                )
                                            ),
                                            new KeysFromYamlNode(
                                                new YamlMappingOfPath(
                                                    bydbms,
                                                    dbmskey,
                                                    from
                                                )
                                            )
                                        ),
                                        new KeysFromYamlNode(
                                            new YamlMappingOfPath(
                                                bydbms,
                                                dbmskey
                                            )
                                        )
                                    )
                                )
                            ),
                            new KeysFromYamlNode(bydbms)
                        )
                    ).get(dbms);
                }
            ).value()
        );
    }

}
